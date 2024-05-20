package com.example.controlnutricionapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.controlnutricionapp.databinding.ActivityFireBaseBinding
import com.google.firebase.database.DatabaseReference

class FireBaseActivity : AppCompatActivity() {
    private lateinit var  binding: ActivityFireBaseBinding
    private lateinit var dataBase :DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFireBaseBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        dataBase.Firebase.database.
    }
    fun cargar
}