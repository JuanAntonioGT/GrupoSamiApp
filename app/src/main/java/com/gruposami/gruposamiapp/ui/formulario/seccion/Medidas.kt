package com.gruposami.gruposamiapp.ui.formulario.seccion

import android.view.View
import androidx.core.widget.doAfterTextChanged
import com.bumptech.glide.Glide
import com.gruposami.gruposamiapp.databinding.Formulario06Binding
import com.gruposami.gruposamiapp.domain.multimedia.model.Multimedia
import com.gruposami.gruposamiapp.domain.servicio.model.Servicio
import com.gruposami.gruposamiapp.domain.sesion.model.Sesion
import com.gruposami.gruposamiapp.ui.formulario.FormularioActivity
import java.io.File

fun medidas(activity: FormularioActivity, servicio: Servicio) {
    val binding: Formulario06Binding =
        Formulario06Binding.inflate(activity.layoutInflater)
    val view: View = binding.root
    activity.setContentView(view)

    servicio.faseFormulario = 10
    activity.formularioViewModel.modificarServicio(servicio)

    // Mostrar las vistas que sean necesario
    if (servicio.descripcionUno.equals("Mesa")) {
        binding.linearLayoutMedidasLuz.visibility = View.VISIBLE
    } else {
        binding.linearLayoutMedidasLuz.visibility = View.GONE
    }
    binding.botonSimple.setOnClickListener { binding.linearLayoutMedidasLuz.visibility = View.GONE }
    binding.botonCompleto.setOnClickListener { binding.linearLayoutMedidasLuz.visibility = View.VISIBLE }

    // Cargar información en el editText si la tiene
    if (servicio.ofertadaAlto != null) {
        binding.editTextOfertadaAlto.setText(servicio.ofertadaAlto.toString())
    }
    if (servicio.ofertadaAncho != null) {
        binding.editTextOfertadaAncho.setText(servicio.ofertadaAncho.toString())
    }
    if (servicio.modificadaAlto != null) {
        binding.editTextModificadaAlto.setText(servicio.modificadaAlto.toString())
    }
    if (servicio.modificadaAncho != null) {
        binding.editTextModificadaAncho.setText(servicio.modificadaAncho.toString())
    }

    if (servicio.luzAlto != null) {
        binding.editTextLuzAlto.setText(servicio.luzAlto.toString())
    }
    if (servicio.luzAncho != null) {
        binding.editTextLuzAncho.setText(servicio.luzAncho.toString())
    }
    if (servicio.espatulaAlto != null) {
        binding.editTextEspatulaAlto.setText(servicio.espatulaAlto.toString())
    }
    if (servicio.espatulaAncho != null) {
        binding.editTextEspatulaAncho.setText(servicio.espatulaAncho.toString())
    }

    // Funciones para cuando escribo en un campo de texto
    binding.editTextOfertadaAlto.doAfterTextChanged {
        if (binding.editTextOfertadaAlto.length() > 0) {
            servicio.ofertadaAlto = binding.editTextOfertadaAlto.text.toString().toDouble()
        } else {
            servicio.ofertadaAlto = null
        }
        calcularOfertada(servicio, binding)
    }
    binding.editTextOfertadaAncho.doAfterTextChanged {
        if (binding.editTextOfertadaAncho.length() > 0) {
            servicio.ofertadaAncho = binding.editTextOfertadaAncho.text.toString().toDouble()
        } else {
            servicio.ofertadaAncho = null
        }
        calcularOfertada(servicio, binding)
    }

    binding.editTextModificadaAlto.doAfterTextChanged {
        if (binding.editTextModificadaAlto.length() > 0) {
            servicio.modificadaAlto = binding.editTextModificadaAlto.text.toString().toDouble()
        } else {
            servicio.modificadaAlto = null
        }
    }
    binding.editTextModificadaAncho.doAfterTextChanged {
        if (binding.editTextModificadaAncho.length() > 0) {
            servicio.modificadaAncho = binding.editTextModificadaAncho.text.toString().toDouble()
        } else {
            servicio.modificadaAncho = null
        }
    }

    binding.editTextLuzAlto.doAfterTextChanged {
        if (binding.editTextLuzAlto.length() > 0) {
            servicio.luzAlto = binding.editTextLuzAlto.text.toString().toDouble()
        } else {
            servicio.luzAlto = null
        }
        calcularFormulada(servicio, binding)
    }
    binding.editTextLuzAncho.doAfterTextChanged {
        if (binding.editTextLuzAncho.length() > 0) {
            servicio.luzAncho = binding.editTextLuzAncho.text.toString().toDouble()
        } else {
            servicio.luzAncho = null
        }
        calcularFormulada(servicio, binding)
    }

    binding.editTextEspatulaAlto.doAfterTextChanged {
        if (binding.editTextEspatulaAlto.length() > 0) {
            servicio.espatulaAlto = binding.editTextEspatulaAlto.text.toString().toDouble()
        } else {
            servicio.espatulaAlto = null
        }
        calcularFormulada(servicio, binding)
    }
    binding.editTextEspatulaAncho.doAfterTextChanged {
        if (binding.editTextEspatulaAncho.length() > 0) {
            servicio.espatulaAncho = binding.editTextEspatulaAncho.text.toString().toDouble()
        } else {
            servicio.espatulaAncho = null
        }
        calcularFormulada(servicio, binding)
    }

    // Boton para dibujar el cristal
    binding.botonDibujo.setOnClickListener {
        activity.dibujoCristal()
    }
    activity.formularioViewModel.obtenerMultimedia(
        Multimedia(
            id = null,
            fechaCreacion = null,
            fechaModificacion = null,
            ruta = null,
            tipoArchivo = "Imagen",
            categoriaArchivo = Sesion.filtroEstado,
            etiquetaArchivo = "Dibujo",
            servicioId = servicio.id
        )
    )
    activity.formularioViewModel.multimediaDibujoMutable.observe(activity) {
        if (it != null) {
            if (it.etiquetaArchivo == Sesion.filtroEstado && it.categoriaArchivo == Sesion.filtroEstado) {
                Glide.with(activity).load(File(it.ruta.toString())).into(binding.imageView1)
            }
        }
    }

    calcularFormulada(servicio, binding)
    calcularOfertada(servicio, binding)

    binding.botonAtras.setOnClickListener {
        servicio.faseFormulario = 9;
        activity.ubicacion()
    }
    binding.botonCancelar.setOnClickListener {
        activity.dialogCancelar()
    }
    binding.botonSiguiente.setOnClickListener {
        servicio.faseFormulario = 11
        activity.formularioViewModel.modificarServicio(servicio)
        activity.ubicacion()
    }

}

fun calcularFormulada(servicio: Servicio, binding: Formulario06Binding) {
    if (servicio.luzAlto != null && servicio.luzAncho != null && servicio.espatulaAlto != null && servicio.espatulaAncho != null) {
        val a = binding.editTextLuzAlto.text.toString().toDouble()
        val b = binding.editTextLuzAncho.text.toString().toDouble()
        val c = binding.editTextEspatulaAlto.text.toString().toDouble()
        val d = binding.editTextEspatulaAncho.text.toString().toDouble()
        servicio.formuladaAlto = a + (c * 0.3 + c)
        servicio.formuladaAncho = b + (d * 0.3 + d)
        binding.textViewFormula.text = "${servicio.formuladaAlto} x ${servicio.formuladaAncho}"
    } else {
        servicio.formuladaAlto = null
        servicio.formuladaAncho = null
        binding.textViewFormula.text = null
    }
}

fun calcularOfertada(servicio: Servicio, binding: Formulario06Binding) {
    if (servicio.ofertadaAlto != null && servicio.ofertadaAncho != null) {
        binding.textViewOfertada.text = "${servicio.ofertadaAlto} x ${servicio.ofertadaAncho}"
    } else {
        binding.textViewOfertada.text = null
    }
}
