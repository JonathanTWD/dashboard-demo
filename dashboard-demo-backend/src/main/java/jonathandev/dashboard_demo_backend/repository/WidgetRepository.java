package jonathandev.dashboard_demo_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jonathandev.dashboard_demo_backend.entity.Dashboard;
import jonathandev.dashboard_demo_backend.entity.Widget;

public interface WidgetRepository extends JpaRepository<Widget, Long> {

    List<Widget> findByDashboard(Dashboard dashboard);
}