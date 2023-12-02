package com.gruposami.gruposamiapp.ui.formulario.seccion

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.gruposami.gruposamiapp.databinding.PasarelaBinding
import com.gruposami.gruposamiapp.domain.servicio.model.Servicio
import com.gruposami.gruposamiapp.ui.firma.FirmaActivity
import com.gruposami.gruposamiapp.ui.formulario.FormularioActivity
import com.gruposami.gruposamiapp.ui.orden.OrdenActivity
import com.gruposami.gruposamiapp.ui.orden.adapters.ServicioAdapter


fun pasarelaMedicion(activity: FormularioActivity, servicio: Servicio) {
    val binding: PasarelaBinding = PasarelaBinding.inflate(activity.layoutInflater)
    val view: View = binding.root
    activity.setContentView(view)

    binding.textView.text = "Estos son los Servicios de esta Orden.\n" +
            " Los coloreados en verde son los que ya has completado."

    lateinit var adapter: ServicioAdapter
    val llmanager = LinearLayoutManager(activity)
    activity.formularioViewModel.obtenerListaServicios(servicio.ordenId!!)

    activity.formularioViewModel.listaServicios.observe(activity) { it ->
        adapter = ServicioAdapter(it, false, null)
        binding.recyclerPasarela.layoutManager = llmanager
        binding.recyclerPasarela.adapter = adapter
    }

    binding.botonSalir.setOnClickListener {
        activity.startActivity(Intent(activity, OrdenActivity::class.java))
    }

    binding.botonFirmar.setOnClickListener {
        // Intent para irme a la firma. Necesita orden_id
        val intent = Intent(activity, FirmaActivity::class.java)
        intent.putExtra("orden_id", servicio.ordenId)
        activity.startActivity(intent)
    }
}