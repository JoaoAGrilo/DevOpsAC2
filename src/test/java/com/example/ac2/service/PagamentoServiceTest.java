package com.example.ac2.service;

import com.example.ac2.dto.PagamentoDTO;
import com.example.ac2.entity.Aluno;
import com.example.ac2.entity.Pagamento;
import com.example.ac2.entity.Valor;
import com.example.ac2.repository.AlunoRepository;
import com.example.ac2.repository.PagamentoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class PagamentoServiceTest {

    @Mock
    private PagamentoRepository pagamentoRepository;

    @Mock
    private AlunoRepository alunoRepository;

    @InjectMocks
    private PagamentoService pagamentoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testProcessarPagamento_AcessoLiberado() {
        // Arrange
        Long alunoId = 1L;
        Aluno aluno = new Aluno("João", false);
        aluno.setId(alunoId);

        PagamentoDTO pagamentoDTO = new PagamentoDTO(
                null,
                BigDecimal.valueOf(100.00),
                BigDecimal.valueOf(100.00)
        );

        Pagamento pagamento = new Pagamento(
                new Valor(pagamentoDTO.getValorCobrado()),
                new Valor(pagamentoDTO.getValorRecebido())
        );

        when(alunoRepository.findById(alunoId)).thenReturn(Optional.of(aluno));
        when(pagamentoRepository.save(any(Pagamento.class))).thenReturn(pagamento);

        // Act
        String resultado = pagamentoService.processarPagamento(alunoId, pagamentoDTO);

        // Assert
        assertEquals("Acesso liberado", resultado);

        verify(pagamentoRepository, times(1)).save(any(Pagamento.class));
        verify(alunoRepository, times(1)).save(aluno);
    }

    @Test
    void testProcessarPagamento_AcessoNaoLiberado() {
        // Arrange
        Long alunoId = 1L;
        Aluno aluno = new Aluno("Maria", false);
        aluno.setId(alunoId);

        PagamentoDTO pagamentoDTO = new PagamentoDTO(
                null,
                BigDecimal.valueOf(100.00),
                BigDecimal.valueOf(50.00)
        );

        Pagamento pagamento = new Pagamento(
                new Valor(pagamentoDTO.getValorCobrado()),
                new Valor(pagamentoDTO.getValorRecebido())
        );

        when(alunoRepository.findById(alunoId)).thenReturn(Optional.of(aluno));
        when(pagamentoRepository.save(any(Pagamento.class))).thenReturn(pagamento);

        // Act
        String resultado = pagamentoService.processarPagamento(alunoId, pagamentoDTO);

        // Assert
        assertEquals("Acesso não liberado", resultado);

        verify(pagamentoRepository, times(1)).save(any(Pagamento.class));
        verify(alunoRepository, times(1)).save(aluno);
    }

    @Test
    void testProcessarPagamento_AlunoNaoEncontrado() {
        // Arrange
        Long alunoId = 99L;

        PagamentoDTO pagamentoDTO = new PagamentoDTO(
                null,
                BigDecimal.valueOf(100.00),
                BigDecimal.valueOf(100.00)
        );

        when(alunoRepository.findById(alunoId)).thenReturn(Optional.empty());

        // Act & Assert
        IllegalArgumentException exception = 
                assertThrows(IllegalArgumentException.class, () -> 
                    pagamentoService.processarPagamento(alunoId, pagamentoDTO));

        assertEquals("Aluno não encontrado", exception.getMessage());

        // Verificar que pagamentoRepository.save() nunca foi chamado
        verify(pagamentoRepository, never()).save(any(Pagamento.class));
    }
}
