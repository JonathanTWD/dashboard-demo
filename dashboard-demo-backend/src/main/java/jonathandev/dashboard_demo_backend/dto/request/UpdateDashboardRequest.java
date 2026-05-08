package jonathandev.dashboard_demo_backend.dto.request;

public record UpdateDashboardRequest(
    String name,
    String description,
    String layout,
    Boolean isDefault
) {
}