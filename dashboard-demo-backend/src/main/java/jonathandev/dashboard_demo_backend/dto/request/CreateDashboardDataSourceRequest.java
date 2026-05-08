package jonathandev.dashboard_demo_backend.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CreateDashboardDataSourceRequest(
    @NotBlank
    String name,
    @NotBlank
    String type,
    String config,
    Boolean active
) {
}