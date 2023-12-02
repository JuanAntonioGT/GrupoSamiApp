package com.gruposami.gruposamiapp.ui.formulario.seccion

import android.view.MotionEvent
import android.view.View
import android.widget.*
import com.gruposami.gruposamiapp.databinding.Formulario02Binding
import com.gruposami.gruposamiapp.domain.servicio.model.Servicio
import com.gruposami.gruposamiapp.ui.formulario.FormularioActivity

import com.gruposami.gruposamiapp.utils.NuevaFotografia
import com.gruposami.gruposamiapp.utils.NuevoVideo


fun desperfectos(activity: FormularioActivity, servicio: Servicio) {
    val binding: Formulario02Binding = Formulario02Binding.inflate(activity.layoutInflater)
    val view: View = binding.root
    activity.setContentView(view)

    servicio.faseFormulario = 6
    activity.formularioViewModel.modificarServicio(servicio)

    binding.textViewTitulo.text = "¿La ${servicio.descripcionUno} presenta algún desperfecto?"

    binding.checkBox1.setOnTouchListener { v: View?, event: MotionEvent? ->
        binding.checkBox2.isChecked = false
        false
    }
    binding.checkBox2.setOnTouchListener { v: View?, event: MotionEvent? ->
        binding.checkBox1.isChecked = false
        false
    }
    binding.checkBox3.setOnTouchListener { v: View?, event: MotionEvent? ->
        binding.checkBox4.isChecked = false
        false
    }
    binding.checkBox4.setOnTouchListener { v: View?, event: MotionEvent? ->
        binding.checkBox3.isChecked = false
        false
    }
    binding.checkBox5.setOnTouchListener { v: View?, event: MotionEvent? ->
        binding.checkBox6.isChecked = false
        false
    }
    binding.checkBox6.setOnTouchListener { v: View?, event: MotionEvent? ->
        binding.checkBox5.isChecked = false
        false
    }

    binding.linearLayout1.visibility = View.GONE
    binding.linearLayout2.visibility = View.GONE
    binding.linearLayout3.visibility = View.GONE

    if (servicio.descripcionTres != null && servicio.descripcionTres.equals("Corredera")) {
        binding.linearLayout1.visibility = View.VISIBLE
        binding.linearLayout2.visibility = View.VISIBLE
        binding.linearLayout3.visibility = View.VISIBLE
        binding.textView2.text = "¿Rueda bien la ${servicio.descripcionUno}?"
        binding.textView3.text = "¿Cierra bien con el marco?"
        binding.textView4.text = "¿Funciona bien el pestillo?"
    }
    if (servicio.descripcionTres != null && servicio.descripcionTres.equals("Abatible")) {
        binding.linearLayout1.visibility = View.VISIBLE
        binding.linearLayout2.visibility = View.VISIBLE
        binding.linearLayout3.visibility = View.VISIBLE
        binding.textView2.text = "¿Abate bien la ${servicio.descripcionUno}?"
        binding.textView3.text = "¿Cierra bien con el marco?"
        binding.textView4.text = "¿Funciona bien el pestillo?"
    }

    if (servicio.desperfectos?.startsWith("La ${servicio.descripcionUno} rueda bien,") == true) {
        binding.checkBox1.isChecked = true
    } else if (servicio.desperfectos?.startsWith("La ${servicio.descripcionUno} abate bien,") == true) {
        binding.checkBox1.isChecked = true
    }
    if (servicio.desperfectos?.startsWith("La ${servicio.descripcionUno} no rueda bien,") == true) {
        binding.checkBox2.isChecked = true
    } else if (servicio.desperfectos?.startsWith("La ${servicio.descripcionUno} no abate bien,") == true) {
        binding.checkBox2.isChecked = true
    }
    if (servicio.desperfectos?.contains("no cierra bien con el marco ") == true) {
        binding.checkBox4.isChecked = true
    } else if (servicio.desperfectos?.contains("cierra bien con el marco ") == true) {
        binding.checkBox3.isChecked = true
    }
    if (servicio.desperfectos?.contains("no funciona el pestillo.") == true) {
        binding.checkBox6.isChecked = true
    } else if (servicio.desperfectos?.contains(" funciona el pestillo.") == true) {
        binding.checkBox5.isChecked = true
    }
    val texto = servicio.desperfectos?.replaceBeforeLast(".", "")
    binding.editText.setText(texto)

    binding.boton1.setOnClickListener {
        val nuevaImagen = NuevaFotografia(view, servicio, "Desperfectos")
        activity.formularioViewModel.insertarImagen(multimedia = nuevaImagen)
    }
    binding.boton2.setOnClickListener {
        val nuevoVideo = NuevoVideo(view, servicio, "Desperfectos")
        activity.formularioViewModel.insertarImagen(multimedia = nuevoVideo)
    }

    binding.botonAtras.setOnClickListener {
        if (servicio.descripcionDos != null) {
            servicio.faseFormulario = 2
        } else if (servicio.descripcionTres != null) {
            servicio.faseFormulario = 3
        } else if (servicio.descripcionCuatro != null) {
            servicio.faseFormulario = 4
        } else if (servicio.descripcionCinco != null) {
            servicio.faseFormulario = 5
        } else {
            servicio.faseFormulario = 1
        }
        activity.ubicacion()
    }
    binding.botonCancelar.setOnClickListener { activity.dialogCancelar() }
    binding.botonSiguiente.setOnClickListener {
        if (servicio.descripcionTres != null && servicio.descripcionTres == "Corredera") {
            if (binding.checkBox1.isChecked) {
                servicio.desperfectos = "La ${servicio.descripcionUno} rueda bien, "
            }
            if (binding.checkBox2.isChecked) {
                servicio.desperfectos = "La ${servicio.descripcionUno} no rueda bien, "
            }
            if (binding.checkBox3.isChecked) {
                servicio.desperfectos += "cierra bien con el marco "
            }
            if (binding.checkBox4.isChecked) {
                servicio.desperfectos += "no cierra bien con el marco "
            }
            if (binding.checkBox5.isChecked) {
                servicio.desperfectos += "y funciona el pestillo."
            }
            if (binding.checkBox6.isChecked) {
                servicio.desperfectos += "y no funciona el pestillo."
            }
            if (!binding.checkBox1.isChecked && !binding.checkBox2.isChecked ||
                !binding.checkBox3.isChecked && !binding.checkBox4.isChecked ||
                !binding.checkBox5.isChecked && !binding.checkBox6.isChecked
            ) {
                Toast.makeText(
                    activity,
                    "Tienes que contestar las preguntas para continuar.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                if (binding.editText.length() > 0) {
                    servicio.desperfectos += binding.editText.text.toString().trim { it <= ' ' }
                }
                servicio.faseFormulario = 7
                activity.ubicacion()
            }
        }
        if (servicio.descripcionTres != null && servicio.descripcionTres == "Abatible") {
            if (binding.checkBox1.isChecked) {
                servicio.desperfectos = "La ${servicio.descripcionUno} abate bien, "
            }
            if (binding.checkBox2.isChecked) {
                servicio.desperfectos = "La ${servicio.descripcionUno} no abate bien, "
            }
            if (binding.checkBox3.isChecked) {
                servicio.desperfectos += "cierra bien con el marco "
            }
            if (binding.checkBox4.isChecked) {
                servicio.desperfectos += "no cierra bien con el marco "
            }
            if (binding.checkBox5.isChecked) {
                servicio.desperfectos += "y funciona el pestillo."
            }
            if (binding.checkBox6.isChecked) {
                servicio.desperfectos += "y no funciona el pestillo."
            }
            if (!binding.checkBox1.isChecked && !binding.checkBox2.isChecked ||
                !binding.checkBox3.isChecked && !binding.checkBox4.isChecked ||
                !binding.checkBox5.isChecked && !binding.checkBox6.isChecked
            ) {
                Toast.makeText(
                    activity,
                    "Tienes que contestar las preguntas para continuar.",
                    Toast.LENGTH_SHORT
                ).show()
            }
            if (servicio.desperfectos != null) {
                if (binding.editText.length() > 0) {
                    servicio.desperfectos += binding.editText.text.toString().trim { it <= ' ' }
                }
                servicio.faseFormulario = 7
                activity.ubicacion()
            }
        }

        if (binding.linearLayout1.visibility == View.GONE && binding.linearLayout2.visibility == View.GONE && binding.linearLayout3.visibility == View.GONE) {
            servicio.desperfectos = binding.editText.text.toString()
            servicio.faseFormulario = 7
            activity.ubicacion()
        }
    }
}
