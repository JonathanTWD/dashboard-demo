package jonathandev.dashboard_demo_backend.dto.request;

public record UpdateWidgetRequest(
    Long dataSourceId,
    String title,
    String widgetType,
    Integer positionX,
    Integer positionY,
    Integer width,
    Integer height,
    String config,
    Integer refreshIntervalSeconds
) {
}