package com.example.prueba

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.InputFilter
import android.text.InputType
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.prueba.databinding.ActivityDetalleBinding


class DetalleActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var bindig: ActivityDetalleBinding
    var nombre: String? =""
    var tipoIntent: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindig = ActivityDetalleBinding.inflate(layoutInflater)
        val view = bindig.root
        setContentView(view)
        nombre = intent.getStringExtra("saludo")
        agregarSaludo()
        bindig.btnAcercaDe.setOnClickListener{
            abrirDialogoAcercaDe()
        }
        bindig.btnVerWeb.setOnClickListener {
            if (validar())
                lanzarIntent(tipoIntent)
            else
                bindig.etUrl.setError(getString(R.string.mensaje_error))
        }
        configurarSpinnerArreglo()
        bindig.opcionesIntent.onItemSelectedListener = this
    }
    fun validar():Boolean{
        return bindig.etUrl.text.isNotEmpty()
    }
    fun agregarSaludo(){
        bindig.tvBienvenida.text = "Bienvenido ${nombre}"
    }
    fun lanzarIntent(tipo:  Int){
        var url = bindig.etUrl.text.toString()
        when(tipo){
            0-> {
                if (!continenLaRuta(url))
                    url ="https://" +url
            }
            1-> url ="tel: "+ url
            2-> url ="mailto" + url
        }
        val uriConten = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, uriConten)
        startActivity(intent)
    }
    fun abrirDialogoAcercaDe(){
        val aertaAcercaDe = AlertDialog.Builder(this@DetalleActivity)
        aertaAcercaDe.setTitle("Acerca De")
        aertaAcercaDe.setMessage("Bienvenido" +
                "SkaJplr")
        aertaAcercaDe.setPositiveButton("Aceptar", {
            dialogInterface, i -> Toast.makeText(this@DetalleActivity, "Gracias", Toast.LENGTH_LONG).show()
        })
        aertaAcercaDe.setNegativeButton("Cancellar", null)
        aertaAcercaDe.setCancelable(false)
        val dialogo = aertaAcercaDe.create()
        dialogo.show()
    }
    fun configurarSpinnerRecurso(){
        ArrayAdapter.createFromResource(this@DetalleActivity, R.array.opciones_intent,android.R.layout.simple_spinner_item).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            bindig.opcionesIntent.adapter = it
        }

    }
    fun configurarSpinnerArreglo(){
        val opcionesIntent = arrayOf("Sitio Web","LLamada Telefonica","Envio de correo")
        val adapterCadena = ArrayAdapter<String>(this@DetalleActivity, android.R.layout.simple_spinner_item,opcionesIntent)
        adapterCadena.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        bindig.opcionesIntent.adapter = adapterCadena
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Log.i("Seleccion intent: ", "selec ${position}")
        tipoIntent = position
        cargarOpcionesIntent(position)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
    fun cargarOpcionesIntent(id : Int){
        bindig.etUrl.setText("")
        when(id){
            0->{
                bindig.etUrl.hint = "Ingresa la URL"
                bindig.btnVerWeb.text = "Ir a sitio web"
            }
            1->{
                bindig.etUrl.hint = "Ingresa el numero Telefonico"
                bindig.btnVerWeb.text = "Realizar Llamada"
                bindig.etUrl.inputType = InputType.TYPE_CLASS_NUMBER
                bindig.etUrl.filters = arrayOf(InputFilter.LengthFilter(10))
            }
            2->{
                bindig.etUrl.hint = "Ingresa el correo"
                bindig.btnVerWeb.text = "Enviar Correo Electronico"
            }
            else ->{

            }
        }
    }
    fun continenLaRuta(url: String):Boolean{
        var bandera = false
        var cadena = ""
        for (i in 0 until url.length) {
            cadena += url[i]
            if (cadena=="https://"){
                bandera = true
                break
            }
        }
        return bandera
    }
}