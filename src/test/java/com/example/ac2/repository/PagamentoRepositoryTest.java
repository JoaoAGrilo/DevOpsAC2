package com.example.ac2.repository;

import com.example.ac2.entity.Pagamento;
import com.example.ac2.entity.Valor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
public class PagamentoRepositoryTest {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Test
    public void testSalvarERecuperarPagamento() {
        Valor valorCobrado = new Valor(new BigDecimal("100.00"));
        Valor valorRecebido = new Valor(new BigDecimal("100.00"));
        Pagamento pagamento = new Pagamento(valorCobrado, valorRecebido);

        // Salvar o pagamento
        Pagamento pagamentoSalvo = pagamentoRepository.save(pagamento);
        assertNotNull(pagamentoSalvo.getId());

        // Recuperar e verificar
        Pagamento pagamentoRecuperado = pagamentoRepository.findById(pagamentoSalvo.getId()).orElse(null);
        assertNotNull(pagamentoRecuperado);
        assertEquals(valorCobrado, pagamentoRecuperado.getValorCobrado());
        assertEquals(valorRecebido, pagamentoRecuperado.getValorRecebido());
    }
}
