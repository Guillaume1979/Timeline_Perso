package co.simplon.timelineapi.service;

import co.simplon.timelineapi.exception.InvalidTimelineIdException;
import co.simplon.timelineapi.model.Card;
import co.simplon.timelineapi.model.Timeline;
import co.simplon.timelineapi.repository.CardRepository;
import co.simplon.timelineapi.repository.TimelineRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardServiceImpl implements CardService {

    private CardRepository cardRepo;
    private TimelineRepository timelineRepo;

    public CardServiceImpl(CardRepository cardRepository, TimelineRepository timelineRepo) {
        this.cardRepo = cardRepository;
        this.timelineRepo = timelineRepo;
    }

    @Override
    public List<Card> getTimelineCards(Long timelineId) {
        return this.cardRepo.findByTimelineId(timelineId);
    }

    @Override
    public Card saveCard(Card card, Long timelineId) {
        Optional<Timeline> cardTimeline = timelineRepo.findById(timelineId);

        if (cardTimeline.isPresent()) {
            card.setTimeline(cardTimeline.get());
            return this.cardRepo.save(card);
        } else {
            throw new InvalidTimelineIdException();
        }
    }

    @Override
    public void deleteCard(Long cardId) {
        cardRepo.deleteById(cardId);
    }
}
