package jonathandev.dashboard_demo_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jonathandev.dashboard_demo_backend.entity.AppUser;
import jonathandev.dashboard_demo_backend.entity.Dashboard;
import jonathandev.dashboard_demo_backend.entity.DashboardMember;

public interface DashboardMemberRepository extends JpaRepository<DashboardMember, Long> {

    List<DashboardMember> findByDashboard(Dashboard dashboard);

    List<DashboardMember> findByUser(AppUser user);
}