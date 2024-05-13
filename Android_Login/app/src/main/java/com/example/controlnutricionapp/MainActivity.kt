package com.example.controlnutricionapp

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.controlnutricionapp.adaptadores.NotaAdapter
import com.example.controlnutricionapp.databinding.ActivityMainBinding
import com.example.controlnutricionapp.interfaces.ListenerRecycleNotas
import com.example.controlnutricionapp.modelo.NotasBD
import com.example.controlnutricionapp.poko.Nota

class MainActivity : AppCompatActivity(), ListenerRecycleNotas {
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
        cargarMisNotas()
        binding.btnGuardar.setOnClickListener{
            if (validarCampos()){
                var nota = Nota(-1,binding.etTituloNota.text.toString(),binding.etContenidoNota.text.toString(),username)
                agregarNotas(nota)
            }
        }
        configuracionRecycle()
        /*binding.btnActualizar.setOnClickListener {
            if(validarCampos() && validarIdCampo()){
                var nota = Nota(binding.etIdNota.text.toString().toLong(),binding.etTituloNota.text.toString(),binding.etContenidoNota.text.toString(),username)
                actualizarNota(nota)
            }
        }
        binding.btnEliminar.setOnClickListener {
            if(validarIdEliminar()){
                eliminarNota(binding.etEliminarNota.text.toString().toLong())
            }
        }
        binding.etIdNota.setOnClickListener {

        }*/
    }

    private fun eliminarNota(idNota: Long) {
        var mensaje =""
        val resultado = modelo.eliminarNota(idNota)
        if(resultado>0){
            limpiarCampos()
            mensaje = "Nota Eliminada"
            cargarMisNotas()
        }else{
            mensaje = "Error, no se pudo Eliminar la nota. "
        }
        Toast.makeText(this@MainActivity,mensaje,Toast.LENGTH_LONG).show()

    }

    /*private fun validarIdEliminar(): Boolean {
        var bandera = true
        if (binding.etEliminarNota.text.isEmpty()){
            binding.etEliminarNota.setError("Campo obligatorio")
            bandera = false
        }
        return bandera
    }

    private fun actualizarNota(nota: Nota) {
        var mensaje =""
        val resultado = modelo.actualizarNota(nota)
        if(resultado>0){
            limpiarCampos()
            mensaje = "Nota Actualizada"
            cargarMisNotas()
        }else{
            mensaje = "Error, no se pudo actualizar la nota. "
        }
        Toast.makeText(this@MainActivity,mensaje,Toast.LENGTH_LONG).show()
    }*/

    private fun limpiarCampos() {
        binding.etTituloNota.setText("")
        binding.etContenidoNota.setText("")
        //binding.etIdNota.setText("")
        //binding.etEliminarNota.setText("")
    }

    /*private fun validarIdCampo(): Boolean {
        var bandera = true
        if (binding.etIdNota.text.isEmpty()){
            binding.etIdNota.setError("Campo obligatorio")
            bandera = false
        }
        return bandera
    }*/

    fun cargarMisNotas(){
        val notas = modelo.seleccionarNotasPorUsuario(username);
        /*var informacion =""
        for (nota in notas){
            informacion += "ID nota: ${nota.idNota}, Titulo: ${nota.titulo}, ID usuario: ${nota.idUsuario}, Contenido ${nota.contenido}\n\n"
        }
        binding.tvNotas.setText(informacion)*/
        if(notas.size>0){
            binding.tvMensajeNota.visibility = View.INVISIBLE
            binding.rcNotas.visibility = View.VISIBLE
            binding.rcNotas.adapter = NotaAdapter(notas,this@MainActivity)
        }else{
            binding.tvMensajeNota.visibility = View.VISIBLE
            binding.rcNotas.visibility = View.INVISIBLE
        }
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
    private fun configuracionRecycle(){
        binding.rcNotas.layoutManager = LinearLayoutManager(this@MainActivity)
        binding.rcNotas.setHasFixedSize(true)
    }

    override fun clicEditarNota(nota: Nota, position: Int) {
        Toast.makeText(this@MainActivity,"Posicion $position", Toast.LENGTH_LONG).show()
    }

    override fun clicEliminarNota(nota: Nota, position: Int) {
        Toast.makeText(this@MainActivity,"Posicion $position", Toast.LENGTH_LONG).show()
    }
}