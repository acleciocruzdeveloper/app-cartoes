package io.aclecioscruz.service_card.application.representation;

import io.aclecioscruz.service_card.domain.Card;
import io.aclecioscruz.service_card.domain.EFlag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardRequest {


    private Long id;
    private String nome;
    private EFlag flag;
    private BigDecimal renda;
    private BigDecimal limite;

    public Card toModel() {
        return Card.builder()
                .nome(nome)
                .flag(flag)
                .limite(limite)
                .renda(renda)
                .build();
    }

    public CardRequest toRequest(Card cards){
        return CardRequest.builder()
                .id(cards.getId())
                .nome(cards.getNome())
                .limite(cards.getLimite())
                .renda(cards.getRenda())
                .flag(cards.getFlag())
                .build();
    }
}
