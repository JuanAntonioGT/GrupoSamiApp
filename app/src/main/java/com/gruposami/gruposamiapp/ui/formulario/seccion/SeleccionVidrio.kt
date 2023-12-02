package com.gruposami.gruposamiapp.ui.formulario.seccion

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Spinner
import com.gruposami.gruposamiapp.databinding.Formulario05Binding
import com.gruposami.gruposamiapp.domain.servicio.model.Servicio
import com.gruposami.gruposamiapp.ui.formulario.FormularioActivity

// 9
fun seleccionVidrio(activity: FormularioActivity, servicio: Servicio) {
    val binding: Formulario05Binding =
        Formulario05Binding.inflate(activity.layoutInflater)
    val view: View = binding.root
    activity.setContentView(view)

    servicio.faseFormulario = 9
    activity.formularioViewModel.modificarServicio(servicio)

    val botonVidrioSimple: Button = binding.botonSencillo
    val botonDobleAcristalamiento: Button = binding.botonDoble
    val linearLayout1: LinearLayout = binding.linearLayoutSimple
    val linearLayout2: LinearLayout = binding.linearLayoutDoble
    val spinner1: Spinner = binding.spinner1
    val spinner2: Spinner = binding.spinner2
    val spinner3: Spinner = binding.spinner3
    val spinner4: Spinner = binding.spinner4
    val otrosspinner1: EditText = binding.editText1
    val otrosspinner2: EditText = binding.editText2
    val otrosspinner3: EditText = binding.editText4
    val otrosspinner4: EditText = binding.editText5
    val linearLayoutPalilleria: LinearLayout = binding.linearLayout3
    val checkBoxPalilleria: CheckBox = binding.checkBox1
    val editTextPalilleria: EditText = binding.editText3
    val botonAtras: Button = binding.botonAtras
    val botonCancelar: Button = binding.botonCancelar
    val botonSiguiente: Button = binding.botonSiguiente

    activity.formularioViewModel.obtenerListaVidrios()
    activity.formularioViewModel.obtenerListaVidriosDescatalogados()

    activity.formularioViewModel.listaVidrios.observe(activity) { it ->
        val listaVidrios = ArrayList<String>()
        val listaCamara = ArrayList<String>()

        if (it != null) {
            for (vidrio in it) {
                if (vidrio!!.descripcion!!.startsWith("Camara")) {
                    listaCamara.add(vidrio.descripcion.toString())
                } else {
                    listaVidrios.add(vidrio.descripcion.toString())
                }
            }
            activity.formularioViewModel.listaVidriosDescatalogados.observe(activity) { it2 ->
                it2.map { listaVidrios.add("${it!!.descripcion.toString()} - Descatalogado") }
            }
            listaCamara.add("Otros")
            listaVidrios.add("Otros")

            spinner1.adapter = ArrayAdapter(activity, android.R.layout.simple_spinner_item, listaVidrios)
            spinner2.adapter = ArrayAdapter(activity, android.R.layout.simple_spinner_item, listaVidrios)
            spinner3.adapter = ArrayAdapter(activity, android.R.layout.simple_spinner_item, listaCamara)
            spinner4.adapter = ArrayAdapter(activity, android.R.layout.simple_spinner_item, listaVidrios)

        }

    }

    // Crear una lógica para enseñar uno u otro por defecto
    var opcionSeleccionada: Boolean? = null

    if (!servicio.modeloB.isNullOrEmpty()) {
        opcionSeleccionada = true
    } else if (!servicio.modeloA.isNullOrEmpty()) {
        opcionSeleccionada = false
    } else {
        opcionSeleccionada = null
    }

    when (opcionSeleccionada) {
        null -> {
            linearLayout1.visibility = View.GONE
            linearLayout2.visibility = View.GONE
            otrosspinner1.visibility = View.GONE
            otrosspinner2.visibility = View.GONE
            otrosspinner3.visibility = View.GONE
            otrosspinner4.visibility = View.GONE
            linearLayoutPalilleria.visibility = View.GONE

        }

        false -> {
            linearLayout1.visibility = View.VISIBLE
            linearLayout2.visibility = View.GONE
            otrosspinner1.visibility = View.GONE
            otrosspinner2.visibility = View.GONE
            otrosspinner3.visibility = View.GONE
            otrosspinner4.visibility = View.GONE
            linearLayoutPalilleria.visibility = View.GONE
        }

        true -> {
            linearLayout1.visibility = View.GONE
            linearLayout2.visibility = View.VISIBLE
            otrosspinner1.visibility = View.GONE
            otrosspinner2.visibility = View.GONE
            otrosspinner3.visibility = View.GONE
            otrosspinner4.visibility = View.GONE
            linearLayoutPalilleria.visibility = View.VISIBLE
            editTextPalilleria.visibility = View.GONE
        }
    }

    botonVidrioSimple.setOnClickListener {
        opcionSeleccionada = false
        linearLayout1.visibility = View.VISIBLE
        linearLayout2.visibility = View.GONE
    }
    botonDobleAcristalamiento.setOnClickListener {
        opcionSeleccionada = true
        linearLayout1.visibility = View.GONE
        linearLayout2.visibility = View.VISIBLE
        linearLayoutPalilleria.visibility = View.VISIBLE
        editTextPalilleria.visibility = View.GONE
    }

    spinner1.onItemSelectedListener =
        object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                if (spinner1.selectedItem == "Otros") {
                    otrosspinner1.visibility = View.VISIBLE
                }
                if (spinner1.selectedItem != "Otros") {
                    otrosspinner1.visibility = View.GONE
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

    spinner2.onItemSelectedListener =
        object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                if (spinner2.selectedItem == "Otros") {
                    otrosspinner2.visibility = View.VISIBLE
                }
                if (spinner2.selectedItem != "Otros") {
                    otrosspinner2.visibility = View.GONE
                }
            }


            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    spinner3.onItemSelectedListener =
        object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                if (spinner3.selectedItem == "Otros") {
                    otrosspinner3.visibility = View.VISIBLE
                }
                if (spinner3.selectedItem != "Otros") {
                    otrosspinner3.visibility = View.GONE
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    spinner4.onItemSelectedListener =
        object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                if (spinner4.selectedItem == "Otros") {
                    otrosspinner4.visibility = View.VISIBLE
                }
                if (spinner4.selectedItem != "Otros") {
                    otrosspinner4.visibility = View.GONE
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }


    checkBoxPalilleria.setOnCheckedChangeListener { buttonView: CompoundButton?, isChecked: Boolean ->
        if (isChecked) {
            editTextPalilleria.visibility = View.VISIBLE
        }
        if (!isChecked) {
            editTextPalilleria.visibility = View.GONE
        }
    }

    botonAtras.setOnClickListener {
        servicio.faseFormulario = 8
        activity.ubicacion()
    }
    botonCancelar.setOnClickListener { activity.dialogCancelar() }

    botonSiguiente.setOnClickListener {
        servicio.faseFormulario = 10
        activity.ubicacion()

//        if (linearLayout1.visibility == View.VISIBLE) {
//            servicio.cantidad = 1
//            if (spinner2.selectedItem == "Otros") {
//                servicio.modeloA = otrosspinner2.text.toString()
//            } else {
//                servicio.modeloA =
//                    spinner2.selectedItem.toString()
//            }
//            servicio.camara = null
//            servicio.palilleria = null
//            servicio.modeloB = null
//            activity.filtroCuadroMedicion()
//
//        } else if (linearLayout2.visibility == View.VISIBLE) {
//            servicio.cantidad = 1
//            if (spinner3.selectedItem == "Otros") {
//                servicio.modeloA = otrosspinner3.text.toString()
//            } else {
//                servicio.modeloA =
//                    spinner3.selectedItem.toString()
//            }
//            if (spinner4.selectedItem == "Otros") {
//                servicio.camara = otrosspinner4.text.toString()
//            } else {
//                servicio.camara =
//                    spinner4.selectedItem.toString()
//            }
//            if (checkBoxPalilleria.isChecked) {
//                servicio.palilleria =
//                    editTextPalilleria.text.toString()
//            }
//            if (spinner5.selectedItem == "Otros") {
//                servicio.modeloB = otrosspinner5.text.toString()
//            } else {
//                servicio.modeloB =
//                    spinner5.selectedItem.toString()
//            }
//            activity.filtroCuadroMedicion()
//
//        } else {
//            Toast.makeText(
//                activity,
//                "Debes seleccionar el modelo del cristal",
//                Toast.LENGTH_SHORT
//            )
//                .show()
//        }
    }
}
