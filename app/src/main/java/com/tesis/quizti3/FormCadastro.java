package com.tesis.quizti3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FormCadastro extends AppCompatActivity {


    private Button btn_incluir_usuario;
    private EditText edit_nome;
    private EditText edit_email;
    private EditText edit_senha;
    DataBase db = new DataBase(this, "bd_quiz", null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_cadastro);
        IniciarComponentes();


        btn_incluir_usuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //inserir dados
                String nome = edit_nome.getText().toString();
                String email = edit_email.getText().toString();
                String senha = edit_senha.getText().toString();

                db.getReadableDatabase().execSQL("INSERT INTO login(email, senha, perfil) VALUES ('"+ email +"', '"+ senha + "',1)");
                Toast.makeText(FormCadastro.this,
                        "Usuario registrado com sucesso.", Toast.LENGTH_LONG).show();
                //limpar campos
                edit_nome.setText("");
                edit_email.setText("");
                edit_senha.setText("");

            }
        });

        getSupportActionBar().hide();
    }

    private void IniciarComponentes() {
        btn_incluir_usuario = findViewById(R.id.btn_incluir_usuario);
        edit_nome = findViewById(R.id.edit_nome);
        edit_email = findViewById(R.id.edit_email);
        edit_senha = findViewById(R.id.edit_senha);

    }
}