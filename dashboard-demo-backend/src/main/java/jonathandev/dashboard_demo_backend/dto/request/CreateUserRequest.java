package jonathandev.dashboard_demo_backend.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CreateUserRequest(
    @NotBlank
    String name,
    @NotBlank
    String email,
    @NotBlank
    String passwordHash,
    String role
) {
}