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

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ViewListContents_PerguntaQuiz extends AppCompatActivity {

    public static final String ID_QUIZ = "com.tesis.quizti3.ID_QUIZ";
    public static final String PERGUNTA_QUIZ = "com.tesis.quizti3.NOME_QUIZ";
    DataBase db = new DataBase(this, "bd_quiz", null, 1);
    ArrayList<PerguntaQuiz> PerguntaQuizList;
    ListView listView;
    PerguntaQuiz PerguntaQuiz;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewcontents_layout);

        // db = new Database(this);
        SharedPreferences prefs = getSharedPreferences("shared_quiz_data",   Context.MODE_PRIVATE);
        String codigoQuiz = prefs.getString("codigo_quiz", "");
        //Toast.makeText(ViewListContents_PerguntaQuiz.this, "QUIZ2: " + codigoQuiz, Toast.LENGTH_LONG).show();

        PerguntaQuizList = new ArrayList<>();
        Cursor data = db.getListContentsPerguntaQuiz(Integer.valueOf(codigoQuiz));
        int numRows = data.getCount();

       // System.out.println(numRows);
        if(numRows == 0){
            Toast.makeText(ViewListContents_PerguntaQuiz.this,"Sem registros :(.",Toast.LENGTH_LONG).show();
        }else{
            int i=1;
            PerguntaQuiz = new PerguntaQuiz("Código Quiz","Pergunta","Resposta Certa");
            PerguntaQuizList.add(0,PerguntaQuiz);
            while(data.moveToNext()){
                PerguntaQuiz = new PerguntaQuiz(String.valueOf(data.getInt(0)),data.getString(1),data.getString(2));
                PerguntaQuizList.add(i,PerguntaQuiz);
              //  System.out.println(String.valueOf(data.getInt(0)) +" "+data.getString(1)+" "+data.getString(2));
              //  System.out.println(PerguntaQuizList.get(i).getPergunta());
                i++;
            }
            ThreeColumn_ListAdapter_Pergunta_Quiz adapter =  new ThreeColumn_ListAdapter_Pergunta_Quiz(this,R.layout.list_adapter_view_pergunta_quiz, PerguntaQuizList);
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

                if (perfsInt == 2) {
                    Intent intent = new Intent(ViewListContents_PerguntaQuiz.this, FormPerguntaAluno.class);
                    intent.putExtra(ID_QUIZ, Integer.valueOf(PerguntaQuizList.get(position).getCodigoQuiz()));
                    intent.putExtra(PERGUNTA_QUIZ, PerguntaQuizList.get(position).getPergunta());
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(ViewListContents_PerguntaQuiz.this, FormPergunta.class);
                    intent.putExtra(ID_QUIZ, Integer.valueOf(PerguntaQuizList.get(position).getCodigoQuiz()));
                    intent.putExtra(PERGUNTA_QUIZ, PerguntaQuizList.get(position).getPergunta());
                    startActivity(intent);
                }
            }
        });
    }
}
