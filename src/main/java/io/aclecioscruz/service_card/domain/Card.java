package io.aclecioscruz.service_card.domain;

import io.aclecioscruz.service_card.application.representation.CardRequest;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Card implements Serializable {

    @Serial
    private static final long serialVersionUID = 93027098474102624L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Enumerated(value = EnumType.STRING)
    private EFlag flag;
    private BigDecimal renda;
    private BigDecimal limite;

    public CardRequest toConverter() {
        return CardRequest.builder()
                .id(id)
                .flag(flag)
                .nome(nome)
                .limite(limite)
                .renda(renda)
                .build();
    }

}
