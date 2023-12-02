package com.gruposami.gruposamiapp.ui.formulario.seccion

import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.gruposami.gruposamiapp.databinding.Formulario08Binding
import com.gruposami.gruposamiapp.domain.servicio.model.Servicio
import com.gruposami.gruposamiapp.ui.formulario.FormularioActivity

// 11
fun sellado(activity: FormularioActivity, servicio: Servicio) {
    val binding: Formulario08Binding = Formulario08Binding.inflate(activity.layoutInflater)
    val view: View = binding.root
    activity.setContentView(view)

    servicio.faseFormulario = 11
    activity.formularioViewModel.modificarServicio(servicio)

    val checkBox1: CheckBox = binding.checkBox1
    val checkBox2: CheckBox = binding.checkBox2
    val checkBox3: CheckBox = binding.checkBox3
    val checkBox4: CheckBox = binding.checkBox4
    val checkBox5: CheckBox = binding.checkBox5
    val checkBox6: CheckBox = binding.checkBox6
    val checkBox7: CheckBox = binding.checkBox7
    val checkBox8: CheckBox = binding.checkBox8
    val checkBox9: CheckBox = binding.checkBox9
    val checkBox10: CheckBox = binding.checkBox10
    val checkBox11: CheckBox = binding.checkBox11
    val checkBox12: CheckBox = binding.checkBox12
    val editText: EditText = binding.editText1

    val botonAtras: Button = binding.botonAtras
    val botonCancelar: Button = binding.botonCancelar
    val botonSiguiente: Button = binding.botonSiguiente

    botonAtras.setOnClickListener {
        servicio.faseFormulario = 10
        activity.ubicacion()
    }
    botonCancelar.setOnClickListener {
        activity.dialogCancelar()
    }
    botonSiguiente.setOnClickListener {
        if (checkBox1.isChecked) {
            servicio.sellado = "${servicio.sellado} Silicona Neutra Blanca"
        } else {
            servicio.sellado?.replace("Silicona Neutra Blanca", "")
        }
        if (checkBox2.isChecked) {
            servicio.sellado = "${servicio.sellado} Silicona Neutra Negra"
        } else {
            servicio.sellado?.replace("Silicona Neutra Negra", "")
        }
        if (checkBox3.isChecked) {
            servicio.sellado = "${servicio.sellado} Silicona Neutra Transparente"
        } else {
            servicio.sellado?.replace("Silicona Neutra Transparente", "")
        }
        if (checkBox4.isChecked) {
            servicio.sellado = "${servicio.sellado} Silicona Acética Blanca"
        } else {
            servicio.sellado?.replace("Silicona Acética Blanca", "")
        }
        if (checkBox5.isChecked) {
            servicio.sellado = "${servicio.sellado} Silicona Acética Negra"
        } else {
            servicio.sellado?.replace("Silicona Acética Negra", "")
        }
        if (checkBox6.isChecked) {
            servicio.sellado = "${servicio.sellado} Silicona Acética Transparente"
        } else {
            servicio.sellado?.replace("Silicona Acética Transparente", "")
        }
        if (checkBox7.isChecked) {
            servicio.sellado = "${servicio.sellado} Silicona Acrilica Blanca"
        } else {
            servicio.sellado?.replace("Silicona Acrilica Blanca", "")
        }
        if (checkBox8.isChecked) {
            servicio.sellado = "${servicio.sellado} Silicona Acrilica Gris"
        } else {
            servicio.sellado?.replace("Silicona Acrilica Gris", "")
        }
        if (checkBox9.isChecked) {
            servicio.sellado = "${servicio.sellado} Goma Gris"
        } else {
            servicio.sellado?.replace("Goma Gris", "")
        }
        if (checkBox10.isChecked) {
            servicio.sellado = "${servicio.sellado} Goma Negra"
        } else {
            servicio.sellado?.replace("Goma Negra", "")
        }
        if (checkBox11.isChecked) {
            servicio.sellado = "${servicio.sellado} Goma Transparente"
        } else {
            servicio.sellado?.replace("Goma Transparente", "")
        }
        if (checkBox12.isChecked) {
            servicio.sellado = "${servicio.sellado} ${editText.text}"
        } else {
            servicio.sellado?.replace("${editText.text}", "")
        }

        if (servicio.sellado.isNullOrEmpty()) {
            Toast.makeText(
                activity,
                "Debes seleccionar al menos tipo de sellado",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            servicio.faseFormulario = 12
            activity.formularioViewModel.modificarServicio(servicio)
            activity.ubicacion()
        }
    }
}
