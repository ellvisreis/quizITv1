package com.tesis.quizti3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ThreeColumn_ListAdapter extends ArrayAdapter<quiz> {

    private LayoutInflater mInflater;
    private ArrayList<quiz> quizs;
    private int mViewResourceId;

    public ThreeColumn_ListAdapter(Context context, int textViewResourceId, ArrayList<quiz> quizs) {
        super(context, textViewResourceId, quizs);
        this.quizs = quizs;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(mViewResourceId, null);

        quiz quiz = quizs.get(position);

        if (quiz != null) {

            TextView lista_quiz_col1 = (TextView) convertView.findViewById(R.id.lista_quiz_col1);
            TextView lista_quiz_col2 = (TextView) convertView.findViewById(R.id.lista_quiz_col2);
            TextView lista_quiz_col3 = (TextView) convertView.findViewById(R.id.lista_quiz_col3);
            if (lista_quiz_col1 != null) {
                lista_quiz_col1.setText(quiz.getCodigo());
            }
            if (lista_quiz_col2 != null) {
                lista_quiz_col2.setText((quiz.getNome()));
            }
            if (lista_quiz_col3 != null) {
                lista_quiz_col3.setText((quiz.getExplicacao()));
            }
        }

        return convertView;
    }
}
