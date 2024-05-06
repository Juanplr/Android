package com.example.controlnutricionapp.modelo

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class NotasBD(contexto:Context) : SQLiteOpenHelper(contexto, NOMBRE_BD, null, VERSION_BD){
    companion object{
        private const val  NOMBRE_BD = "notas.bd"
        private  const val  NOMBRE_TABLA ="notas"
        private const val COL_ID = "idNota"
        private const val COL_TITULO = "titulo"
        private const val COL_CONTENIDO = "contenido"
        private  const val  COL_ID_USUARIO = "idUsuario"
        private const val  VERSION_BD = 1
    }
    override fun onCreate(db: SQLiteDatabase?) {
        TODO("Not yet implemented")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

}