package com.example.controlnutricionapp.modelo

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.controlnutricionapp.poko.Nota

//serializacion trasformar un tipo de dato a otro.
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
        val CREATE_TABLE_NOTAS = ("CREATE TABLE $NOMBRE_TABLA" +
                "($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COL_TITULO TEXT, " +
                "$COL_CONTENIDO TEXT, " +
                "$COL_ID_USUARIO TEXT)")
        db!!.execSQL(CREATE_TABLE_NOTAS)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun incertarNota(nota: Nota): Long{
        val db =writableDatabase
        val valoresInsert = ContentValues()
        valoresInsert.put(COL_TITULO, nota.titulo)
        valoresInsert.put(COL_CONTENIDO, nota.contenido)
        valoresInsert.put(COL_ID_USUARIO, nota.idUsuario)
        val filas = db.insert(NOMBRE_TABLA,null, valoresInsert)
        db.close()
        return filas
    }
    @SuppressLint("Range")
    fun seleccionarNotas(): List<Nota>{
        val misNotas = mutableListOf<Nota>()
        val db = readableDatabase
        val resultadoConsulta: Cursor? = db.query(NOMBRE_TABLA,null,null,null,null,null,null)
        if(resultadoConsulta!=null){
            while (resultadoConsulta.moveToNext()){
                val idNota = resultadoConsulta.getLong(resultadoConsulta.getColumnIndex(COL_ID))
                val titulo = resultadoConsulta.getString(resultadoConsulta.getColumnIndex(COL_TITULO))
                val contenido = resultadoConsulta.getString(resultadoConsulta.getColumnIndex(
                    COL_CONTENIDO))
                val idUsuario = resultadoConsulta.getString(resultadoConsulta.getColumnIndex(
                    COL_ID_USUARIO))
                val nota = Nota(idNota,titulo,contenido,idUsuario)
                misNotas.add(nota)
            }
            resultadoConsulta.close()
        }
        db.close()
        return  misNotas
    }

    @SuppressLint("Range")
    fun seleccionarNotasPorUsuario(idUsuario:String): List<Nota>{
        val misNotas = mutableListOf<Nota>()
        val db = readableDatabase
        val resultadoConsulta: Cursor? = db.query(NOMBRE_TABLA,null,"$COL_ID_USUARIO = ?",
            arrayOf(idUsuario),null,null,null)
        if(resultadoConsulta!=null){
            while (resultadoConsulta.moveToNext()){
                val idNota = resultadoConsulta.getLong(resultadoConsulta.getColumnIndex(COL_ID))
                val titulo = resultadoConsulta.getString(resultadoConsulta.getColumnIndex(COL_TITULO))
                val contenido = resultadoConsulta.getString(resultadoConsulta.getColumnIndex(
                    COL_CONTENIDO))
                val idUsuario = resultadoConsulta.getString(resultadoConsulta.getColumnIndex(
                    COL_ID_USUARIO))
                val nota = Nota(idNota,titulo,contenido,idUsuario)
                misNotas.add(nota)
            }
            resultadoConsulta.close()
        }
        db.close()
        return  misNotas
    }

    fun actualizarNota(nota:Nota):Int{
        val db = writableDatabase
        val valoresUpdate = ContentValues().apply {
            put(COL_TITULO,nota.titulo)
            put(COL_CONTENIDO,nota.contenido)
        }
        val filasA = db.update(NOMBRE_TABLA,valoresUpdate,"$COL_ID = ? ", arrayOf(nota.idNota.toString()))
        db.close()
        return filasA
    }
    fun eliminarNota(idNota:Long):Int{
        val db = writableDatabase
        val filasA = db.delete(NOMBRE_TABLA,"$COL_ID = ? ", arrayOf(idNota.toString()))
        db.close()
        return filasA
    }
}