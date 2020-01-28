package co.simplon.timelineapi.repository;

import co.simplon.timelineapi.model.Timeline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimelineRepository extends JpaRepository<Timeline, Long> {

}
