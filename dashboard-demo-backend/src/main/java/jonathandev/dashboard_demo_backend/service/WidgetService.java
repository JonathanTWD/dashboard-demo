package jonathandev.dashboard_demo_backend.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import jonathandev.dashboard_demo_backend.dto.request.CreateWidgetRequest;
import jonathandev.dashboard_demo_backend.dto.request.UpdateWidgetRequest;
import jonathandev.dashboard_demo_backend.entity.Dashboard;
import jonathandev.dashboard_demo_backend.entity.DashboardDataSource;
import jonathandev.dashboard_demo_backend.entity.Widget;
import jonathandev.dashboard_demo_backend.repository.DashboardDataSourceRepository;
import jonathandev.dashboard_demo_backend.repository.DashboardRepository;
import jonathandev.dashboard_demo_backend.repository.WidgetRepository;

@Service
@Transactional
public class WidgetService {

    private final WidgetRepository widgetRepository;
    private final DashboardRepository dashboardRepository;
    private final DashboardDataSourceRepository dataSourceRepository;

    public WidgetService(
        WidgetRepository widgetRepository,
        DashboardRepository dashboardRepository,
        DashboardDataSourceRepository dataSourceRepository
    ) {
        this.widgetRepository = widgetRepository;
        this.dashboardRepository = dashboardRepository;
        this.dataSourceRepository = dataSourceRepository;
    }

    public Widget create(Long dashboardId, CreateWidgetRequest request) {
        Dashboard dashboard = findDashboard(dashboardId);
        Widget widget = new Widget();
        widget.setDashboard(dashboard);
        applyWidgetRequest(widget, request.dataSourceId(), request.title(), request.widgetType(), request.positionX(), request.positionY(), request.width(), request.height(), request.config(), request.refreshIntervalSeconds());
        return widgetRepository.save(widget);
    }

    @Transactional(readOnly = true)
    public List<Widget> findByDashboard(Long dashboardId) {
        Dashboard dashboard = findDashboard(dashboardId);
        return widgetRepository.findByDashboard(dashboard);
    }

    @Transactional(readOnly = true)
    public Widget findById(Long id) {
        return widgetRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Widget not found"));
    }

    public Widget update(Long id, UpdateWidgetRequest request) {
        Widget widget = findById(id);
        applyWidgetRequest(widget, request.dataSourceId(), request.title(), request.widgetType(), request.positionX(), request.positionY(), request.width(), request.height(), request.config(), request.refreshIntervalSeconds());
        return widgetRepository.save(widget);
    }

    public void delete(Long id) {
        if (!widgetRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Widget not found");
        }
        widgetRepository.deleteById(id);
    }

    private Dashboard findDashboard(Long dashboardId) {
        return dashboardRepository.findById(dashboardId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Dashboard not found"));
    }

    private void applyWidgetRequest(
        Widget widget,
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
        if (dataSourceId != null) {
            DashboardDataSource dataSource = dataSourceRepository.findById(dataSourceId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data source not found"));
            widget.setDataSource(dataSource);
        }
        if (title != null) {
            widget.setTitle(title);
        }
        if (widgetType != null) {
            widget.setWidgetType(widgetType);
        }
        if (positionX != null) {
            widget.setPositionX(positionX);
        }
        if (positionY != null) {
            widget.setPositionY(positionY);
        }
        if (width != null) {
            widget.setWidth(width);
        }
        if (height != null) {
            widget.setHeight(height);
        }
        if (config != null) {
            widget.setConfig(config);
        }
        if (refreshIntervalSeconds != null) {
            widget.setRefreshIntervalSeconds(refreshIntervalSeconds);
        }
    }
}