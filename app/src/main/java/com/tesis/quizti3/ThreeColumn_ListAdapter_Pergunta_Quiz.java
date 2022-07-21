package com.tesis.quizti3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ThreeColumn_ListAdapter_Pergunta_Quiz extends ArrayAdapter<PerguntaQuiz> {

    private LayoutInflater mInflater;
    private ArrayList<PerguntaQuiz> PerguntaQuizs;
    private int mViewResourceId;

    public ThreeColumn_ListAdapter_Pergunta_Quiz(Context context, int textViewResourceId, ArrayList<PerguntaQuiz> PerguntaQuizs) {
        super(context, textViewResourceId, PerguntaQuizs);
        this.PerguntaQuizs = PerguntaQuizs;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(mViewResourceId, null);

        PerguntaQuiz PerguntaQuiz = PerguntaQuizs.get(position);

        if (PerguntaQuiz != null) {

            TextView lista_pergunta_quiz_col1 = (TextView) convertView.findViewById(R.id.lista_pergunta_quiz_col1);
            TextView lista_pergunta_quiz_col2 = (TextView) convertView.findViewById(R.id.lista_pergunta_quiz_col2);
            TextView lista_pergunta_quiz_col3 = (TextView) convertView.findViewById(R.id.lista_pergunta_quiz_col3);
            if (lista_pergunta_quiz_col1 != null) {
                lista_pergunta_quiz_col1.setText(PerguntaQuiz.getCodigoQuiz());
            }
            if (lista_pergunta_quiz_col2 != null) {
                lista_pergunta_quiz_col2.setText((PerguntaQuiz.getPergunta()));
            }
            if (lista_pergunta_quiz_col3 != null) {
                lista_pergunta_quiz_col3.setText((PerguntaQuiz.getCerta()));
            }
        }

        return convertView;
    }
}
