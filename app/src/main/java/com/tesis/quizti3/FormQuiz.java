package com.tesis.quizti3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FormQuiz extends AppCompatActivity {

    DataBase db = new DataBase(this, "bd_quiz", null, 1);
    private Button btn_incluir_quiz;
    private EditText edit_codigo_quiz;
    private EditText edit_nome_quiz;
    private EditText edit_subtitulo_quiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_quiz);
        IniciarComponentes();

        btn_incluir_quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //inserir dados
                String codigo = edit_codigo_quiz.getText().toString();
                String nome = edit_nome_quiz.getText().toString();
                String explicacao = edit_subtitulo_quiz.getText().toString();

                db.getReadableDatabase().execSQL("INSERT INTO quiz(codigo, nome, explicacao) VALUES ("+ codigo +",'"+ nome +"', '"+ explicacao +"')");

                Toast.makeText(FormQuiz.this,
                        "Quiz registrado com sucesso.", Toast.LENGTH_LONG).show();

                //limpar campos
                edit_codigo_quiz.setText("");
                edit_nome_quiz.setText("");
                edit_subtitulo_quiz.setText("");
            }
        });
    }

    private void IniciarComponentes() {

        btn_incluir_quiz = findViewById(R.id.btn_incluir_quiz);
        edit_codigo_quiz = findViewById(R.id.edit_codigo_quiz);
        edit_nome_quiz = findViewById(R.id.edit_nome_quiz);
        edit_subtitulo_quiz = findViewById(R.id.edit_subtitulo_quiz);

    }


}