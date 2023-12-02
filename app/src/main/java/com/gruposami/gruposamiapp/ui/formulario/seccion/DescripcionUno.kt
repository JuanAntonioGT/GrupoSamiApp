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


fun descripcionUno(activity: FormularioActivity, servicio: Servicio) {
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
    titulo.text = "¿Que vas a medir?"
    binding.linearContenedorBotones.addView(titulo)

    servicio.medido = false
    servicio.faseFormulario = 1
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
                        servicio.descripcionUno = boton.text.toString()
                        servicio.faseFormulario = 2
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
                    binding.linearContenedorBotones.removeAllViews()
                    servicio.descripcionUno = editText.text.toString()
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

    binding.botonAtras.visibility = View.GONE
    binding.botonCancelar.setOnClickListener { activity.dialogCancelar() }
    binding.botonSiguiente.visibility = View.GONE

}
