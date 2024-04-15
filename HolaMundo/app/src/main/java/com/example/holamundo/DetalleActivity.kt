package com.example.holamundo

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.holamundo.databinding.ActivityDetalleBinding

class DetalleActivity : AppCompatActivity() {
    private lateinit var bindig: ActivityDetalleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindig = ActivityDetalleBinding.inflate(layoutInflater)
        val view = bindig.root
        setContentView(view)
        bindig.btnCalcularH.setOnClickListener{
            val catetoC = bindig.etCampoC.text.toString()
            val catetoB = bindig.etCampoB.text.toString()

            if(esValido(catetoB,catetoC))
                mostrarH(catetoB,catetoC)
        }
        bindig.btnLimpiar.setOnClickListener{
            limpiar()
        }
    }

    fun limpiar(){
        bindig.etCampoB.setText("")
        bindig.etCampoC.setText("")
    }
    fun esValido(catetoB:String, catetoC: String): Boolean{
        var bandera = false
        if (catetoB.isEmpty())
            bindig.etCampoB.setError(getString(R.string.mensaje_error))
        else
            if (catetoC.isEmpty())
                bindig.etCampoC.setError(getString(R.string.mensaje_error))
            else
                bandera = true
        return bandera
    }

    fun mostrarH(catetoB:String, catetoC: String){
        var catetoC = java.lang.Double.valueOf(catetoC)
        var catetoB = java.lang.Double.valueOf(catetoB)
        var hipotenusa = (catetoC * catetoC) + (catetoB* catetoB)
        Toast.makeText(this@DetalleActivity,"La Hipotenusa es: " + hipotenusa , Toast.LENGTH_LONG).show()
    }
}