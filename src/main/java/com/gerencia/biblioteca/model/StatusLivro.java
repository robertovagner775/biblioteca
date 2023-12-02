package com.gerencia.biblioteca.model;


public enum StatusLivro {

    DISPONIVEL("disponivel"),
    EMPRESTADO("emprestado");
  

    private String descricao;

    StatusLivro(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return this.descricao;
    }
}
