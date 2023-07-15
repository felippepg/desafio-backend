package br.com.banco.services;

import br.com.banco.entities.Conta;
import br.com.banco.entities.Transferencia;
import br.com.banco.repositories.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ContaService {
    @Autowired
    ContaRepository contaRepository;
    public Conta buscarContaPorId(Long id) {
        var conta = contaRepository.findById(id);
        if(conta.isEmpty()) {
            throw new RuntimeException("Conta não encontrada");
        }

        return conta.get();

    }

    public Conta atualizarSaldo(Page<Transferencia> transferencias, Conta conta) {
        BigDecimal saldo = BigDecimal.ZERO;

        for (Transferencia transferencia : transferencias) {
            saldo = saldo.add(transferencia.getValor());
        }

        conta.setSaldoTotal(saldo);
        if(conta.getSaldoNoPeriodo() == null) {
            conta.setSaldoNoPeriodo(conta.getSaldoTotal());
        }
        return conta;
    }

}
