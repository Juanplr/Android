package com.example.prueba

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.prueba.databinding.ActivityMainBinding
import com.example.prueba.databinding.ActivitySeundaPantallaBinding

class SeundaPantalla : AppCompatActivity() {
    private lateinit var bindig: ActivitySeundaPantallaBinding
    var nombre: String? =""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindig = ActivitySeundaPantallaBinding.inflate(layoutInflater)
        val view = bindig.root
        setContentView(view)
        nombre = intent.getStringExtra("saludo")
        agregarSaludo()
        bindig.btnAcercaDe.setOnClickListener{
            abrirDialogoAcercaDe()
        }
        bindig.btnVerWeb.setOnClickListener {
            if (validar())
                abrirSitioWeb()
            else
                bindig.etUrl.setError(getString(R.string.mensaje_error))
        }
    }
    fun validar():Boolean{
        return bindig.etUrl.text.isNotEmpty()
    }
    fun agregarSaludo(){
        bindig.tvBienvenida.text = "Bienvenido ${nombre}"
    }
    fun abrirSitioWeb(){
        val url = bindig.etUrl.text.toString()
        val uriConten = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, uriConten)
        startActivity(intent)
    }
    fun abrirDialogoAcercaDe(){
        val aertaAcercaDe = AlertDialog.Builder(this@SeundaPantalla)
        aertaAcercaDe.setTitle("Acerca De")
        aertaAcercaDe.setMessage("Bienvenido" +
                "SkaJplr")
        aertaAcercaDe.setPositiveButton("Aceptar", {
            dialogInterface, i -> Toast.makeText(this@SeundaPantalla, "Gracias", Toast.LENGTH_LONG).show()
        })
        aertaAcercaDe.setNegativeButton("Cancellar", null)
        aertaAcercaDe.setCancelable(false)
        val dialogo = aertaAcercaDe.create()
        dialogo.show()
    }
}