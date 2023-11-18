package com.gruposami.gruposamiapp.ui.orden.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gruposami.gruposamiapp.R
import com.gruposami.gruposamiapp.domain.servicio.model.ServicioCompleto
import com.gruposami.gruposamiapp.ui.orden.OrdenViewModel

class ServicioAdapter(
    private var servicioList: List<ServicioCompleto>,
    private var mostrarMenus: Boolean,
    private val ordenViewModel: OrdenViewModel?
) : RecyclerView.Adapter<ServicioViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServicioViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ServicioViewHolder(layoutInflater.inflate(R.layout.item_servicio, parent, false))
    }

    override fun onBindViewHolder(holder: ServicioViewHolder, position: Int) {
        val item = servicioList[position]
        holder.render(item, mostrarMenus, ordenViewModel)
    }

    override fun getItemCount(): Int = servicioList.size

}
