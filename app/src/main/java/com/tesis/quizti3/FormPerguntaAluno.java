package com.tesis.quizti3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class FormPerguntaAluno extends AppCompatActivity {

    private TextView titulo_lista_aluno_quiz;
    private RadioButton radio_resposta1;
    private RadioButton radio_resposta2;
    private RadioButton radio_resposta3;
    private RadioButton radio_resposta4;
    private RadioButton radio_resposta5;
    private RadioGroup radio_group_resposta;
    private Button registrar_pergunta;

    private Random randomGenerator;

    DataBase db = new DataBase(this, "bd_quiz", null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_pergunta_aluno);

        InicializarComponentes();

        Intent intent = getIntent();
        int idQuiz = intent.getIntExtra(ListaQuiz.ID_QUIZ, 0);


        //Toast.makeText(FormPerguntaAluno.this, "QUIZ: " + idQuiz, Toast.LENGTH_LONG).show();

        //pegar as perguntas do quiz e colocar as opções na tela
        Cursor cursor = db.getReadableDatabase().rawQuery("SELECT codigo, codigo_quiz, pergunta, certa, errada1, errada2, errada3, errada4 FROM pergunta_quiz WHERE codigo_quiz = " + idQuiz + " ORDER BY codigo", null);

        int ccodigo = cursor.getColumnIndex("codigo");
        int ccodigo_quiz = cursor.getColumnIndex("codigo_quiz");
        int cpergunta = cursor.getColumnIndex("pergunta");
        int ccerta = cursor.getColumnIndex("certa");
        int cerrada1 = cursor.getColumnIndex("errada1");
        int cerrada2 = cursor.getColumnIndex("errada2");
        int cerrada3 = cursor.getColumnIndex("errada3");
        int cerrada4 = cursor.getColumnIndex("errada4");

        try {
            cursor.moveToFirst();
            if (cursor != null && cursor.getCount() > 0) {

                    int codigo = cursor.getInt(ccodigo);
                    int codigo_quiz = cursor.getInt(ccodigo_quiz);

                    //Toast.makeText(FormPerguntaAluno.this, "PERGUNTA: " + cursor.getString(cpergunta), Toast.LENGTH_LONG).show();
                    titulo_lista_aluno_quiz.setText(cursor.getString(cpergunta)+"?");

                    ArrayList<String> respostas = new ArrayList<String>();

                    respostas.add(cursor.getString(ccerta));
                    respostas.add(cursor.getString(cerrada1));
                    respostas.add(cursor.getString(cerrada2));
                    respostas.add(cursor.getString(cerrada3));
                    respostas.add(cursor.getString(cerrada4));

                    Collections.shuffle(respostas);

                    radio_resposta1.setText(respostas.get(0));
                    radio_resposta2.setText(respostas.get(1));
                    radio_resposta3.setText(respostas.get(2));
                    radio_resposta4.setText(respostas.get(3));
                    radio_resposta5.setText(respostas.get(4));

                    registrar_pergunta.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(FormPerguntaAluno.this, "Pergunta respondida", Toast.LENGTH_LONG).show();

                            int index = radio_group_resposta.getCheckedRadioButtonId();
                            View radioButton = radio_group_resposta.findViewById(index);
                            int idx = radio_group_resposta.indexOfChild(radioButton);
                            RadioButton r = (RadioButton) radio_group_resposta.getChildAt(idx);
                            String selectedText = r.getText().toString();

                            //SALVA A RESPOSTA
                            //Toast.makeText(FormPerguntaAluno.this, "Respondido: " + codigo_quiz + "," + codigo  + "," + selectedText, Toast.LENGTH_LONG).show();
                            db.getWritableDatabase().execSQL("INSERT INTO resposta_quiz(codigo_quiz, codigo_pergunta, resposta) VALUES("+codigo_quiz+","+codigo+", '"+selectedText+"')");

                        }
                    });
            } else {
                Toast.makeText(FormPerguntaAluno.this, "Não existem perguntas para esse quiz", Toast.LENGTH_LONG).show();
            }
        } finally {
            cursor.close();
        }



    }

    private void InicializarComponentes(){
        registrar_pergunta = findViewById(R.id.registrar_pergunta);
        titulo_lista_aluno_quiz = findViewById(R.id.titulo_lista_aluno_quiz);
        radio_group_resposta = findViewById(R.id.radio_group_resposta);
        radio_resposta1 = findViewById(R.id.radio_resposta1);
        radio_resposta2 = findViewById(R.id.radio_resposta2);
        radio_resposta3 = findViewById(R.id.radio_resposta3);
        radio_resposta4 = findViewById(R.id.radio_resposta4);
        radio_resposta5 = findViewById(R.id.radio_resposta5);

    }
}