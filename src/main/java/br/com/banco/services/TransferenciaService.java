package br.com.banco.services;

import br.com.banco.dtos.TransferenciaDto;
import br.com.banco.entities.Conta;
import br.com.banco.repositories.TransferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransferenciaService {

    @Autowired
    TransferenciaRepository transferenciaRepository;

    @Autowired
    ContaService contaService;

    public Page<TransferenciaDto> buscarTransacoesPorNumeroConta(Long idConta, Pageable pageable) {

        Conta conta = contaService.buscarContaPorId(idConta);
        var listaTransacoes = transferenciaRepository.findByConta(conta);
        contaService.atualizarSaldo(listaTransacoes, conta);
        var transacoes = transferenciaRepository.findByConta(conta, pageable);

        return transacoes.map(TransferenciaDto::new);

    }

    public Page<TransferenciaDto> buscarTransacoes(Pageable pageable) {
        var listaTransacoes = transferenciaRepository.findAll();
        var contas = contaService.buscarTodas();
        contas.stream()
                .map(conta -> contaService.atualizarSaldo(listaTransacoes, conta))
                .collect(Collectors.toList());
        var transacoes = transferenciaRepository.findAll(pageable);
        return transacoes.map(TransferenciaDto::new);
    }
}
