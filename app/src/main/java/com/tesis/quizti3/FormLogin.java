package com.tesis.quizti3;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FormLogin extends AppCompatActivity {

    private TextView text_tela_cadastro;
    private TextView entrar;
    EditText email;
    EditText senha;

    DataBase db = new DataBase(this, "bd_quiz", null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_login);

        email =  (EditText) findViewById(R.id.edit_email);
        senha =  (EditText) findViewById(R.id.edit_senha);

        getSupportActionBar().hide();
        IniciarComponentes();

        text_tela_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent intent = new Intent(FormLogin.this, FormCadastro.class);
                    startActivity(intent);
            }
        });

        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String semail = email.getText().toString().trim();
                String ssenha = senha.getText().toString().trim();
                Intent intent;

                if (!TextUtils.isEmpty(semail)) {

                    try {

                        Cursor cursor = db.getReadableDatabase().rawQuery("SELECT email, perfil FROM login WHERE email = '" + semail + "' and senha = '" + ssenha + "'", null);

                        int cemail = cursor.getColumnIndex("email");
                        int cperfil = cursor.getColumnIndex("perfil");

                        cursor.moveToFirst();

                        if (cursor != null && cursor.getCount() == 1) {
                            if (cursor.getInt(cperfil) == 1) {
                                intent = new Intent(FormLogin.this, ListaQuizAluno.class);
                                startActivity(intent);
                                return;
                            } else if (cursor.getInt(cperfil) == 2) {
                                intent = new Intent(FormLogin.this, Home.class);
                                startActivity(intent);
                                return;
                            } else {
                                email.setError("Usuario invalido.");
                            }
                        }
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }else {

                    Toast.makeText(FormLogin.this,
                            "Informações obrigatorias.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }



    private void IniciarComponentes(){

        text_tela_cadastro = findViewById(R.id.text_tela_cadastro);
        entrar = findViewById(R.id.btn_incluir_quiz);


    }
}