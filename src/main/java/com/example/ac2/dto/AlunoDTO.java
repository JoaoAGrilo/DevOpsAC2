package com.example.ac2.dto;

import com.example.ac2.entity.Aluno;

public class AlunoDTO {

    private Long id;
    private String nome;
    private boolean acessoLiberado;

    // Construtor vazio
    public AlunoDTO() {}

    // Construtor com parâmetros
    public AlunoDTO(Long id, String nome, boolean acessoLiberado) {
        this.id = id;
        this.nome = nome;
        this.acessoLiberado = acessoLiberado;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isAcessoLiberado() {
        return acessoLiberado;
    }

    public void setAcessoLiberado(boolean acessoLiberado) {
        this.acessoLiberado = acessoLiberado;
    }

    // Método de conversão de entidade para DTO
    public static AlunoDTO fromEntity(Aluno aluno) {
        return new AlunoDTO(
                aluno.getId(),
                aluno.getNome(),
                aluno.isAcessoLiberado()
        );
    }
}
