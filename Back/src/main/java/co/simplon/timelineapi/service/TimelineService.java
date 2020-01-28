package co.simplon.timelineapi.service;

import co.simplon.timelineapi.model.Timeline;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TimelineService {

    List<Timeline> getAllTimelines();

    Timeline saveTimeline(Timeline timeline);

    void deleteTimeline(Long timelineId);

}
