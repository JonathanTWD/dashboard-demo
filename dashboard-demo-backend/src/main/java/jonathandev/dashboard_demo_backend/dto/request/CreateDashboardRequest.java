package jonathandev.dashboard_demo_backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateDashboardRequest(
    @NotNull
    Long userId,
    @NotBlank
    String name,
    String description,
    String layout,
    Boolean isDefault
) {
}