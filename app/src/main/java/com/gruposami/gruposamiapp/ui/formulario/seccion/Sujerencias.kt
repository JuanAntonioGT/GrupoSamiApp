package com.gruposami.gruposamiapp.ui.formulario.seccion

import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import com.gruposami.gruposamiapp.databinding.Formulario10Binding
import com.gruposami.gruposamiapp.domain.servicio.model.Servicio
import com.gruposami.gruposamiapp.ui.formulario.FormularioActivity

// 13
fun sujerencias(activity: FormularioActivity, servicio: Servicio) {
    val binding: Formulario10Binding = Formulario10Binding.inflate(activity.layoutInflater)
    val view: View = binding.root
    activity.setContentView(view)

    servicio.faseFormulario = 13
    activity.formularioViewModel.modificarServicio(servicio)

    val checkBox1: CheckBox = binding.checkBox1
    val checkBox2: CheckBox = binding.checkBox2
    val checkBox3: CheckBox = binding.checkBox3
    val editText1: EditText = binding.editText1
    val atras: Button = binding.botonAtras
    val cancelar: Button = binding.botonCancelar
    val siguiente: Button = binding.botonSiguiente
    atras.setOnClickListener {
        servicio.faseFormulario = 12
        activity.ubicacion()
    }
    cancelar.setOnClickListener { activity.dialogCancelar() }
    siguiente.setOnClickListener {
        if (checkBox1.isChecked) {
            servicio.observaciones =
                "${servicio.observaciones} Hablar antes de preparar el material "
        }
        if (checkBox2.isChecked) {
            servicio.observaciones = "${servicio.observaciones} Llevar escalera"
        }
        if (checkBox3.isChecked) {
            servicio.observaciones = "${servicio.observaciones} 2 Operarios"
        }
        if (editText1.length() > 0) {
            // Todo comentario de medicion / instalacion, lo de observaciones...
            servicio.observaciones =
                servicio.observaciones + editText1.text.toString().trim { it <= ' ' }
        }
        servicio.faseFormulario = 14
        activity.formularioViewModel.modificarServicio(servicio)
        activity.ubicacion()
    }
}
