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

public class ListaQuiz extends AppCompatActivity {

    public static final String ID_QUIZ = "com.tesis.quizti3.ID_QUIZ";
    private ListView list_quiz;


    DataBase db = new DataBase(this, "bd_quiz", null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_quiz);

        list_quiz = findViewById(R.id.lista_quiz);

        //busca os quiz e coloca em itens;
        Cursor cursor = db.getReadableDatabase().rawQuery("SELECT codigo, nome, explicacao FROM quiz ORDER BY codigo", null);

        int ccodigo = cursor.getColumnIndex("codigo");
        int cnome = cursor.getColumnIndex("nome");
        int cexplicacao = cursor.getColumnIndex("explicacao");

        List<String> itens = new ArrayList<String>();
        List<Integer> idItens = new ArrayList<Integer>();

        try {
            cursor.moveToFirst();
            if (cursor != null && cursor.getCount() > 0) {
                do {
                    itens.add(cursor.getString(cnome));
                    idItens.add(cursor.getInt(ccodigo));
                } while(cursor.moveToNext());
            } else {
                itens.add("Nenhum quiz cadastrado");
            }
        } finally {
            cursor.close();
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_2,
                android.R.id.text1, itens
        );
        list_quiz.setAdapter(adapter);

        //click da lista de quiz
        list_quiz.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                //Toast.makeText(ListaQuiz.this, "QUIZ: " + itens.get(position), Toast.LENGTH_LONG).show();
                SharedPreferences prefs = getSharedPreferences("shared_login_data",   Context.MODE_PRIVATE);
                int perfsInt = prefs.getInt("perfil", 0);

                if (perfsInt == 1) {
                    Intent intent = new Intent(ListaQuiz.this, FormPerguntaAluno.class);
                    intent.putExtra(ID_QUIZ, idItens.get(position));
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(ListaQuiz.this, FormPergunta.class);
                    intent.putExtra(ID_QUIZ, idItens.get(position));
                    startActivity(intent);
                }
            }
        });
    }


}