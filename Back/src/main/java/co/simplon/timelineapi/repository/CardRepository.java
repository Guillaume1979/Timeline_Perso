package co.simplon.timelineapi.repository;

import co.simplon.timelineapi.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {

    public List<Card> findByTimelineId(Long timelineId);

}
