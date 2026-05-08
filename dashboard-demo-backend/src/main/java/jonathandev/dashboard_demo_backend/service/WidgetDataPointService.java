package jonathandev.dashboard_demo_backend.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import jonathandev.dashboard_demo_backend.dto.request.CreateWidgetDataPointRequest;
import jonathandev.dashboard_demo_backend.entity.Widget;
import jonathandev.dashboard_demo_backend.entity.WidgetDataPoint;
import jonathandev.dashboard_demo_backend.repository.WidgetDataPointRepository;
import jonathandev.dashboard_demo_backend.repository.WidgetRepository;

@Service
@Transactional
public class WidgetDataPointService {

    private final WidgetDataPointRepository repository;
    private final WidgetRepository widgetRepository;

    public WidgetDataPointService(WidgetDataPointRepository repository, WidgetRepository widgetRepository) {
        this.repository = repository;
        this.widgetRepository = widgetRepository;
    }

    public WidgetDataPoint create(Long widgetId, CreateWidgetDataPointRequest request) {
        Widget widget = widgetRepository.findById(widgetId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Widget not found"));

        WidgetDataPoint dataPoint = new WidgetDataPoint();
        dataPoint.setWidget(widget);
        dataPoint.setLabel(request.label());
        dataPoint.setValue(request.value());
        dataPoint.setMeta(request.meta() != null ? request.meta() : "{}");
        return repository.save(dataPoint);
    }

    @Transactional(readOnly = true)
    public List<WidgetDataPoint> findByWidget(Long widgetId) {
        Widget widget = widgetRepository.findById(widgetId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Widget not found"));
        return repository.findByWidgetOrderByRecordedAtDesc(widget);
    }

    @Transactional(readOnly = true)
    public WidgetDataPoint findById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data point not found"));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Data point not found");
        }
        repository.deleteById(id);
    }
}