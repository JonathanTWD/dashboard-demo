package jonathandev.dashboard_demo_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jonathandev.dashboard_demo_backend.entity.Dashboard;
import jonathandev.dashboard_demo_backend.entity.DashboardInvite;
import jonathandev.dashboard_demo_backend.entity.DashboardInviteStatus;

public interface DashboardInviteRepository extends JpaRepository<DashboardInvite, Long> {

    List<DashboardInvite> findByDashboard(Dashboard dashboard);

    List<DashboardInvite> findByStatus(DashboardInviteStatus status);

    List<DashboardInvite> findByEmailIgnoreCase(String email);

    java.util.Optional<DashboardInvite> findByToken(String token);
}