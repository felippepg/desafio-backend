package br.com.banco.services;

import br.com.banco.entities.Conta;
import br.com.banco.entities.Transferencia;
import br.com.banco.repositories.ContaRepository;
import br.com.banco.repositories.TransferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferenciaService {
    @Autowired
    ContaRepository contaRepository;

    @Autowired
    TransferenciaRepository transferenciaRepository;

    public List<Transferencia> buscarTransacoesPorNumeroConta(Long idConta) {

        Conta conta = contaRepository.findById(idConta).get();
        var transacoes = transferenciaRepository.findByConta(conta);
        return transacoes;
    }
}
