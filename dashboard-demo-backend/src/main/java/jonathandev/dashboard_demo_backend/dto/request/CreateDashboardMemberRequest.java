package jonathandev.dashboard_demo_backend.dto.request;

import jonathandev.dashboard_demo_backend.entity.DashboardMemberRole;

import jakarta.validation.constraints.NotNull;

public record CreateDashboardMemberRequest(
    @NotNull
    Long userId,
    DashboardMemberRole role
) {
}