package com.example.controlnutricionapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.controlnutricionapp.databinding.ActivityMainBinding
import com.example.controlnutricionapp.modelo.NotasBD
import com.example.controlnutricionapp.poko.Nota

class MainActivity : AppCompatActivity() {
    private lateinit var  binding: ActivityMainBinding
    private lateinit var modelo: NotasBD
    private var username=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        modelo = NotasBD(this@MainActivity)
        username = intent.getStringExtra("userName")!!
        binding.btnGuardar.setOnClickListener{
            if (validarCampos()){
                var nota = Nota(-1,binding.etTituloNota.text.toString(),binding.etContenidoNota.text.toString(),username)
                agregarNotas(nota)
            }
        }
    }
    fun cargarMisNotas(){
        val notas = modelo.seleccionarNotas();
        var informacion =""
        for (nota in notas){
            informacion += "ID nota: ${nota.idNota}, Titulo: ${nota.titulo}, ID usuario: ${nota.idUsuario}\n\n"
        }
        binding.tvNotas.setText(informacion)
    }
    fun agregarNotas(nota:Nota){
        val resultado = modelo.incertarNota(nota)
        var mensaje = ""
        if(resultado>0){
            binding.etTituloNota.setText("")
            binding.etContenidoNota.setText("")
            mensaje = "Guardado"
            cargarMisNotas()
        }else{
            mensaje = "Error"
        }
        Toast.makeText(this@MainActivity,mensaje,Toast.LENGTH_LONG).show()
    }
    fun validarCampos():Boolean{
        var bandera = true
        if (binding.etTituloNota.text.isEmpty()){
            binding.etTituloNota.setError("Campo obligatorio")
            bandera = false
        }
        if (binding.etContenidoNota.text.isEmpty()){
            binding.etContenidoNota.setError("Campo obligatorio")
            bandera = false
        }
        return bandera
    }
}