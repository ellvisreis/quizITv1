package com.tesis.quizti3;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBase extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "quiz";
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

        //CRIA TABELA DE RESPOSTAS DO QUIZ
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS resposta_quiz(codigo int(9) primary key, codigo_quiz INT(9), codigo_pergunta INT(9), resposta VARCHAR)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    void addLogins(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL("INSERT INTO login(nome, email, senha, perfil) VALUES('Ellvis aluno', 'aluno@email.com', '12345', 1)");
        sqLiteDatabase.execSQL("INSERT INTO login(nome, email, senha, perfil) VALUES('Ellvis professor','professor@email.com', '12345', 2)");
    }

    public Cursor getListContents() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;
    }

    public Cursor getListContentsPerguntaQuiz(Integer codigo_quiz) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = db.rawQuery("SELECT codigo_quiz, pergunta, certa, errada1, errada2, errada3, errada4 FROM pergunta_quiz where codigo_quiz="+ codigo_quiz + " ORDER BY codigo_quiz", null);
        return data;
    }

    public Cursor getListContentsAlunoQuiz() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = db.rawQuery("SELECT (select nome from quiz q where q.codigo = rq.codigo_quiz) as nome, (select pergunta from pergunta_quiz pq where pq.codigo_quiz = rq.codigo_quiz order by codigo asc limit 1  ) as pergunta, (select count(1) from pergunta_quiz pq where pq.codigo = rq.codigo_pergunta and pq.certa = rq.resposta) as certa FROM resposta_quiz rq ORDER BY codigo_quiz", null);
        return data;
    }
}