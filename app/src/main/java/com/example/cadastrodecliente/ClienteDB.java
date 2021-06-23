package com.example.cadastrodecliente;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ClienteDB {
    private SQLiteDatabase db;

    public ClienteDB (SQLiteDatabase db) {
        this.db = db;
    }

    public void inserir(Cliente cliente) throws SQLException {
        ContentValues valores = new ContentValues();

        valores.put("nome", cliente.getNome());
        valores.put("apelido", cliente.getApelido());
        valores.put("login", cliente.getLogin());
        valores.put("senha", cliente.getSenha());
        valores.put("email", cliente.getEmail());
        valores.put("telefone", cliente.getTelefone());
        valores.put("cidade", cliente.getCidade());
        valores.put("endereco", cliente.getEndereco());
        valores.put("bairro", cliente.getBairro());

        db.insertOrThrow("clientes", null, valores);
    }

    public void remover (Cliente cliente){
        String args[] = new String[1];
        args[0] = String.valueOf(cliente.getCod());

        int resposta = db.delete("clientes", "codigo = ?", args);

        if(resposta == 0)
            throw new SQLException();
    }

    public void alterar(Cliente cliente) {
        ContentValues valores = new ContentValues();

        valores.put("nome", cliente.getNome());
        valores.put("apelido", cliente.getApelido());
        valores.put("login", cliente.getLogin());
        valores.put("senha", cliente.getSenha());
        valores.put("email", cliente.getEmail());
        valores.put("telefone", cliente.getTelefone());
        valores.put("cidade", cliente.getCidade());
        valores.put("endereco", cliente.getEndereco());
        valores.put("bairro", cliente.getBairro());

        String args[] = new String[1];
        args[0] = String.valueOf(cliente.getCod());

       int resposta =  db.update("clientes", valores, "codigo = ?", args);

        if(resposta == 0)
            throw new SQLException();

    }

    public Cliente pesquisar (String cod) {
        String sql = "SELECT * FROM clientes where codigo = '" + cod + "'";
        Cursor cursor = db.rawQuery(sql, null);

        Cliente cliente = new Cliente();

        cursor.moveToFirst();
        if (cursor.getCount() == 0) {
            return cliente;
        }

        cliente.setCod(cursor.getInt(0));
        cliente.setNome(cursor.getString(1));
        cliente.setLogin(cursor.getString(2));
        cliente.setSenha(cursor.getString(3));
        cliente.setEmail(cursor.getString(4));
        cliente.setTelefone(cursor.getString(5));
        cliente.setApelido(cursor.getString(6));
        cliente.setCidade(cursor.getString(7));
        cliente.setEndereco(cursor.getString(8));
        cliente.setBairro(cursor.getString(9));

        return cliente;
    }

    public Cliente pesquisar0 (String login) {
        Cursor cursor;

        String args[] = new String [1];
        args[0] = login;

        cursor = db.query("clientes", null, "login = ?", args, null, null, null);

        cursor.moveToFirst();
        if(cursor.getCount() == 0) return null;

        Cliente cliente = new Cliente();
        int index = 0;

        index = cursor.getColumnIndex("codigo");
        cliente.setCod(cursor.getInt(index));

        index = cursor.getColumnIndex("nome");
        cliente.setNome(cursor.getString(index));

        index = cursor.getColumnIndex("login");
        cliente.setLogin(cursor.getString(index));

        index = cursor.getColumnIndex("senha");
        cliente.setSenha(cursor.getString(index));

        index = cursor.getColumnIndex("email");
        cliente.setEmail(cursor.getString(index));

        index = cursor.getColumnIndex("telefone");
        cliente.setTelefone(cursor.getString(index));

        index = cursor.getColumnIndex("apelido");
        cliente.setTelefone(cursor.getString(index));

        index = cursor.getColumnIndex("cidade");
        cliente.setTelefone(cursor.getString(index));

        index = cursor.getColumnIndex("endereco");
        cliente.setTelefone(cursor.getString(index));

        index = cursor.getColumnIndex("bairro");
        cliente.setTelefone(cursor.getString(index));

        return cliente;
    }

    public String getListaClientes() {
        String lista = "";
        String sql = "SELECT * FROM clientes";

        Cursor cursor = db.rawQuery(sql, null);

        cursor.moveToFirst();

        if(cursor.getCount() > 0) {
            int indexNome = cursor.getColumnIndex("nome");
            int indexCod = cursor.getColumnIndex("codigo");
            do {
                lista += (cursor.getString(indexCod) +" - "+ cursor.getString(indexNome) + "\n");
            } while (cursor.moveToNext());
        }
        return lista;
    }
}
