package com.example.ac2.entity;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class PagamentoTest {

    @Test
    public void testPagamentoValido() {
        Valor valorCobrado = new Valor(new BigDecimal("100.00"));
        Valor valorRecebido = new Valor(new BigDecimal("100.00"));
        Pagamento pagamento = new Pagamento(valorCobrado, valorRecebido);

        assertTrue(pagamento.isPagamentoValido(), "O pagamento deveria ser válido quando os valores são iguais.");
    }

    @Test
    public void testPagamentoInvalido() {
        Valor valorCobrado = new Valor(new BigDecimal("100.00"));
        Valor valorRecebido = new Valor(new BigDecimal("50.00"));
        Pagamento pagamento = new Pagamento(valorCobrado, valorRecebido);

        assertFalse(pagamento.isPagamentoValido(), "O pagamento deveria ser inválido quando os valores são diferentes.");
    }

    @Test
    public void testValorEquals() {
        Valor valor1 = new Valor(new BigDecimal("100.00"));
        Valor valor2 = new Valor(new BigDecimal("100.00"));
        Valor valor3 = new Valor(new BigDecimal("50.00"));

        assertEquals(valor1, valor2, "Valores iguais devem ser considerados equivalentes.");
        assertNotEquals(valor1, valor3, "Valores diferentes não devem ser considerados equivalentes.");
    }
}
