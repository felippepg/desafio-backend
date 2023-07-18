package br.com.banco.services;

import br.com.banco.dtos.TransferenciaDto;
import br.com.banco.entities.Conta;
import br.com.banco.entities.Transferencia;
import br.com.banco.repositories.TransferenciaRepository;
import br.com.banco.utilitario.ConverterData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransferenciaService {

    @Autowired
    TransferenciaRepository transferenciaRepository;

    @Autowired
    ContaService contaService;

    @Autowired
    ConverterData conversor;

    public Page<TransferenciaDto> buscarTransacoesPorNumeroConta(Long idConta, Pageable pageable) {

        Conta conta = contaService.buscarContaPorId(idConta);
        var listaTransacoes = transferenciaRepository.findByConta(conta);
        contaService.atualizarSaldo(listaTransacoes);
        var transacoes = transferenciaRepository.findByConta(conta, pageable);

        return transacoes.map(TransferenciaDto::new);

    }

    public Page<TransferenciaDto> buscarTransacoes(Pageable pageable) {
        var listaTransacoes = transferenciaRepository.findAll();
        var contas = contaService.buscarTodas();
        contaService.atualizarSaldo(listaTransacoes);
        var transacoes = transferenciaRepository.findAll(pageable);
        return transacoes.map(TransferenciaDto::new);
    }

    public Page<TransferenciaDto> buscarTransacoesPorPeriodos(String dataInicial, String dataFinal, Pageable pageable) {
        List<Conta> contas = contaService.buscarTodas();
        var datas = conversor.tranformarStringEmData(dataInicial, dataFinal);
        var transacoes = transferenciaRepository.findByDataTransferenciaBetween(datas.get(0), datas.get(1), pageable);
        var listaTransacoes = transferenciaRepository.findByDataTransferenciaBetween(datas.get(0), datas.get(1));
        var listaTransacoesSaldoTotal = transferenciaRepository.findAll();
        contaService.atualizaSaldoPeriodo(listaTransacoes);
        contaService.atualizarSaldo(listaTransacoesSaldoTotal);

        return transacoes.map(TransferenciaDto::new);

    }

    public Page<TransferenciaDto> buscarTransacoesPorPeriodosEOperador(String dataInicial, String dataFinal, String operador, Pageable pageable) {
        List<Conta> contas = contaService.buscarTodas();
        var datas = conversor.tranformarStringEmData(dataInicial, dataFinal);
        var transacoes = transferenciaRepository.findByDataTransferenciaBetweenAndNomeOperadorTransacao(datas.get(0), datas.get(1), operador, pageable);
        var listaTransacoes = transferenciaRepository.findByDataTransferenciaBetweenAndNomeOperadorTransacao(datas.get(0), datas.get(1), operador);
        var listaTransacoesSaldoTotal = transferenciaRepository.findAll();
        contaService.atualizaSaldoPeriodo(listaTransacoes);
        contaService.atualizarSaldo(listaTransacoesSaldoTotal);

        return transacoes.map(TransferenciaDto::new);
    }

    public Page<TransferenciaDto> buscarTransacoesPorNomeOperador(String operador, Pageable pageable) {
        List<Conta> contas = contaService.buscarTodas();
        var listaTransacoes = transferenciaRepository.findByNomeOperadorTransacao(operador);
        contaService.atualizarSaldo(listaTransacoes);

        var transacoes = transferenciaRepository.findByNomeOperadorTransacao(operador, pageable);

        return transacoes.map(TransferenciaDto::new);
    }
}
