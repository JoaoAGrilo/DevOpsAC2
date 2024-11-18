package com.example.ac2.dto;

import com.example.ac2.entity.Pagamento;

import java.math.BigDecimal;

public class PagamentoDTO {

    private Long id;
    private BigDecimal valorCobrado;
    private BigDecimal valorRecebido;

    // Construtor vazio
    public PagamentoDTO() {}

    // Construtor com parâmetros
    public PagamentoDTO(Long id, BigDecimal valorCobrado, BigDecimal valorRecebido) {
        this.id = id;
        this.valorCobrado = valorCobrado;
        this.valorRecebido = valorRecebido;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValorCobrado() {
        return valorCobrado;
    }

    public void setValorCobrado(BigDecimal valorCobrado) {
        this.valorCobrado = valorCobrado;
    }

    public BigDecimal getValorRecebido() {
        return valorRecebido;
    }

    public void setValorRecebido(BigDecimal valorRecebido) {
        this.valorRecebido = valorRecebido;
    }

    // Método de conversão de entidade para DTO
    public static PagamentoDTO fromEntity(Pagamento pagamento) {
        return new PagamentoDTO(
                pagamento.getId(),
                pagamento.getValorCobrado().getValor(),
                pagamento.getValorRecebido().getValor()
        );
    }
}
