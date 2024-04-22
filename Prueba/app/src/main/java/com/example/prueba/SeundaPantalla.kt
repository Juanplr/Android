package com.example.prueba

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

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
        configurarSpinnerArreglo()
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
    fun configurarSpinnerRecurso(){
        ArrayAdapter.createFromResource(this@SeundaPantalla, R.array.opciones_intent,android.R.layout.simple_spinner_item).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            bindig.opcionesIntent.adapter = it
        }

    }
    fun configurarSpinnerArreglo(){
        val opcionesIntent = arrayOf("Sitio Web","LLamada Telefonica","Envio de correo")
        val adapterCadena = ArrayAdapter<String>(this@SeundaPantalla, android.R.layout.simple_spinner_item,opcionesIntent)
        adapterCadena.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        bindig.opcionesIntent.adapter = adapterCadena
    }
}