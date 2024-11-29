package io.aclecioscruz.service_card.application.representation;

import io.aclecioscruz.service_card.domain.CardConsumer;
import io.aclecioscruz.service_card.domain.EFlag;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
public class CardsClientResponse implements Serializable {


    @Serial
    private static final long serialVersionUID = 4674387553415422477L;

    private String nome;
    private EFlag flag;
    private BigDecimal limite;

    public static CardsClientResponse toConverter(CardConsumer data) {
        return CardsClientResponse.builder()
                .nome(data.getCards().getNome())
                .flag(data.getCards().getFlag())
                .limite(data.getLimite())
                .build();
    }
}
