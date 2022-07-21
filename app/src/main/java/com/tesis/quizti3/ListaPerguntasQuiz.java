package com.tesis.quizti3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

public class ListaPerguntasQuiz extends AppCompatActivity {

    public static final String ID_QUIZ = "com.tesis.quizti3.ID_QUIZ";
    private ListView list_perguntas_quiz;


    DataBase db = new DataBase(this, "bd_quiz", null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_quiz);

        list_perguntas_quiz = findViewById(R.id.lista_quiz);
        SharedPreferences prefs = getSharedPreferences("shared_quiz_data",   Context.MODE_PRIVATE);
        String codigoQuiz = prefs.getString("codigo_quiz", ""); // prefs.getString("nombre del campo" , "valor por defecto")
        Toast.makeText(ListaPerguntasQuiz.this, "QUIZ2: " + codigoQuiz, Toast.LENGTH_LONG).show();

        //busca os perguntas quiz e coloca em itens;
        Cursor cursor = db.getReadableDatabase().rawQuery("SELECT codigo_quiz, pergunta, certa, errada1, errada2, errada3, errada4 FROM pergunta_quiz where codigo_quiz="+ codigoQuiz + " ORDER BY codigo_quiz", null);

        int ccodigo = cursor.getColumnIndex("codigo_quiz");
        int cnome = cursor.getColumnIndex("pergunta");
        int cexplicacao = cursor.getColumnIndex("explicacao");

        List<String> itens = new ArrayList<String>();
        List<Integer> idItens = new ArrayList<Integer>();

        try {
            cursor.moveToFirst();
            if (cursor != null && cursor.getCount() > 0) {
                while(cursor.moveToNext()) {
                    Toast.makeText(ListaPerguntasQuiz.this, "PERGUNTA: " + cursor.getString(cnome), Toast.LENGTH_LONG).show();

                    itens.add(cursor.getString(cnome));
                    idItens.add(cursor.getInt(ccodigo));
                }
            } else {
                itens.add("Nenhum pergunta cadastrada");
            }
        } finally {
            cursor.close();
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_2,
                android.R.id.text1, itens
        );
        list_perguntas_quiz.setAdapter(adapter);

        //click da lista de quiz
        list_perguntas_quiz.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                //Toast.makeText(ListaQuiz.this, "QUIZ: " + itens.get(position), Toast.LENGTH_LONG).show();
                SharedPreferences prefs = getSharedPreferences("shared_login_data",   Context.MODE_PRIVATE);
                int perfsInt = prefs.getInt("perfil", 0); // prefs.getString("nombre del campo" , "valor por defecto")

                if (perfsInt == '2') {
                    Intent intent = new Intent(ListaPerguntasQuiz.this, FormPerguntaAluno.class);
                    intent.putExtra(ID_QUIZ, idItens.get(position));
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(ListaPerguntasQuiz.this, FormPergunta.class);
                    intent.putExtra(ID_QUIZ, idItens.get(position));
                    startActivity(intent);

                }
            }
        });
    }


}