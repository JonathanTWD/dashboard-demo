package jonathandev.dashboard_demo_backend.service;

import java.time.Instant;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import jonathandev.dashboard_demo_backend.dto.request.AcceptDashboardInviteRequest;
import jonathandev.dashboard_demo_backend.dto.request.CreateDashboardInviteRequest;
import jonathandev.dashboard_demo_backend.dto.request.CreateDashboardMemberRequest;
import jonathandev.dashboard_demo_backend.dto.request.CreateDashboardRequest;
import jonathandev.dashboard_demo_backend.dto.request.UpdateDashboardMemberRoleRequest;
import jonathandev.dashboard_demo_backend.dto.request.UpdateDashboardRequest;
import jonathandev.dashboard_demo_backend.entity.AppUser;
import jonathandev.dashboard_demo_backend.entity.Dashboard;
import jonathandev.dashboard_demo_backend.entity.DashboardInvite;
import jonathandev.dashboard_demo_backend.entity.DashboardInviteStatus;
import jonathandev.dashboard_demo_backend.entity.DashboardMember;
import jonathandev.dashboard_demo_backend.entity.DashboardMemberRole;
import jonathandev.dashboard_demo_backend.repository.AppUserRepository;
import jonathandev.dashboard_demo_backend.repository.DashboardInviteRepository;
import jonathandev.dashboard_demo_backend.repository.DashboardMemberRepository;
import jonathandev.dashboard_demo_backend.repository.DashboardRepository;

@Service
@Transactional
public class DashboardService {

    private final DashboardRepository dashboardRepository;
    private final AppUserRepository userRepository;
    private final DashboardMemberRepository memberRepository;
    private final DashboardInviteRepository inviteRepository;

    public DashboardService(
        DashboardRepository dashboardRepository,
        AppUserRepository userRepository,
        DashboardMemberRepository memberRepository,
        DashboardInviteRepository inviteRepository
    ) {
        this.dashboardRepository = dashboardRepository;
        this.userRepository = userRepository;
        this.memberRepository = memberRepository;
        this.inviteRepository = inviteRepository;
    }

    public Dashboard create(CreateDashboardRequest request) {
        AppUser owner = userRepository.findById(request.userId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Owner user not found"));

        Dashboard dashboard = new Dashboard();
        dashboard.setUser(owner);
        dashboard.setName(request.name());
        dashboard.setDescription(request.description());
        dashboard.setLayout(request.layout() != null ? request.layout() : "{}");
        dashboard.setDefaultDashboard(Boolean.TRUE.equals(request.isDefault()));

        Dashboard saved = dashboardRepository.save(dashboard);

        DashboardMember ownerMember = new DashboardMember();
        ownerMember.setDashboard(saved);
        ownerMember.setUser(owner);
        ownerMember.setRole(DashboardMemberRole.OWNER);
        memberRepository.save(ownerMember);

        return saved;
    }

    @Transactional(readOnly = true)
    public List<Dashboard> findAll(Long userId) {
        if (userId != null) {
            AppUser user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
            return dashboardRepository.findByUser(user);
        }
        return dashboardRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Dashboard findById(Long id) {
        return dashboardRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Dashboard not found"));
    }

    public Dashboard update(Long id, UpdateDashboardRequest request) {
        Dashboard dashboard = findById(id);

        if (request.name() != null) {
            dashboard.setName(request.name());
        }
        if (request.description() != null) {
            dashboard.setDescription(request.description());
        }
        if (request.layout() != null) {
            dashboard.setLayout(request.layout());
        }
        if (request.isDefault() != null) {
            dashboard.setDefaultDashboard(request.isDefault());
        }

        return dashboardRepository.save(dashboard);
    }

    public void delete(Long id) {
        if (!dashboardRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Dashboard not found");
        }
        dashboardRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<DashboardMember> getMembers(Long dashboardId) {
        Dashboard dashboard = findById(dashboardId);
        return memberRepository.findByDashboard(dashboard);
    }

    public DashboardMember addMember(Long dashboardId, CreateDashboardMemberRequest request) {
        Dashboard dashboard = findById(dashboardId);
        AppUser user = userRepository.findById(request.userId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        boolean exists = memberRepository.findByDashboard(dashboard).stream()
            .anyMatch(member -> member.getUser().getId().equals(user.getId()));
        if (exists) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User is already a member of the dashboard");
        }

        DashboardMember member = new DashboardMember();
        member.setDashboard(dashboard);
        member.setUser(user);
        member.setRole(request.role() != null ? request.role() : DashboardMemberRole.EDITOR);
        return memberRepository.save(member);
    }

    public DashboardMember updateMemberRole(Long dashboardId, Long memberId, UpdateDashboardMemberRoleRequest request) {
        DashboardMember member = findMember(dashboardId, memberId);
        if (member.getRole() == DashboardMemberRole.OWNER) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Owner role cannot be changed");
        }
        member.setRole(request.role());
        return memberRepository.save(member);
    }

    public void removeMember(Long dashboardId, Long memberId) {
        DashboardMember member = findMember(dashboardId, memberId);
        if (member.getRole() == DashboardMemberRole.OWNER) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Owner cannot be removed");
        }
        memberRepository.delete(member);
    }

    @Transactional(readOnly = true)
    public List<DashboardInvite> getInvites(Long dashboardId) {
        Dashboard dashboard = findById(dashboardId);
        return inviteRepository.findByDashboard(dashboard);
    }

    public DashboardInvite createInvite(Long dashboardId, CreateDashboardInviteRequest request) {
        Dashboard dashboard = findById(dashboardId);

        DashboardInvite invite = new DashboardInvite();
        invite.setDashboard(dashboard);
        invite.setEmail(request.email());
        invite.setRole(request.role() != null ? request.role() : DashboardMemberRole.EDITOR);
        invite.setStatus(DashboardInviteStatus.PENDING);
        invite.setToken(generateToken());
        invite.setExpiresAt(request.expiresAt() != null ? request.expiresAt() : Instant.now().plusSeconds(604800));

        if (request.invitedByUserId() != null) {
            invite.setInvitedByUser(userRepository.findById(request.invitedByUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Inviting user not found")));
        }

        return inviteRepository.save(invite);
    }

    public DashboardInvite acceptInvite(Long inviteId, AcceptDashboardInviteRequest request) {
        DashboardInvite invite = findInvite(inviteId);
        return acceptInvite(invite, request);
    }

    public DashboardInvite acceptInviteByToken(String token, AcceptDashboardInviteRequest request) {
        DashboardInvite invite = inviteRepository.findByToken(token)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invite not found"));
        return acceptInvite(invite, request);
    }

    private DashboardInvite acceptInvite(DashboardInvite invite, AcceptDashboardInviteRequest request) {
        if (invite.getStatus() != DashboardInviteStatus.PENDING) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Invite is not pending");
        }
        if (invite.getExpiresAt() != null && invite.getExpiresAt().isBefore(Instant.now())) {
            invite.setStatus(DashboardInviteStatus.EXPIRED);
            inviteRepository.save(invite);
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Invite has expired");
        }

        AppUser user = userRepository.findById(request.userId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        boolean alreadyMember = memberRepository.findByDashboard(invite.getDashboard()).stream()
            .anyMatch(member -> member.getUser().getId().equals(user.getId()));
        if (alreadyMember) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User is already a member of the dashboard");
        }

        DashboardMember member = new DashboardMember();
        member.setDashboard(invite.getDashboard());
        member.setUser(user);
        member.setRole(invite.getRole());
        memberRepository.save(member);

        invite.setStatus(DashboardInviteStatus.ACCEPTED);
        invite.setAcceptedAt(Instant.now());
        return inviteRepository.save(invite);
    }

    public DashboardInvite revokeInvite(Long inviteId) {
        DashboardInvite invite = findInvite(inviteId);
        invite.setStatus(DashboardInviteStatus.REVOKED);
        return inviteRepository.save(invite);
    }

    private DashboardMember findMember(Long dashboardId, Long memberId) {
        DashboardMember member = memberRepository.findById(memberId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found"));
        if (!member.getDashboard().getId().equals(dashboardId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found in dashboard");
        }
        return member;
    }

    private DashboardInvite findInvite(Long inviteId) {
        return inviteRepository.findById(inviteId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invite not found"));
    }

    private String generateToken() {
        return java.util.UUID.randomUUID().toString().replace("-", "");
    }
}