package com.example.cadastrodecliente;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private TextView lista;

    private ConstraintLayout listaLayout;

    // Metodo da acao do botao adicionar cliente
    public void adicionarCliente (View view) {

        Intent it = new Intent(this,CadastroActivity.class);
        startActivity(it);
        this.finish();
    }

    private void estabeleConexao() throws Exception {
        InicializacaoDB initDB = null;

            initDB = new InicializacaoDB(this, "clientes.db", null, 1);
            Conexao.db = initDB.getWritableDatabase();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista = findViewById(R.id.textViewLista);
        listaLayout = findViewById(R.id.listaLayout);

        if(Conexao.db == null) {
            try {
                estabeleConexao();

                Snackbar sb = Snackbar.make(listaLayout, R.string.banco_sucesso, Snackbar.LENGTH_LONG);

                sb.show();
            } catch (Exception e) {
                Snackbar sb = Snackbar.make(listaLayout, R.string.banco_erro, Snackbar.LENGTH_LONG);

                sb.show();
            }
        }

        ClienteDB clienteDB = new ClienteDB(Conexao.db);
        lista.setText(clienteDB.getListaClientes());
    }

    public void adicionar (View view){
        Intent it = new Intent(this, CadastroActivity.class);
        startActivity(it);
        this.finish();
    }
}