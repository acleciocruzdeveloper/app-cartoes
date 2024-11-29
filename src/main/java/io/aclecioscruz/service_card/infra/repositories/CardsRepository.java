package io.aclecioscruz.service_card.infra.repositories;

import io.aclecioscruz.service_card.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface CardsRepository extends JpaRepository<Card, Long> {
    List<Card> findByRendaLessThanEqual(BigDecimal renda);
}
