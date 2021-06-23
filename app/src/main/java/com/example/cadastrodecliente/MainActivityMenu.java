package com.example.cadastrodecliente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivityMenu extends AppCompatActivity {

    private String lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }
    public void listaCliente (View view) {
        Intent it = new Intent(this, MainActivity.class);

        startActivity(it);
    }

    public void cadastrarCliente (View view) {
        Intent it = new Intent(this, CadastroActivity.class);

        startActivity(it);
    }
}