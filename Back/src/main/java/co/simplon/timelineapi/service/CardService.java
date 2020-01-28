package co.simplon.timelineapi.service;

import co.simplon.timelineapi.model.Card;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CardService {

    List<Card> getTimelineCards(Long timelineId);

    Card saveCard(Card card, Long timelineId);

    void deleteCard(Long cardId);
}
