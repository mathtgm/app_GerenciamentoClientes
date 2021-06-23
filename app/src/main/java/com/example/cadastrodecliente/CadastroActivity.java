package com.example.cadastrodecliente;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class CadastroActivity extends AppCompatActivity {

    private EditText codigo;
    private EditText nome;
    private EditText login;
    private EditText senha;
    private EditText email;
    private EditText telefone;
    private EditText apelido;
    private EditText cidade;
    private EditText endereco;
    private EditText bairro;
    private String lista;

    private ConstraintLayout cadastroLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        codigo = findViewById(R.id.editTextCodigo);
        nome = findViewById(R.id.editTextNome);
        login = findViewById(R.id.editTextLogin);
        senha = findViewById(R.id.editTextPassword);
        email = findViewById(R.id.editTextEmail);
        telefone = findViewById(R.id.editTextPhone);
        apelido = findViewById(R.id.editTextApelido);
        cidade =  findViewById(R.id.editTextCidade);
        endereco = findViewById(R.id.editTextEndereco);
        bairro = findViewById(R.id.editTextBairro);

        cadastroLayout = findViewById(R.id.cadastroLayout);

        if(Conexao.db == null)
            estabeleConexao();
    }

    private void estabeleConexao() {
        InicializacaoDB initDB = null;

        initDB = new InicializacaoDB(this, "clientes.db", null, 1);
        Conexao.db = initDB.getWritableDatabase();
    }

    public void cadastrarCliente (View view) {
       Cliente cliente = new Cliente();

        cliente.setNome((nome.getText().toString()));
        cliente.setLogin((login.getText().toString()));
        cliente.setSenha((senha.getText().toString()));
        cliente.setEmail((email.getText().toString()));
        cliente.setTelefone((telefone.getText().toString()));
        cliente.setApelido((apelido.getText().toString()));
        cliente.setCidade((cidade.getText().toString()));
        cliente.setEndereco((endereco.getText().toString()));
        cliente.setBairro((bairro.getText().toString()));

        ClienteDB clienteDB = new ClienteDB(Conexao.db);
        try {
            clienteDB.inserir(cliente);

            Snackbar sb = Snackbar.make(cadastroLayout, R.string.inserir_sucesso, Snackbar.LENGTH_LONG);

            sb.show();
        } catch (SQLException e) {
            Snackbar sb = Snackbar.make(cadastroLayout, R.string.inserir_erro, Snackbar.LENGTH_LONG);

            sb.show();
        }

        limpar(view);
    }

    public void alterar(View view){
        Cliente cliente = new Cliente();

        cliente.setNome((nome.getText().toString()));
        cliente.setApelido(apelido.getText().toString());
        cliente.setCidade(cidade.getText().toString());
        cliente.setBairro(bairro.getText().toString());
        cliente.setEndereco(endereco.getText().toString());
        cliente.setLogin((login.getText().toString()));
        cliente.setSenha((senha.getText().toString()));
        cliente.setEmail((email.getText().toString()));
        cliente.setTelefone((telefone.getText().toString()));
        cliente.setCod(Integer.valueOf(codigo.getText().toString()));

        ClienteDB clienteDB = new ClienteDB(Conexao.db);

        try {
            clienteDB.alterar(cliente);

            Snackbar sb = Snackbar.make(cadastroLayout, R.string.alterar_sucesso, Snackbar.LENGTH_LONG);

            sb.show();
        } catch (SQLException e) {
            Snackbar sb = Snackbar.make(cadastroLayout, R.string.alterar_erro, Snackbar.LENGTH_LONG);

            sb.show();
        }

        limpar(view);
    }

    public void remover(View view){
        Cliente cliente = new Cliente();

        cliente.setCod(Integer.valueOf(codigo.getText().toString()));
        ClienteDB clienteDB = new ClienteDB(Conexao.db);

        try {
            clienteDB.remover(cliente);

            Snackbar sb = Snackbar.make(cadastroLayout, R.string.remover_sucesso, Snackbar.LENGTH_LONG);

            sb.show();
        } catch (SQLException e) {
            Snackbar sb = Snackbar.make(cadastroLayout, R.string.remover_erro, Snackbar.LENGTH_LONG);

            sb.show();
        }

        limpar(view);
    }

    public void cancelar (View view) {
        Intent it = new Intent(this, MainActivityMenu.class);

        if(it.hasExtra("lista")){
            lista = it.getStringExtra("lista");
        }

        startActivity(it);
        this.finish();
    }

    public void limpar (View view) {
        nome.setText("");
        email.setText("");
        senha.setText("");
        telefone.setText("");
        login.setText("");
        apelido.setText("");
        cidade.setText("");
        endereco.setText("");
        bairro.setText("");
        codigo.setText("");
    }

    public void buscar (View view) {

            ClienteDB clienteDB = new ClienteDB(Conexao.db);
            Cliente cliente;

            cliente = clienteDB.pesquisar(codigo.getText().toString());

            if(cliente.getCod() == 0) {
                Snackbar sb = Snackbar.make(cadastroLayout, R.string.pesquisa_erro, Snackbar.LENGTH_LONG);

                sb.show();
            }

            nome.setText(cliente.getNome());
            email.setText(cliente.getEmail());
            senha.setText(cliente.getSenha());
            telefone.setText(cliente.getTelefone());
            login.setText(cliente.getLogin());
            apelido.setText(cliente.getApelido());
            cidade.setText(cliente.getCidade());
            endereco.setText(cliente.getEndereco());
            bairro.setText(cliente.getBairro());

    }
}