package com.tesis.quizti3;

public class quiz {
    private String Codigo;
    private String Nome;
    private String Explicacao;

    public quiz(String lcodigo, String lnome, String lexplicacao){
        Codigo = lcodigo;
        Nome = lnome;
        Explicacao = lexplicacao;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getExplicacao() {
        return Explicacao;
    }

    public void setExplicacao(String explicacao) {
        Explicacao = explicacao;
    }
}