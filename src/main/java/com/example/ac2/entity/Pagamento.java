package com.example.ac2.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Embedded;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;

@Entity
@Table(name = "pagamento")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "valor", column = @Column(name = "valor_cobrado"))
    })
    private Valor valorCobrado;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "valor", column = @Column(name = "valor_recebido"))
    })
    private Valor valorRecebido;

    public Pagamento() {}

    public Pagamento(Valor valorCobrado, Valor valorRecebido) {
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

    public Valor getValorCobrado() {
        return valorCobrado;
    }

    public void setValorCobrado(Valor valorCobrado) {
        this.valorCobrado = valorCobrado;
    }

    public Valor getValorRecebido() {
        return valorRecebido;
    }

    public void setValorRecebido(Valor valorRecebido) {
        this.valorRecebido = valorRecebido;
    }

    // Método para verificar se o pagamento é válido
    public boolean isPagamentoValido() {
        return valorCobrado.equals(valorRecebido);
    }
}
