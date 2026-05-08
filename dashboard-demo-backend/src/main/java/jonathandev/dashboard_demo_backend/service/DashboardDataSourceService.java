package jonathandev.dashboard_demo_backend.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import jonathandev.dashboard_demo_backend.dto.request.CreateDashboardDataSourceRequest;
import jonathandev.dashboard_demo_backend.dto.request.UpdateDashboardDataSourceRequest;
import jonathandev.dashboard_demo_backend.entity.DashboardDataSource;
import jonathandev.dashboard_demo_backend.repository.DashboardDataSourceRepository;

@Service
@Transactional
public class DashboardDataSourceService {

    private final DashboardDataSourceRepository repository;

    public DashboardDataSourceService(DashboardDataSourceRepository repository) {
        this.repository = repository;
    }

    public DashboardDataSource create(CreateDashboardDataSourceRequest request) {
        DashboardDataSource dataSource = new DashboardDataSource();
        dataSource.setName(request.name());
        dataSource.setType(request.type());
        dataSource.setConfig(request.config() != null ? request.config() : "{}");
        dataSource.setActive(request.active() == null || request.active());
        return repository.save(dataSource);
    }

    @Transactional(readOnly = true)
    public List<DashboardDataSource> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public DashboardDataSource findById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data source not found"));
    }

    public DashboardDataSource update(Long id, UpdateDashboardDataSourceRequest request) {
        DashboardDataSource dataSource = findById(id);
        if (request.name() != null) {
            dataSource.setName(request.name());
        }
        if (request.type() != null) {
            dataSource.setType(request.type());
        }
        if (request.config() != null) {
            dataSource.setConfig(request.config());
        }
        if (request.active() != null) {
            dataSource.setActive(request.active());
        }
        return repository.save(dataSource);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Data source not found");
        }
        repository.deleteById(id);
    }
}