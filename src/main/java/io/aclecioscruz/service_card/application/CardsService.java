package io.aclecioscruz.service_card.application;

import io.aclecioscruz.service_card.domain.Card;
import io.aclecioscruz.service_card.infra.repositories.CardsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CardsService {

    private final CardsRepository cardsRepository;

    @Transactional
    public Card newCard(Card cards) {
        return cardsRepository.save(cards);
    }

    public List<Card> findByIncome(Long renda) {
        return cardsRepository.findByRendaLessThanEqual(
                BigDecimal.valueOf(renda)
        );
    }

    public List<Card> getAllCards() {
        return cardsRepository.findAll();
    }
}
