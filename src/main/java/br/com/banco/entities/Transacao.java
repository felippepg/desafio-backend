package br.com.banco.entities;

import br.com.banco.enums.Tipo;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "Transacao")
@Table(name = "transacao")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Transacao {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dataTransferencia;
    private BigDecimal valor;
    @Enumerated(EnumType.STRING)
    private Tipo tipo;
    private String nomeOperadorTransacao;
    @ManyToOne
    @JoinColumn(name = "conta_id")
    private Conta conta;
}
