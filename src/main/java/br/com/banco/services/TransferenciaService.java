package br.com.banco.services;

import br.com.banco.dtos.TransferenciaDto;
import br.com.banco.entities.Conta;
import br.com.banco.entities.Transferencia;
import br.com.banco.repositories.ContaRepository;
import br.com.banco.repositories.TransferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
        var transacoes = transferenciaRepository.findByConta(conta, pageable);
        contaService.atualizarSaldo(transacoes, conta);

//        return transacoes.stream()
//                .map(TransferenciaDto::new)
//                .collect(Collectors.toList());
        return transacoes.map(TransferenciaDto::new);

    }
}
