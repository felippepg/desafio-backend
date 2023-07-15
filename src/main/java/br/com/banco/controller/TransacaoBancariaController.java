package br.com.banco.controller;

import br.com.banco.dtos.TransferenciaDto;
import br.com.banco.entities.Transferencia;
import br.com.banco.services.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
