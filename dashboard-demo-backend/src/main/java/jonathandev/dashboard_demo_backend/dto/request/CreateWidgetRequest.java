package jonathandev.dashboard_demo_backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateWidgetRequest(
    Long dataSourceId,
    @NotBlank
    String title,
    @NotBlank
    String widgetType,
    @NotNull
    Integer positionX,
    @NotNull
    Integer positionY,
    @NotNull
    Integer width,
    @NotNull
    Integer height,
    String config,
    Integer refreshIntervalSeconds
) {
}