package com.gruposami.gruposamiapp.ui.orden.adapters

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gruposami.gruposamiapp.databinding.ItemServicioBinding
import com.gruposami.gruposamiapp.domain.servicio.model.Servicio
import com.gruposami.gruposamiapp.domain.servicio.model.ServicioCompleto
import com.gruposami.gruposamiapp.domain.sesion.model.Sesion
import com.gruposami.gruposamiapp.ui.formulario.FormularioActivity
import com.gruposami.gruposamiapp.ui.orden.OrdenViewModel


class ServicioViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val binding = ItemServicioBinding.bind(view)

    fun render(servicio: ServicioCompleto, mostrarMenus: Boolean, ordenViewModel: OrdenViewModel?) {
        val colorNaranja = Color.parseColor("#FAA307")
        val colorVerde = Color.parseColor("#43A047")

        val estadoActual = Sesion.filtroEstado // Medir Medido -- Montar Montado

        // Imagen, si la hubiera, para poner
        initImagenIntermedia(servicio)

        // Colorear en caso los 3 casos:
        // Blanco: el servicio aún no ha comenzado
        // Ambar: el servicio ha comenzado a completarse pero no ha terminado
        // Verde: se ha finalizado el servicio.
        if (estadoActual == "Medir" || estadoActual == "Medido") {
            when (servicio.servicio.medido) {
                null -> {
                    binding.linearLayoutServicio.setBackgroundColor(Color.WHITE)
                }

                false -> {
                    binding.linearLayoutServicio.setBackgroundColor(colorNaranja)
                }

                true -> {
                    binding.linearLayoutServicio.setBackgroundColor(colorVerde)
                }
            }
        }
        if (estadoActual == "Montar" || estadoActual == "Montado") {
            when (servicio.servicio.montado) {
                null -> {
                    binding.linearLayoutServicio.setBackgroundColor(Color.WHITE)
                }

                false -> {
                    binding.linearLayoutServicio.setBackgroundColor(colorNaranja)
                }

                true -> {
                    binding.linearLayoutServicio.setBackgroundColor(colorVerde)
                }
            }
        }

        // 1º textViewPendiente. Si está pendiente, de lo que sea, que lo ponga. Si no, GONE
        if (servicio.servicio.pendiente == true) {
            binding.textViewPendiente.visibility = View.VISIBLE
            binding.textViewPendiente.text = "Pendiente de: ${servicio.servicio.tipoPendiente.toString()}"
        } else {
            binding.textViewPendiente.visibility = View.GONE
        }

        // 2º textViewDescripcion. Siempre tendrá una descripción previa, que es la que se pone aquí.
        binding.textViewDescripcion.text = "Descripción previa: ${servicio.servicio.descripcionPrevia}"

        // 3º textViewCristal.
        // todo: que cuando es para medir diga "Pendiente de medir" o si es pa montar, "pendiente de montar"
        if (servicio.servicio.descripcionUno.isNullOrEmpty()) {
            binding.textViewCristal.text = "Pendiente de ${Sesion.filtroEstado}"
        } else {
            var servicioString = servicio.servicio.descripcionUno
            if (!servicio.servicio.descripcionDos.isNullOrEmpty()) {
                servicioString = servicioString + " " + servicio.servicio.descripcionDos
            }
            if (!servicio.servicio.descripcionTres.isNullOrEmpty()) {
                servicioString = servicioString + " " + servicio.servicio.descripcionTres
            }
            if (!servicio.servicio.descripcionCuatro.isNullOrEmpty()) {
                servicioString = servicioString + " " + servicio.servicio.descripcionCuatro
            }
            if (!servicio.servicio.descripcionCinco.isNullOrEmpty()) {
                servicioString = servicioString + " " + servicio.servicio.descripcionCinco
            }
            binding.textViewCristal.text = servicioString
        }

        // Lo de mostrar menu o no es basicamente porque este adaptador se recicla para otra fase del formulario
        // en la que no quiero que se pueda interactuar con el usuario y solo salga la información y punto
        if (mostrarMenus) {
            // Poner un if si vengo para medir o para montar y dedicarle un formulario nuevo
            if (estadoActual == "Medir" || estadoActual == "Medido"){
                when (servicio.servicio.medido) {
                    null -> menuNull(servicio.servicio)
                    false -> menuFalse(servicio.servicio)
                    true -> menuTrue(servicio.servicio, ordenViewModel!!)
                }
            }
            if (estadoActual == "Montar" || estadoActual == "Montado"){
                when (servicio.servicio.montado) {
                    null -> menuNull(servicio.servicio)
                    false -> menuFalse(servicio.servicio)
                    true -> menuTrue(servicio.servicio, ordenViewModel!!)
                }
            }

        }

    }

    // Boton comenzar medicion
    private fun menuNull(servicio: Servicio) {
        binding.linearLayoutServicio.setOnClickListener {
            AlertDialog.Builder(itemView.context)
                .setTitle("Iniciar el formulario\n")
                .setCancelable(true)
                .setPositiveButton("Inicar") { _: DialogInterface?, _: Int ->
                    val intent = Intent(itemView.context, FormularioActivity::class.java)
                    intent.putExtra("servicioId", servicio.id)
                    itemView.context.startActivity(intent)
                    // Comenzar el formulario Medicion
                }
                .setNegativeButton("Cancelar") { _: DialogInterface?, _: Int -> }
                .show()
        }

    }

    // Boton para reanudar la medicion
    private fun menuFalse(servicio: Servicio) {
        binding.linearLayoutServicio.setOnClickListener {
            AlertDialog.Builder(itemView.context)
                .setTitle("Continuar el formulario\n")
                .setCancelable(true)
                .setPositiveButton("Continuar") { _: DialogInterface?, _: Int ->
                    val intent = Intent(itemView.context, FormularioActivity::class.java)
                    intent.putExtra("servicioId", servicio.id)
                    itemView.context.startActivity(intent)
                }
                .setNegativeButton("Cancelar") { _: DialogInterface?, _: Int -> }
                .show()
        }
    }

    private fun menuTrue(servicio: Servicio, ordenViewModel: OrdenViewModel) {
        binding.linearLayoutServicio.setOnClickListener {
            val charSequences = arrayOf<CharSequence>(
                "Añadir Fotografías Complementarias",
                "Añadir Fotografias de Desperfectos",
                "Añadir Video Complementario",
                "Añadir Video de Desperfectos",
                // "Ver detalles del cristal"
            )
            val builder: AlertDialog.Builder = AlertDialog.Builder(itemView.context)
            builder.setTitle("")
            builder.setItems(charSequences) { _, selected_option ->
                when (selected_option) {
//                    0 -> MultimediaUtils().fotografia(itemView, servicio, "Complementaria", ordenViewModel)
//                    1 -> MultimediaUtils().video(itemView, servicio, "Complementaria", ordenViewModel)
//                    2 -> MultimediaUtils().fotografia(itemView, servicio, "Desperfectos", ordenViewModel)
//                    3 -> MultimediaUtils().video(itemView, servicio, "Desperfectos", ordenViewModel)
                }
            }
            builder.show()
        }
    }

    private fun initImagenIntermedia(servicio: ServicioCompleto?) {
        if (!servicio?.multimedia.isNullOrEmpty()) {
            var imagenPresentacion = ""
            servicio?.multimedia?.map {
                if (it != null) {
                    if (it.etiquetaArchivo == "Intermedia") {
                        imagenPresentacion = it.ruta.toString()
                    }
                }
            }
            try {
                Glide.with(itemView.context).load(imagenPresentacion).into(binding.imageViewIntermedia)
            } catch (e: Exception) {
                println(e.message)
            }
        }

    }

}
