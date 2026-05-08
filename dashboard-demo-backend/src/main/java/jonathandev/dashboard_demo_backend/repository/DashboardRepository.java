package jonathandev.dashboard_demo_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jonathandev.dashboard_demo_backend.entity.AppUser;
import jonathandev.dashboard_demo_backend.entity.Dashboard;

public interface DashboardRepository extends JpaRepository<Dashboard, Long> {

    List<Dashboard> findByUser(AppUser user);
}