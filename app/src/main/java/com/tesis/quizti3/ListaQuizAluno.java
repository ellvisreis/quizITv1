package com.tesis.quizti3;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListaQuizAluno extends AppCompatActivity {

    ListView listView;
    DataBase db = new DataBase(this, "bd_quiz", null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_quiz_aluno);

        //busca as respostas e coloca em itens;
        Cursor cursor = db.getReadableDatabase().rawQuery("SELECT codigo, nome, explicacao FROM quiz ORDER BY codigo", null);

        int ccodigo = cursor.getColumnIndex("codigo");
        int cnome = cursor.getColumnIndex("nome");
        int cexplicacao = cursor.getColumnIndex("explicacao");

        List<String> itens = new ArrayList<String>();






       // ThreeColumn_ListAdapter adapter = new ThreeColumn_ListAdapter(this, R.layout.list_adapter_view, lista);
       // listView = (ListView) findViewById(R.id.lista_respostas_aluno);
    }

}