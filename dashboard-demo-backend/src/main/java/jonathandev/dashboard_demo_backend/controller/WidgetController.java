package jonathandev.dashboard_demo_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jonathandev.dashboard_demo_backend.dto.request.CreateWidgetRequest;
import jonathandev.dashboard_demo_backend.dto.request.UpdateWidgetRequest;
import jonathandev.dashboard_demo_backend.entity.Widget;
import jonathandev.dashboard_demo_backend.service.WidgetService;

@RestController
@RequestMapping("/api")
public class WidgetController {

    private final WidgetService service;

    public WidgetController(WidgetService service) {
        this.service = service;
    }

    @PostMapping("/dashboards/{dashboardId}/widgets")
    public ResponseEntity<Widget> create(@PathVariable Long dashboardId, @Valid @RequestBody CreateWidgetRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dashboardId, request));
    }

    @GetMapping("/dashboards/{dashboardId}/widgets")
    public List<Widget> listByDashboard(@PathVariable Long dashboardId) {
        return service.findByDashboard(dashboardId);
    }

    @GetMapping("/widgets/{id}")
    public Widget get(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/widgets/{id}")
    public Widget update(@PathVariable Long id, @Valid @RequestBody UpdateWidgetRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/widgets/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}