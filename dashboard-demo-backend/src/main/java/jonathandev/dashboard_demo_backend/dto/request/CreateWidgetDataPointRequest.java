package jonathandev.dashboard_demo_backend.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;

public record CreateWidgetDataPointRequest(
    String label,
    @NotNull
    BigDecimal value,
    String meta
) {
}