package io.aclecioscruz.service_card.infra.repositories;

import io.aclecioscruz.service_card.domain.CardConsumer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<CardConsumer, Long> {

    List<CardConsumer> findByCpf(String cpf);

    List<CardConsumer> findByLimiteLessThanEqual(Long income);
}
