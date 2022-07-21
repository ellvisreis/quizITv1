package com.tesis.quizti3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FormPergunta extends AppCompatActivity {

    private Button btn_registrar_pergunta;
    private Button btn_listar_pergunta;
    private EditText edit_codigo_quiz;
    private EditText edit_pergunta_quiz;
    private EditText edit_resposta_correta;
    private EditText edit_resposta_errada1;
    private EditText edit_resposta_errada2;
    private EditText edit_resposta_errada3;
    private EditText edit_resposta_errada4;
    public static final String ID_QUIZ = "com.tesis.quizti3.ID_QUIZ";

    DataBase db = new DataBase(this, "bd_quiz", null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_pergunta);
        IniciarComponentes();

        Intent intent = getIntent();
        int idQuiz = intent.getIntExtra(ViewListContents.ID_QUIZ, 0);
        edit_codigo_quiz.setText(idQuiz+"");


        btn_listar_pergunta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String codigo_quiz = edit_codigo_quiz.getText().toString();
               // Toast.makeText(FormPergunta.this, "QUIZ: " + codigo_quiz, Toast.LENGTH_LONG).show();

                SharedPreferences prefs = getSharedPreferences("shared_quiz_data",   Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("codigo_quiz", String.valueOf(idQuiz));
                editor.commit();

                Intent intent = new Intent(FormPergunta.this, ViewListContents_PerguntaQuiz.class);
                intent.putExtra(ID_QUIZ, codigo_quiz);
                startActivity(intent);

            }
        });

        btn_registrar_pergunta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //inserir dados
                String codigo_quiz = edit_codigo_quiz.getText().toString();
                String pergunta = edit_pergunta_quiz.getText().toString();
                String respostaCorreta = edit_resposta_correta.getText().toString();
                String respostaErrada1 = edit_resposta_errada1.getText().toString();
                String respostaErrada2 = edit_resposta_errada2.getText().toString();
                String respostaErrada3 = edit_resposta_errada3.getText().toString();
                String respostaErrada4 = edit_resposta_errada4.getText().toString();

                db.getReadableDatabase().execSQL("INSERT INTO pergunta_quiz(codigo_quiz, pergunta, certa, errada1, errada2, errada3, errada4 ) VALUES ("+ codigo_quiz +",'"+ pergunta +"', '"+ respostaCorreta + "','" + respostaErrada1 + "','" + respostaErrada2 + "','" + respostaErrada3 + "','" + respostaErrada4 + "')");

                Toast.makeText(FormPergunta.this,
                        "Pergunta registrada com sucesso.", Toast.LENGTH_LONG).show();

                //limpar campos
                edit_codigo_quiz.setText("");
                edit_pergunta_quiz.setText("");
                edit_resposta_correta.setText("");
                edit_resposta_errada1.setText("");
                edit_resposta_errada2.setText("");
                edit_resposta_errada3.setText("");
                edit_resposta_errada4.setText("");
            }
        });
    }

    private void IniciarComponentes() {
        btn_registrar_pergunta = findViewById(R.id.registrar_pergunta);
        btn_listar_pergunta = findViewById(R.id.lista_pergunta);
        edit_codigo_quiz = findViewById(R.id.edit_codigo_quiz_pergunta);
        edit_pergunta_quiz = findViewById(R.id.edit_pergunta_quiz);
        edit_resposta_correta = findViewById(R.id.edit_resposta_correta);
        edit_resposta_errada1 = findViewById(R.id.edit_resposta_errada1);
        edit_resposta_errada2 = findViewById(R.id.edit_resposta_errada2);
        edit_resposta_errada3 = findViewById(R.id.edit_resposta_errada3);
        edit_resposta_errada4 = findViewById(R.id.edit_resposta_errada4);




    }
}