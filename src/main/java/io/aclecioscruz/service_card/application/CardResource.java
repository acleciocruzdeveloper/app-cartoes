package io.aclecioscruz.service_card.application;

import io.aclecioscruz.service_card.application.representation.CardRequest;
import io.aclecioscruz.service_card.application.representation.CardsClientResponse;
import io.aclecioscruz.service_card.domain.CardConsumer;
import io.aclecioscruz.service_card.domain.Card;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardResource {

    private final CardsService service;
    private final CardsConsumerService cardsConsumerService;


    @PostMapping
    public ResponseEntity<CardRequest> createdCard(@Validated @RequestBody CardRequest request) {
        Card cards = service.newCard(request.toModel());
        log.info("CARD CREATED {}", request);
        return ResponseEntity.ok()
                .body(request.toRequest(cards));
    }

    @GetMapping
    public ResponseEntity<List<CardRequest>> getCards() {
        List<Card> cards = service.getAllCards();
        List<CardRequest> collect = cards.stream().map(Card::toConverter)
                .toList();
        return ResponseEntity.ok().body(collect);
    }


    @GetMapping(params = "income")
    public ResponseEntity<List<CardRequest>> getCardsIncome(@RequestParam("income") Long income) {
        List<Card> cards = service.findByIncome(income);
        List<CardRequest> collect = cards.stream().map(Card::toConverter)
                .toList();
        return ResponseEntity.ok().body(collect);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<CardsClientResponse>> getCardForCustomer(
            @RequestParam("cpf") String cpf) {
        List<CardConsumer> cardConsumers = cardsConsumerService.offerCardsForCustomers(cpf);

        List<CardsClientResponse> responses = cardConsumers.stream()
                .map(CardsClientResponse::toConverter)
                .toList();

        return ResponseEntity.ok().body(responses);
    }

}
