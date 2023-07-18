package br.com.banco.services;

import br.com.banco.config.exception.ParametroNaoEncontadoException;
import br.com.banco.entities.Conta;
import br.com.banco.entities.Transferencia;
import br.com.banco.repositories.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
            throw new ParametroNaoEncontadoException("Conta não encontrada");
        }

        return conta.get();

    }

    public List<Conta> atualizarSaldo(List<Transferencia> transferencias) {
        BigDecimal saldo = BigDecimal.ZERO;
        var contas = contaRepository.findAll();
        for (Transferencia transferencia : transferencias) {
                saldo = saldo.add(transferencia.getValor());
        }

        BigDecimal finalSaldo = saldo;
        contas.stream().forEach(conta -> conta.setSaldoTotal(finalSaldo));
        contas.forEach(conta -> {
            if (conta.getSaldoNoPeriodo() == null) {
                conta.setSaldoNoPeriodo(finalSaldo);
            }
        });

        return contas;
    }

    public List<Conta> buscarTodas() {
        var contas = contaRepository.findAll();
        return contas;
    }

    public List<Conta> atualizaSaldoPeriodo(List<Transferencia> transferencias) {
        BigDecimal saldo = BigDecimal.ZERO;
        var contas = contaRepository.findAll();

        for (Transferencia transferencia : transferencias) {
                saldo = saldo.add(transferencia.getValor());
        }

        BigDecimal finalSaldo = saldo;
        contas.stream().forEach(conta -> conta.setSaldoNoPeriodo(finalSaldo));

        return contas;
    }
}
