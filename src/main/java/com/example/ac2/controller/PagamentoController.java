package com.example.ac2.controller;

import com.example.ac2.entity.Pagamento;
import com.example.ac2.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pagamentos")
public class PagamentoController {

    private final PagamentoService pagamentoService;

    @Autowired
    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    // Endpoint de teste para verificar se o controlador está funcionando
    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello, World!";
    }

    // Endpoint para processar um pagamento
    @PostMapping("/processar/{alunoId}")
    public ResponseEntity<String> processarPagamento(
            @PathVariable Long alunoId, 
            @RequestBody Pagamento pagamento) {
        String resultado = pagamentoService.processarPagamento(alunoId, pagamento);
        return ResponseEntity.ok(resultado);
    }
}
