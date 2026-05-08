package jonathandev.dashboard_demo_backend.dto.request;

public record UpdateDashboardDataSourceRequest(
    String name,
    String type,
    String config,
    Boolean active
) {
}