package com.example.ac2.service;

import com.example.ac2.entity.Aluno;
import com.example.ac2.entity.Pagamento;
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

    public String processarPagamento(Long alunoId, Pagamento pagamento) {
        pagamentoRepository.save(pagamento);
        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));

        if (pagamento.isPagamentoValido()) {
            aluno.setAcessoLiberado(true);
            alunoRepository.save(aluno);
            return "Acesso liberado";
        } else {
            aluno.setAcessoLiberado(false);
            alunoRepository.save(aluno);
            return "Acesso não liberado";
        }
    }
}
