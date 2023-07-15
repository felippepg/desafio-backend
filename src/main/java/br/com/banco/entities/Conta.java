package br.com.banco.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "Conta")
@Table(name = "conta")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConta;
    private String nomeResponsavel;
    @Transient
    private BigDecimal saldoTotal;
    @Transient
    private BigDecimal saldoNoPeriodo;
}
