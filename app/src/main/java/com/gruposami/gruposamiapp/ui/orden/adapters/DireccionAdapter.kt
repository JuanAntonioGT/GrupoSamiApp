package com.gruposami.gruposamiapp.ui.orden.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gruposami.gruposamiapp.R
import com.gruposami.gruposamiapp.domain.direccion.model.Direccion
import com.gruposami.gruposamiapp.ui.orden.OrdenViewModel

class DireccionAdapter(
    private var direccionList: List<Direccion>,
    private val ordenViewModel: OrdenViewModel,
) : RecyclerView.Adapter<DireccionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DireccionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return DireccionViewHolder(layoutInflater.inflate(R.layout.item_direccion, parent, false))
    }

    override fun onBindViewHolder(holder: DireccionViewHolder, position: Int) {
        val item = direccionList[position]
        holder.render(item, ordenViewModel)
    }

    override fun getItemCount(): Int = direccionList.size

}