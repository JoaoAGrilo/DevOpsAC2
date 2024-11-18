package com.example.ac2.service;

import com.example.ac2.dto.AlunoDTO;
import com.example.ac2.dto.PagamentoDTO;
import com.example.ac2.entity.Aluno;
import com.example.ac2.entity.Pagamento;
import com.example.ac2.entity.Valor;
import com.example.ac2.repository.AlunoRepository;
import com.example.ac2.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final AlunoRepository alunoRepository;

    @Autowired
    public PagamentoService(PagamentoRepository pagamentoRepository, AlunoRepository alunoRepository) {
        this.pagamentoRepository = pagamentoRepository;
        this.alunoRepository = alunoRepository;
    }

    public String processarPagamento(Long alunoId, PagamentoDTO pagamentoDTO) {
        // Converter DTO para entidade
        Pagamento pagamento = new Pagamento(
                new Valor(pagamentoDTO.getValorCobrado()),
                new Valor(pagamentoDTO.getValorRecebido())
        );

        // Verificar se o aluno existe antes de salvar o pagamento
        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));

        // Salvar o pagamento
        pagamentoRepository.save(pagamento);

        // Verificar validade do pagamento e atualizar o aluno
        if (pagamento.isPagamentoValido()) {
            aluno.setAcessoLiberado(true);
        } else {
            aluno.setAcessoLiberado(false);
        }
        alunoRepository.save(aluno);

        return aluno.isAcessoLiberado() ? "Acesso liberado" : "Acesso não liberado";
    }

    public AlunoDTO buscarAluno(Long alunoId) {
        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));
        return AlunoDTO.fromEntity(aluno);
    }
}
