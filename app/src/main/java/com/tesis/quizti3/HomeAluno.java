package com.tesis.quizti3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeAluno extends AppCompatActivity {

    private TextView id_lista_quiz;
    private TextView id_lista_quiz_aluno;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homealuno);
        IniciarComponentes();

        id_lista_quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(HomeAluno.this, ViewListContents.class);
                startActivity(intent);
            }
        });



        id_lista_quiz_aluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(HomeAluno.this, ViewListContents_AlunoQuiz.class);
                startActivity(intent);
            }
        });




    }

    private void IniciarComponentes(){
        id_lista_quiz = findViewById(R.id.id_lista_quiz);
        id_lista_quiz_aluno = findViewById(R.id.id_lista_quiz_aluno);

    }
}