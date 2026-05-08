package jonathandev.dashboard_demo_backend.dto.request;

public record UpdateUserRequest(
    String name,
    String email,
    String passwordHash,
    String role
) {
}