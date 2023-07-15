package br.com.banco.dtos;

import br.com.banco.entities.Transferencia;
import br.com.banco.enums.Tipo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TransferenciaDto {
    private LocalDateTime dataTransferencia;
    private BigDecimal valor;
    private Tipo tipo;
    private String nomeOperadorTransacao;
    private BigDecimal saldoTotal;
    private BigDecimal saldoTotalPeriodo;

    public TransferenciaDto(Transferencia transferencia) {
        this.dataTransferencia = transferencia.getDataTransferencia();
        this.valor = transferencia.getValor();
        this.tipo = transferencia.getTipo();
        this.nomeOperadorTransacao = transferencia.getNomeOperadorTransacao();
        this.saldoTotal = transferencia.getConta().getSaldoTotal();
        this.saldoTotalPeriodo = transferencia.getConta().getSaldoNoPeriodo();
    }
}
