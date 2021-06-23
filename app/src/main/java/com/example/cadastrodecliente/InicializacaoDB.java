package com.example.cadastrodecliente;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class InicializacaoDB extends SQLiteOpenHelper {


    public InicializacaoDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS clientes("+
                "codigo INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "nome VARCHAR(200) NOT NULL,"+
                "login VARCHAR(50),"+
                "senha VARCHAR(100),"+
                "email VARCHAR(100),"+
                "telefone VARCHAR(20),"+
                "apelido varchar(200), "+
                "cidade varchar(100), "+
                "endereco varchar(100),"+
                "bairro varchar(50));";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
