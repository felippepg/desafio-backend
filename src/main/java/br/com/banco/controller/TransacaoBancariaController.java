package br.com.banco.controller;

import br.com.banco.dtos.TransferenciaDto;
import br.com.banco.entities.Transferencia;
import br.com.banco.services.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("transacao")
public class TransacaoBancariaController {
    @Autowired
    TransferenciaService service;

    @GetMapping("/{id}")
    public ResponseEntity<Page<TransferenciaDto>> buscarPorNumeroConta(@PathVariable Long id, @PageableDefault(size = 4) Pageable pageable) {
        var transacoes = service.buscarTransacoesPorNumeroConta(id, pageable);
        var response = ResponseEntity.ok().body(transacoes);
        return response;
    }

    @GetMapping()
    public ResponseEntity<Page<TransferenciaDto>> buscarTodasTransacoes(@PageableDefault(size = 4) Pageable pageable) {
        var transacoes = service.buscarTransacoes(pageable);
        var response = ResponseEntity.ok().body(transacoes);
        return response;
    }

    @GetMapping("/periodo-data")
    public ResponseEntity<Page<Transferencia>> buscarPorPeriodos(
            @RequestParam("data-inicial") String dataInicial,
            @RequestParam("data-final") String dataFinal,
            @PageableDefault(size = 4) Pageable pageable
    ) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//
//        LocalDate dataInicialParsed = LocalDate.parse(dataInicial, formatter);
//        LocalDate dataFinalParsed = LocalDate.parse(dataFinal, formatter);
//
//        LocalDateTime inicioDia = dataInicialParsed.atStartOfDay();
//        LocalDateTime fimDia = dataFinalParsed.atTime(23, 59, 59);
        var transacoes = service.buscarTransacoesPorPeriodos(dataInicial, dataFinal, pageable);
        var response = ResponseEntity.ok().body(transacoes);
        return response;
    }

}
