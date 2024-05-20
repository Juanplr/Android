package com.example.controlnutricionapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.controlnutricionapp.databinding.ActivityMainBinding
import com.example.controlnutricionapp.databinding.ActivityOpciones2Binding

class OpcionesActivity2 : AppCompatActivity() {
    private lateinit var  binding: ActivityOpciones2Binding
    private var username=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOpciones2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        username = intent.getStringExtra("userName")!!
        binding.btnFireBase.setOnClickListener {
            val intent = Intent(this@OpcionesActivity2, FireBaseActivity::class.java)
            startActivity(intent)
        }
        binding.btnSql.setOnClickListener {
            val intent = Intent(this@OpcionesActivity2, MainActivity::class.java)
            intent.putExtra("userName",username)
            startActivity(intent)
        }

    }
}