package jonathandev.dashboard_demo_backend.dto.request;

import java.time.Instant;

import jonathandev.dashboard_demo_backend.entity.DashboardMemberRole;

import jakarta.validation.constraints.NotBlank;

public record CreateDashboardInviteRequest(
    @NotBlank
    String email,
    Long invitedByUserId,
    DashboardMemberRole role,
    Instant expiresAt
) {
}