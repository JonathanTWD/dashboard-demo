package jonathandev.dashboard_demo_backend.dto.response;

public record AuthResponse(
    String token,
    String tokenType,
    AuthUserResponse user
) {
}