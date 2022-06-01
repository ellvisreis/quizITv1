package com.tesis.quizti3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    private TextView id_formulario_quiz;
    private TextView id_pergunta;
    private TextView id_form_pergunta_aluno;
    private TextView id_lista_quiz;
    private TextView id_lista_pergunta_quiz;
    private TextView id_lista_quiz_aluno;
    private TextView id_lista_aluno_quiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        IniciarComponentes();


        id_formulario_quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Home.this, FormQuiz.class);
                startActivity(intent);
            }
        });



        id_pergunta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Home.this, FormPergunta.class);
                startActivity(intent);
            }
        });

        id_form_pergunta_aluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Home.this, FormPerguntaAluno.class);
                startActivity(intent);
            }
        });

        id_lista_quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Home.this, ListaQuiz.class);
                startActivity(intent);
            }
        });

        id_lista_pergunta_quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Home.this, ListaPerguntasQuiz.class);
                startActivity(intent);
            }
        });

        id_lista_quiz_aluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Home.this, ListaQuizAluno.class);
                startActivity(intent);
            }
        });

        id_lista_aluno_quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Home.this, ListaAlunoQuiz.class);
                startActivity(intent);
            }
        });


    }

    private void IniciarComponentes(){

        id_formulario_quiz = findViewById(R.id.id_formulario_quiz);
        id_pergunta = findViewById(R.id.id_pergunta);
        id_form_pergunta_aluno = findViewById(R.id.id_form_pergunta_aluno);
        id_lista_quiz = findViewById(R.id.id_lista_quiz);
        id_lista_pergunta_quiz = findViewById(R.id.id_lista_pergunta_quiz);
        id_lista_quiz_aluno = findViewById(R.id.id_lista_quiz_aluno);
        id_lista_aluno_quiz = findViewById(R.id.id_lista_aluno_quiz);












    }
}