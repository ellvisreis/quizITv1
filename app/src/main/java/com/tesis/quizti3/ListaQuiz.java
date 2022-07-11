package com.tesis.quizti3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListaQuiz extends AppCompatActivity {

    private ListView list_quiz;

    private  String[] itens = {
        "Java", "Java 2", "PHP", "PHP 2"

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_quiz);

        list_quiz = findViewById(R.id.lista_quiz);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1, itens


        );
        list_quiz.setAdapter(adapter);
    }
}