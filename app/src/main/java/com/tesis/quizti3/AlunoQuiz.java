package com.tesis.quizti3;

public class AlunoQuiz {

    private String NomeQuiz;
    private String Pergunta;
    private String Certa;

    public AlunoQuiz(String lnomequiz, String lpergunta, String lcerta){
        NomeQuiz = lnomequiz;
        Pergunta = lpergunta;
        Certa = lcerta;
    }

    public String getNomeQuiz() {
        return NomeQuiz;
    }

    public void setNomeQuiz(String nomequiz) {
        NomeQuiz = nomequiz;
    }

    public String getPergunta() {
        return Pergunta;
    }

    public void setPergunta(String pergunta) {
        Pergunta = pergunta;
    }

    public String getCerta() {
        return Certa;
    }

    public void setCerta(String certa) {
        Certa = certa;
    }
}
