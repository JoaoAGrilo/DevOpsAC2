package com.example.ac2.controller;

import com.example.ac2.dto.AlunoDTO;
import com.example.ac2.dto.PagamentoDTO;
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
            @RequestBody PagamentoDTO pagamentoDTO) {
        String resultado = pagamentoService.processarPagamento(alunoId, pagamentoDTO);
        return ResponseEntity.ok(resultado);
    }

    // Endpoint para buscar informações de um aluno
    @GetMapping("/{alunoId}")
    public ResponseEntity<AlunoDTO> buscarAluno(@PathVariable Long alunoId) {
        AlunoDTO alunoDTO = pagamentoService.buscarAluno(alunoId);
        return ResponseEntity.ok(alunoDTO);
    }
}
