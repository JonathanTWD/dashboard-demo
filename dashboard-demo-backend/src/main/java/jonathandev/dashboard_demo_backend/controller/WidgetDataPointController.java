package jonathandev.dashboard_demo_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jonathandev.dashboard_demo_backend.dto.request.CreateWidgetDataPointRequest;
import jonathandev.dashboard_demo_backend.entity.WidgetDataPoint;
import jonathandev.dashboard_demo_backend.service.WidgetDataPointService;

@RestController
@RequestMapping("/api")
public class WidgetDataPointController {

    private final WidgetDataPointService service;

    public WidgetDataPointController(WidgetDataPointService service) {
        this.service = service;
    }

    @PostMapping("/widgets/{widgetId}/data-points")
    public ResponseEntity<WidgetDataPoint> create(@PathVariable Long widgetId, @Valid @RequestBody CreateWidgetDataPointRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(widgetId, request));
    }

    @GetMapping("/widgets/{widgetId}/data-points")
    public List<WidgetDataPoint> listByWidget(@PathVariable Long widgetId) {
        return service.findByWidget(widgetId);
    }

    @GetMapping("/widget-data-points/{id}")
    public WidgetDataPoint get(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/widget-data-points/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}