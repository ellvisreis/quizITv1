package com.tesis.quizti3;

public class PerguntaQuiz {
    private String CodigoQuiz;
    private String Pergunta;
    private String Certa;

    public PerguntaQuiz(String lcodigo, String lpergunta, String lcerta){
        CodigoQuiz = lcodigo;
        Pergunta = lpergunta;
        Certa = lcerta;
    }

    public String getCodigoQuiz() {
        return CodigoQuiz;
    }

    public void setCodigoQuiz(String codigo) {
        CodigoQuiz = codigo;
    }

    public String getPergunta() {
        return Pergunta;
    }

    public void setPergunta(String nome) {
        Pergunta = nome;
    }

    public String getCerta() {
        return Certa;
    }

    public void setCerta(String explicacao) {
        Certa = explicacao;
    }
}
