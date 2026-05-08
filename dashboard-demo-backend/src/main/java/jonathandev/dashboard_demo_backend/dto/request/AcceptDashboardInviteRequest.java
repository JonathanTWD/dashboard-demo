package jonathandev.dashboard_demo_backend.dto.request;

import jakarta.validation.constraints.NotNull;

public record AcceptDashboardInviteRequest(
    @NotNull
    Long userId
) {
}