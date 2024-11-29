package io.aclecioscruz.service_card.infra.mqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.aclecioscruz.service_card.domain.CardConsumer;
import io.aclecioscruz.service_card.domain.Card;
import io.aclecioscruz.service_card.domain.DadosSolicitacaoEmissorCartao;
import io.aclecioscruz.service_card.infra.repositories.CardsRepository;
import io.aclecioscruz.service_card.infra.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmissorCartaoSubscriber {

    private final ObjectMapper objectMapper;
    private final CardsRepository cardsRepository;
    private final ClientRepository clientRepository;

    @RabbitListener(queues = "${mq.queues.novo-cartao-solicitado}")
    public void receberSolicitacaoEmissao(@Payload String payload) {
        try {
            DadosSolicitacaoEmissorCartao dadosSolicitacaoEmissorCartao = objectMapper.readValue(payload, DadosSolicitacaoEmissorCartao.class);
            Card card = cardsRepository.findById(dadosSolicitacaoEmissorCartao.getIdCartao()).orElseThrow();
            CardConsumer cardConsumer = new CardConsumer();
            cardConsumer.setCards(card);
            cardConsumer.setCpf(dadosSolicitacaoEmissorCartao.getCpf());
            cardConsumer.setLimite(dadosSolicitacaoEmissorCartao.getLimiteLiberado());

            clientRepository.save(cardConsumer);

        } catch (JsonProcessingException e) {
            throw new RuntimeException("erro ao tentar realizar desserialização dos dados");
        }
    }

}
