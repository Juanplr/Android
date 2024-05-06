package com.example.controlnutricionapp

import android.os.Bundle
import android.content.*
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.controlnutricionapp.databinding.ActivityLoginBinding
import com.example.controlnutricionapp.utilidades.Constantes
import com.example.controlnutricionapp.utilidades.ConstantesEstatico

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(R.layout.activity_login)
        cargarCredenciales()
        binding.btnIngresar.setOnClickListener {
            verificarCredenciales()
        }

    }

    //Utilizar shared preferences privado, utilizando el default
    fun cargarCredenciales(){
        val archivoPreferenciasDefault = getPreferences(Context.MODE_PRIVATE)
        val correoAlmacenado = archivoPreferenciasDefault.getString("correo","")
        binding.etCorreo.setText(correoAlmacenado)
        binding.etContraseA.setText(archivoPreferenciasDefault.getString("contraseña",""))
        if(archivoPreferenciasDefault.getBoolean("guardar",false))
            binding.cdRecordarCuenta.isChecked = true
    }

    //Guardar las credenciales
    fun guardarCredenciales(correo:String, password:String, guardado: Boolean){
        val archivoPreferenciasDefault = getPreferences(Context.MODE_PRIVATE)

        val prerenciaUno = getSharedPreferences("cookies",Context.MODE_PRIVATE)

        /*val archivoEdit = archivoPreferenciasDefault.edit()
        archivoEdit.putString("correo",correo)
        archivoEdit.putString("contraseña",password)
        archivoEdit.putBoolean("guardar",guardado)
        archivoEdit.apply() */
        with(archivoPreferenciasDefault.edit()) {
            putString("correo",correo)
            putString("contraseña",password)
            putBoolean("guardar",guardado)
            apply()
        }
    }

    //Verificar las credenciales
    fun verificarCredenciales(){

        val correo = binding.etCorreo.text.toString()
        val password = binding.etCorreo.text.toString()
        if (correo.equals(Constantes.USERNAME1) && password.equals(Constantes.PASSWORD1) ||
                (correo.equals(ConstantesEstatico.USERNAME2) && password.equals(ConstantesEstatico.PASSWORD2))) {
                    if(binding.cdRecordarCuenta.isChecked)
                        guardarCredenciales(correo, password,true)
                    else
                        guardarCredenciales("","",false)

                    irPantallaPrincipal()
            mostrarToast("Bienvenido a tu Hola mundo")
        }else{
            mostrarToast("Usuario y/o Contraseña incorrestos")
        }
    }

    fun mostrarToast(mensaje: String){
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }

    fun irPantallaPrincipal(){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}