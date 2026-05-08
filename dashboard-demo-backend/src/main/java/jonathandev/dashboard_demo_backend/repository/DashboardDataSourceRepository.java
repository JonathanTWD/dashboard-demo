package jonathandev.dashboard_demo_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jonathandev.dashboard_demo_backend.entity.DashboardDataSource;

public interface DashboardDataSourceRepository extends JpaRepository<DashboardDataSource, Long> {
}