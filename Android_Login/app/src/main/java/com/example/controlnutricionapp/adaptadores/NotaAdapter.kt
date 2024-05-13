package com.example.controlnutricionapp.adaptadores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.controlnutricionapp.poko.Nota
import com.example.controlnutricionapp.R
import com.example.controlnutricionapp.interfaces.ListenerRecycleNotas

class NotaAdapter (val notas: List<Nota>, val listener : ListenerRecycleNotas): RecyclerView.Adapter<NotaAdapter.ViewHolderNota>(){
    class ViewHolderNota(itemView: View): RecyclerView.ViewHolder(itemView){
        val tvTituloNota: TextView = itemView.findViewById(R.id.tv_titulo_nota)
        val tvContenidoNota: TextView = itemView.findViewById(R.id.tv_contenido_nota)
        val tvEditarNota: TextView = itemView.findViewById(R.id.tv_editar)
        val tvEliminarNota: TextView = itemView.findViewById(R.id.tv_eliminar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderNota {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_notas,parent,false)

        return ViewHolderNota(itemView)
    }

    override fun getItemCount(): Int {
        return notas.size
    }

    override fun onBindViewHolder(holder: ViewHolderNota, position: Int) {
        val nota = notas.get(position)
        holder.tvTituloNota.text = nota.titulo
        holder.tvContenidoNota.text = nota.contenido

        holder.tvEditarNota.setOnClickListener{
            listener.clicEditarNota(nota,position)
        }

        holder.tvEliminarNota.setOnClickListener{
            listener.clicEliminarNota(nota,position)
        }
    }
}