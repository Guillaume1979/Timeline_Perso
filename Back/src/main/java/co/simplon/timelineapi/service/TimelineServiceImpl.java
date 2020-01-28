package co.simplon.timelineapi.service;

import co.simplon.timelineapi.model.Timeline;
import co.simplon.timelineapi.repository.TimelineRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimelineServiceImpl implements TimelineService {

    private TimelineRepository timelineRepo;

    public TimelineServiceImpl(TimelineRepository timelineRepo) {
        this.timelineRepo = timelineRepo;
    }

    @Override
    public List<Timeline> getAllTimelines() {
        return this.timelineRepo.findAll();
    }

    @Override
    public Timeline saveTimeline(Timeline timeline) {
        return this.timelineRepo.save(timeline);
    }

    @Override
    public void deleteTimeline(Long timelineId) {
        this.timelineRepo.deleteById(timelineId);
    }
}
