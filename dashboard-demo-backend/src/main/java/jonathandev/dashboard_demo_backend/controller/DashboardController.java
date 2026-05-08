package jonathandev.dashboard_demo_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jonathandev.dashboard_demo_backend.dto.request.AcceptDashboardInviteRequest;
import jonathandev.dashboard_demo_backend.dto.request.CreateDashboardInviteRequest;
import jonathandev.dashboard_demo_backend.dto.request.CreateDashboardMemberRequest;
import jonathandev.dashboard_demo_backend.dto.request.CreateDashboardRequest;
import jonathandev.dashboard_demo_backend.dto.request.UpdateDashboardMemberRoleRequest;
import jonathandev.dashboard_demo_backend.dto.request.UpdateDashboardRequest;
import jonathandev.dashboard_demo_backend.entity.Dashboard;
import jonathandev.dashboard_demo_backend.entity.DashboardInvite;
import jonathandev.dashboard_demo_backend.entity.DashboardMember;
import jonathandev.dashboard_demo_backend.service.DashboardService;

@RestController
@RequestMapping("/api/dashboards")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @PostMapping
    public ResponseEntity<Dashboard> create(@Valid @RequestBody CreateDashboardRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(dashboardService.create(request));
    }

    @GetMapping
    public List<Dashboard> list(@RequestParam(required = false) Long userId) {
        return dashboardService.findAll(userId);
    }

    @GetMapping("/{id}")
    public Dashboard get(@PathVariable Long id) {
        return dashboardService.findById(id);
    }

    @PutMapping("/{id}")
    public Dashboard update(@PathVariable Long id, @Valid @RequestBody UpdateDashboardRequest request) {
        return dashboardService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        dashboardService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/members")
    public List<DashboardMember> members(@PathVariable Long id) {
        return dashboardService.getMembers(id);
    }

    @PostMapping("/{id}/members")
    public ResponseEntity<DashboardMember> addMember(@PathVariable Long id, @Valid @RequestBody CreateDashboardMemberRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(dashboardService.addMember(id, request));
    }

    @PutMapping("/{dashboardId}/members/{memberId}")
    public DashboardMember updateMember(@PathVariable Long dashboardId, @PathVariable Long memberId, @Valid @RequestBody UpdateDashboardMemberRoleRequest request) {
        return dashboardService.updateMemberRole(dashboardId, memberId, request);
    }

    @DeleteMapping("/{dashboardId}/members/{memberId}")
    public ResponseEntity<Void> removeMember(@PathVariable Long dashboardId, @PathVariable Long memberId) {
        dashboardService.removeMember(dashboardId, memberId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/invites")
    public List<DashboardInvite> invites(@PathVariable Long id) {
        return dashboardService.getInvites(id);
    }

    @PostMapping("/{id}/invites")
    public ResponseEntity<DashboardInvite> createInvite(@PathVariable Long id, @Valid @RequestBody CreateDashboardInviteRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(dashboardService.createInvite(id, request));
    }

    @PutMapping("/invites/{inviteId}/accept")
    public DashboardInvite acceptInvite(@PathVariable Long inviteId, @Valid @RequestBody AcceptDashboardInviteRequest request) {
        return dashboardService.acceptInvite(inviteId, request);
    }

    @PutMapping("/dashboard-invites/{token}/accept")
    public DashboardInvite acceptInviteByToken(@PathVariable String token, @Valid @RequestBody AcceptDashboardInviteRequest request) {
        return dashboardService.acceptInviteByToken(token, request);
    }

    @PutMapping("/invites/{inviteId}/revoke")
    public DashboardInvite revokeInvite(@PathVariable Long inviteId) {
        return dashboardService.revokeInvite(inviteId);
    }
}