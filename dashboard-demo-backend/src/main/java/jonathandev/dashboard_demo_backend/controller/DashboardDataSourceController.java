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
import jonathandev.dashboard_demo_backend.dto.request.CreateDashboardDataSourceRequest;
import jonathandev.dashboard_demo_backend.dto.request.UpdateDashboardDataSourceRequest;
import jonathandev.dashboard_demo_backend.entity.DashboardDataSource;
import jonathandev.dashboard_demo_backend.service.DashboardDataSourceService;

@RestController
@RequestMapping("/api/data-sources")
public class DashboardDataSourceController {

    private final DashboardDataSourceService service;

    public DashboardDataSourceController(DashboardDataSourceService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DashboardDataSource> create(@Valid @RequestBody CreateDashboardDataSourceRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @GetMapping
    public List<DashboardDataSource> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public DashboardDataSource get(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public DashboardDataSource update(@PathVariable Long id, @Valid @RequestBody UpdateDashboardDataSourceRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}