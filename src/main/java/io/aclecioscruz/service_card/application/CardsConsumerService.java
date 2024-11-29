package io.aclecioscruz.service_card.application;

import io.aclecioscruz.service_card.domain.CardConsumer;
import io.aclecioscruz.service_card.infra.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CardsConsumerService {

    private final ClientRepository clientRepository;

    public List<CardConsumer> offerCardsForCustomers(String cpf) {
        return clientRepository.findByCpf(cpf);
    }

    @GetMapping(value = "renda")
    public ResponseEntity<List<CardConsumer>> getCardsIncome(@RequestParam("renda") Long income) {
        List<CardConsumer> byPrecoLessThanEqual = clientRepository.findByLimiteLessThanEqual(income);
        return ResponseEntity.ok(byPrecoLessThanEqual);
    }

}
