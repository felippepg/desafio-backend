package br.com.banco.controller;

import br.com.banco.dtos.TransferenciaDto;
import br.com.banco.entities.Transferencia;
import br.com.banco.services.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<TransferenciaDto> buscarPorNumeroConta(@PathVariable Long id) {
        var transacoes = service.buscarTransacoesPorNumeroConta(id);
        var response = ResponseEntity.ok().body(transacoes);
        System.out.println(transacoes);
        return transacoes;
    }

}
