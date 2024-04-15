package com.example.holamundo

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.holamundo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var  binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //inflate trae algo que esta en la vista a codigo
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.btnCalcularT.setOnClickListener{
            val base = binding.etCampoTBase.text.toString()
            val altura = binding.etCampoTAltura.text.toString()

            if (esValidoT(base, altura))
                mostrarAreaT(base,altura)
        }

        binding.btnCalcularR.setOnClickListener{
            val base = binding.etCampoRBase.text.toString()
            val altura = binding.etCampoRAltura.text.toString()

            if (esValidoR(base, altura))
                mostrarAreaR(base,altura)
        }
        binding.btnIrActividad.setOnClickListener{
            iraSegundaAcatividad()
        }
    }

    fun esValidoT(base:String, altura:String):Boolean{
        var bandera = false
        if (base.isEmpty())
            binding.etCampoTBase.setError(getString(R.string.mensaje_error))
        else
            if (altura.isEmpty())
                binding.etCampoTAltura.setError(getString(R.string.mensaje_error))
        else
            bandera = true
        return bandera
    }

    fun mostrarAreaT(base:String, altura:String){
        var base = java.lang.Double.valueOf(base)
        var altura = java.lang.Double.valueOf(altura)
        var area = (base * altura)/2
        Toast.makeText(this@MainActivity,"El Área del Tríangulo es: " + area ,Toast.LENGTH_LONG).show()
    }

    fun esValidoR(base:String, altura:String):Boolean{
        var bandera = false
        if (base.isEmpty())
            binding.etCampoRBase.setError(getString(R.string.mensaje_error))
        else
            if (altura.isEmpty())
                binding.etCampoRAltura.setError(getString(R.string.mensaje_error))
            else
                bandera = true
        return bandera
    }

    fun mostrarAreaR(base:String, altura:String){
        var base = java.lang.Double.valueOf(base)
        var altura = java.lang.Double.valueOf(altura)
        var area = base * altura
        Toast.makeText(this@MainActivity,"El Área del Rectángulo es: " + area ,Toast.LENGTH_LONG).show()
    }


    fun iraSegundaAcatividad(){
        val intent = Intent(this@MainActivity, DetalleActivity::class.java)
        startActivity(intent)
    }
}