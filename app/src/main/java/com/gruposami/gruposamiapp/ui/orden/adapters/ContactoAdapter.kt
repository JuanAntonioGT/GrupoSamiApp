package com.gruposami.gruposamiapp.ui.orden.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gruposami.gruposamiapp.R
import com.gruposami.gruposamiapp.domain.contacto.model.Contacto
import com.gruposami.gruposamiapp.ui.orden.OrdenViewModel


class ContactoAdapter(
    private var contactoList: List<Contacto>,
    private val ordenViewModel: OrdenViewModel,
) : RecyclerView.Adapter<ContactoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ContactoViewHolder(layoutInflater.inflate(R.layout.item_contacto, parent, false))
    }

    override fun onBindViewHolder(holder: ContactoViewHolder, position: Int) {
        val item = contactoList[position]
        holder.render(item, ordenViewModel)
    }

    override fun getItemCount(): Int = contactoList.size

}

