package jonathandev.dashboard_demo_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jonathandev.dashboard_demo_backend.entity.Widget;
import jonathandev.dashboard_demo_backend.entity.WidgetDataPoint;

public interface WidgetDataPointRepository extends JpaRepository<WidgetDataPoint, Long> {

    List<WidgetDataPoint> findByWidgetOrderByRecordedAtDesc(Widget widget);
}