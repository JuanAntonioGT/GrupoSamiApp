package com.gruposami.gruposamiapp.ui.orden.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gruposami.gruposamiapp.R
import com.gruposami.gruposamiapp.domain.orden.model.OrdenCompleta
import com.gruposami.gruposamiapp.ui.orden.OrdenViewModel


class OrdenAdapter(
    private var ordenList: List<OrdenCompleta>,
    private val ordenViewModel: OrdenViewModel,
) : RecyclerView.Adapter<OrdenViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdenViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return OrdenViewHolder(layoutInflater.inflate(R.layout.item_orden, parent, false))
    }

    override fun onBindViewHolder(holder: OrdenViewHolder, position: Int) {
        val item = ordenList[position]
        holder.render(item, ordenViewModel)
    }

    override fun getItemCount(): Int = ordenList.size

}
