package br.com.banco.controller;

import br.com.banco.entities.Transferencia;
import br.com.banco.services.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Transferencia> buscarPorNumeroConta(@PathVariable Long id) {
        return service.buscarTransacoesPorNumeroConta(id);
    }

}
