package com.tesis.quizti3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ViewListContents extends AppCompatActivity {

    public static final String ID_QUIZ = "com.tesis.quizti3.ID_QUIZ";
    public static final String NOME_QUIZ = "com.tesis.quizti3.NOME_QUIZ";
    DataBase db = new DataBase(this, "bd_quiz", null, 1);
    ArrayList<quiz> quizList;
    ListView listView;
    quiz quiz;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewcontents_layout);


        quizList = new ArrayList<>();
        Cursor data = db.getListContents();
        int numRows = data.getCount();
        if(numRows == 0){
            Toast.makeText(ViewListContents.this,"Sem registros :(.",Toast.LENGTH_LONG).show();
        }else{
            int i=1;
            quiz = new quiz("Código","Nome Quiz","Explicação");
            quizList.add(0,quiz);
            while(data.moveToNext()){
                quiz = new quiz(String.valueOf(data.getInt(0)),data.getString(1),data.getString(2));
                quizList.add(i,quiz);
              //  System.out.println(String.valueOf(data.getInt(0)) +" "+data.getString(1)+" "+data.getString(2));
             //   System.out.println(quizList.get(i).getNome());
                i++;
            }
            ThreeColumn_ListAdapter adapter =  new ThreeColumn_ListAdapter(this,R.layout.list_adapter_view, quizList);
            listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(adapter);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
               // System.out.println(position);
               // System.out.println(quizList.get(position).getCodigo());
                //Toast.makeText(ListaQuiz.this, "QUIZ: " + itens.get(position), Toast.LENGTH_LONG).show();
                SharedPreferences prefs = getSharedPreferences("shared_login_data",   Context.MODE_PRIVATE);
                int perfsInt = prefs.getInt("perfil", 0);
                //System.out.println(perfsInt);
                if (perfsInt == 1) {
                    Intent intent = new Intent(ViewListContents.this, FormPerguntaAluno.class);
                    intent.putExtra(ID_QUIZ, Integer.valueOf(quizList.get(position).getCodigo()));
                    intent.putExtra(NOME_QUIZ, quizList.get(position).getNome());
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(ViewListContents.this, FormPergunta.class);
                    intent.putExtra(ID_QUIZ, Integer.valueOf(quizList.get(position).getCodigo()));
                    intent.putExtra(NOME_QUIZ, quizList.get(position).getNome());
                    startActivity(intent);
                }
            }
        });
    }
}