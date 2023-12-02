package com.gruposami.gruposamiapp.ui.formulario.seccion

import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import com.gruposami.gruposamiapp.databinding.Formulario04Binding
import com.gruposami.gruposamiapp.domain.servicio.model.Servicio
import com.gruposami.gruposamiapp.ui.formulario.FormularioActivity


// 8
fun restosVidrio(activity: FormularioActivity, servicio: Servicio) {
    val binding: Formulario04Binding =
        Formulario04Binding.inflate(activity.layoutInflater)
    val view: View = binding.root
    activity.setContentView(view)

    servicio.faseFormulario = 8
    activity.formularioViewModel.modificarServicio(servicio)

    val checkBox1: CheckBox = binding.checkBox1
    val checkBox2: CheckBox = binding.checkBox2
    val checkBox3: CheckBox = binding.checkBox3
    val checkBox4: CheckBox = binding.checkBox4
    val botonAtras: Button = binding.botonAtras
    val botonCancelar: Button = binding.botonCancelar
    val botonSiguiente: Button = binding.botonSiguiente

    checkBox1.setOnTouchListener { v: View?, event: MotionEvent? ->
        checkBox2.isChecked = false
        false
    }
    checkBox2.setOnTouchListener { v: View?, event: MotionEvent? ->
        checkBox1.isChecked = false
        false
    }
    checkBox3.setOnTouchListener { v: View?, event: MotionEvent? ->
        checkBox4.isChecked = false
        false
    }
    checkBox4.setOnTouchListener { v: View?, event: MotionEvent? ->
        checkBox3.isChecked = false
        false
    }

    if (servicio.restos == true) {
        checkBox1.isChecked = true
        checkBox2.isChecked = false
    }
    if (servicio.restos == false) {
        checkBox1.isChecked = false
        checkBox2.isChecked = true
    }

    if (servicio.recogido == true) {
        checkBox3.isChecked = true
        checkBox4.isChecked = false
    }
    if (servicio.recogido == false) {
        checkBox3.isChecked = false
        checkBox4.isChecked = true
    }

    botonAtras.setOnClickListener {
        servicio.faseFormulario = 7
        activity.ubicacion()
    }
    botonCancelar.setOnClickListener { activity.dialogCancelar() }
    botonSiguiente.setOnClickListener {
        if ((checkBox1.isChecked || checkBox2.isChecked) && (checkBox3.isChecked || checkBox4.isChecked)) {
            if (checkBox1.isChecked) {
                servicio.restos = true
            }
            if (checkBox2.isChecked) {
                servicio.restos = false
            }
            if (checkBox3.isChecked) {
                servicio.recogido = true
            }
            if (checkBox4.isChecked) {
                servicio.recogido = false
            }
            servicio.faseFormulario = 9
            activity.ubicacion()
        } else {
            Toast.makeText(activity, "Tienes que contestar las preguntas", Toast.LENGTH_SHORT).show()
        }
    }

}
