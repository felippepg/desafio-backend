package br.com.banco.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContaDto {
    private BigDecimal saldoTotal;
    private BigDecimal saldoPeriodo;
}
