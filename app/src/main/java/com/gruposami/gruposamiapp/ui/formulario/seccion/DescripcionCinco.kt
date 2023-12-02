package com.gruposami.gruposamiapp.ui.formulario.seccion

import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.gruposami.gruposamiapp.databinding.Formulario01Binding
import com.gruposami.gruposamiapp.domain.formularioservicio.model.FormularioServicio
import com.gruposami.gruposamiapp.domain.servicio.model.Servicio
import com.gruposami.gruposamiapp.ui.formulario.FormularioActivity


fun descripcionCinco(activity: FormularioActivity, servicio: Servicio) {
    val binding: Formulario01Binding = Formulario01Binding.inflate(activity.layoutInflater)
    val view: View = binding.root
    activity.setContentView(view)

    // Limpiamos to do el contenido del formulario
    binding.linearContenedorBotones.removeAllViews()

    // Titulo
    val titulo = TextView(activity)
    titulo.textSize = 24F
    titulo.gravity = Gravity.CENTER
    titulo.setTextColor(Color.BLACK)
    titulo.text = "Posición"
    binding.linearContenedorBotones.addView(titulo)

    servicio.faseFormulario = 5
    activity.formularioViewModel.modificarServicio(servicio)

    var listadoFormulario: List<FormularioServicio>

    activity.formularioViewModel.obtenerListaFormulario(servicio)
    activity.formularioViewModel.formularioServicio.observe(activity) {
        if (it != null) {
            if (it.size > 0) {
                listadoFormulario = it

                for (formulario in listadoFormulario) {
                    val boton = Button(activity)
                    boton.text = formulario.titulo
                    binding.linearContenedorBotones.addView(boton)
                    boton.setOnClickListener {
                        servicio.descripcionCinco = boton.text.toString()
                        servicio.faseFormulario = 6
                        binding.linearContenedorBotones.removeAllViews()
                        activity.ubicacion()
                    }
                }

                val editText = EditText(activity)
                editText.hint = "Otros"
                binding.linearContenedorBotones.addView(editText)

                val boton = Button(activity)
                binding.linearContenedorBotones.addView(boton)
                boton.text = "Siguiente"
                boton.setOnClickListener {
                    servicio.descripcionTres = editText.text.toString()
                    servicio.faseFormulario = 6
                    activity.ubicacion()
                }
            } else {
                servicio.faseFormulario = 6
                activity.formularioViewModel.modificarServicio(servicio)
                activity.ubicacion()
            }
        }
    }

    binding.botonAtras.visibility = View.VISIBLE
    binding.botonAtras.setOnClickListener {
        binding.linearContenedorBotones.removeAllViews()
        servicio.descripcionCuatro = null
        servicio.descripcionCinco = null
        servicio.faseFormulario = 4
        activity.ubicacion()
    }
    binding.botonCancelar.visibility = View.VISIBLE
    binding.botonCancelar.setOnClickListener { activity.dialogCancelar() }
    binding.botonSiguiente.visibility = View.GONE

}