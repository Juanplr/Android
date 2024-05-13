package com.example.controlnutricionapp.interfaces

import com.example.controlnutricionapp.poko.Nota

interface ListenerRecycleNotas {
    fun clicEditarNota(nota: Nota, position: Int)
    fun clicEliminarNota(nota: Nota, position: Int)
}