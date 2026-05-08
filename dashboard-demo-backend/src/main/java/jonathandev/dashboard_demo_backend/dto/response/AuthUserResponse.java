package jonathandev.dashboard_demo_backend.dto.response;

public record AuthUserResponse(
    Long id,
    String name,
    String email,
    String role
) {
}