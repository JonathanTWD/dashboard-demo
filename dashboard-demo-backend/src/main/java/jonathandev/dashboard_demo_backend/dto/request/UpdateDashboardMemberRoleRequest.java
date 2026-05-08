package jonathandev.dashboard_demo_backend.dto.request;

import jonathandev.dashboard_demo_backend.entity.DashboardMemberRole;

import jakarta.validation.constraints.NotNull;

public record UpdateDashboardMemberRoleRequest(
    @NotNull
    DashboardMemberRole role
) {
}