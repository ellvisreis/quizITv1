package com.tesis.quizti3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ThreeColumn_ListAdapter_Aluno_Quiz extends ArrayAdapter<AlunoQuiz> {

        private LayoutInflater mInflater;
        private ArrayList<AlunoQuiz> AlunoQuizs;
        private int mViewResourceId;

    public ThreeColumn_ListAdapter_Aluno_Quiz(Context context, int textViewResourceId, ArrayList<AlunoQuiz> AlunoQuizs) {
            super(context, textViewResourceId, AlunoQuizs);
            this.AlunoQuizs = AlunoQuizs;
            mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mViewResourceId = textViewResourceId;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = mInflater.inflate(mViewResourceId, null);

            AlunoQuiz AlunoQuiz = AlunoQuizs.get(position);

            if (AlunoQuiz != null) {

                TextView lista_aluno_quiz_col1 = (TextView) convertView.findViewById(R.id.lista_aluno_quiz_col1);
                TextView lista_aluno_quiz_col2 = (TextView) convertView.findViewById(R.id.lista_aluno_quiz_col2);
                TextView lista_aluno_quiz_col3 = (TextView) convertView.findViewById(R.id.lista_aluno_quiz_col3);
                if (lista_aluno_quiz_col1 != null) {
                    lista_aluno_quiz_col1.setText(AlunoQuiz.getNomeQuiz());
                }
                if (lista_aluno_quiz_col2 != null) {
                    lista_aluno_quiz_col2.setText((AlunoQuiz.getPergunta()));
                }
                if (lista_aluno_quiz_col3 != null) {
                    lista_aluno_quiz_col3.setText((AlunoQuiz.getCerta()));
                }
            }

            return convertView;
        }
    }

