package io.aclecioscruz.service_card.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardConsumer implements Serializable {

    @Serial
    private static final long serialVersionUID = 6463020446285807718L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cpf;

    @JoinColumn(name = "id_cartao")
    @ManyToOne
    private Card cards;

    private BigDecimal limite;

}
