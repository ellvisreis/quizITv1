package com.tesis.quizti3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBase extends SQLiteOpenHelper {

    private static final int VERSAO_BANCO = 1;
    private static final String BANCO_QUIZ = "bd_quiz";

    public DataBase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, BANCO_QUIZ, factory, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //DROPA TODAS AS TABELAS ANTES DE TENTAR CRIAR DE NOVO
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS login");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS quiz");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS pergunta_quiz");

        //CRIA TABELA DE LOGIN
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS login(nome VARCHAR, email VARCHAR, senha VARCHAR, perfil INT(1))");
        //POPULA TABELA DE LOGIN
        addLogins(sqLiteDatabase);


        //CRIA TABELA DE QUIZ
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS quiz(codigo INT(9), nome VARCHAR, explicacao VARCHAR)");

        //CRIA TABELA DE PERGUNTAS DO QUIZ
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS pergunta_quiz(codigo int(9) primary key, codigo_quiz INT(9), pergunta VARCHAR, certa VARCHAR, errada1 VARCHAR, errada2 VARCHAR, errada3 VARCHAR, errada4 VARCHAR)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    void addLogins(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL("INSERT INTO login(nome, email, senha, perfil) VALUES('Ellvis', 'aluno@email.com', '12345', 1)");
        sqLiteDatabase.execSQL("INSERT INTO login(nome, email, senha, perfil) VALUES('Ellvis professor','professor@email.com', '12345', 2)");
    }
}