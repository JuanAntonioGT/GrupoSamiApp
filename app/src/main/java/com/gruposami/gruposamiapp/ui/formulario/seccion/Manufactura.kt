package com.gruposami.gruposamiapp.ui.formulario.seccion

import android.graphics.Bitmap
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import com.gruposami.gruposamiapp.databinding.Formulario09Binding
import com.gruposami.gruposamiapp.domain.servicio.model.Servicio
import com.gruposami.gruposamiapp.ui.formulario.FormularioActivity
import com.gruposami.gruposamiapp.utils.GuardarDibujo

import java.io.IOException

// 12
fun manufactura(activity: FormularioActivity, servicio: Servicio) {
    val binding: Formulario09Binding = Formulario09Binding.inflate(activity.layoutInflater)
    val view: View = binding.root
    activity.setContentView(view)

    servicio.faseFormulario = 12
    activity.formularioViewModel.modificarServicio(servicio)

    binding.textView130.text = ("${servicio.ofertadaAlto} CM")
    binding.textView133.text = ("${servicio.ofertadaAlto} CM")
    binding.textView131.text = ("${servicio.ofertadaAncho} CM")
    binding.textView132.text = ("${servicio.ofertadaAncho} CM")
    binding.textView330.text = ("${servicio.ofertadaAlto} CM")
    binding.textView331.text = ("${servicio.ofertadaAncho} CM")
    binding.textView332.text = ("${servicio.ofertadaAncho} CM")
    binding.textView333.text = ("${servicio.ofertadaAlto} CM")
    binding.textView230.text = ("${servicio.ofertadaAlto} CM")
    binding.textView231.text = ("${servicio.ofertadaAncho} CM")
    binding.textView232.text = ("${servicio.ofertadaAncho} CM")
    binding.textView233.text = ("${servicio.ofertadaAlto} CM")
    binding.textView150.text = ("${servicio.ofertadaAlto} CM")
    binding.textView151.text = ("${servicio.ofertadaAncho} CM")
    binding.textView152.text = ("${servicio.ofertadaAncho} CM")
    binding.textView153.text = ("${servicio.ofertadaAlto} CM")

    val linearCantoPulido: LinearLayout = binding.linearlayoutcantopulido
    linearCantoPulido.visibility = View.GONE
    binding.textView128.setOnClickListener {
        if (linearCantoPulido.visibility == View.GONE) {
            linearCantoPulido.visibility = View.VISIBLE
        } else {
            linearCantoPulido.visibility = View.GONE
        }
    }
    val layoutCantoPulido: ConstraintLayout = binding.dibujocantopulido
    val ladocortoizquiero: CheckBox = binding.checkBox62
    val ladocortoderecho: CheckBox = binding.checkBox63
    val ladolargosuperior: CheckBox = binding.checkBox64
    val ladolargoinferior: CheckBox = binding.checkBox65
    val linearLayoutCurvas: LinearLayout = binding.linearlayoutCurvas
    linearLayoutCurvas.visibility = View.GONE
    binding.textView129.setOnClickListener {
        if (linearLayoutCurvas.visibility == View.GONE) {
            linearLayoutCurvas.visibility = View.VISIBLE
        } else {
            linearLayoutCurvas.visibility = View.GONE
        }
    }
    val layoutCurvas: ConstraintLayout = binding.dibujoCurvas
    val editText1: EditText = binding.editTextNumberDecimal10
    val editText2: EditText = binding.editTextNumberDecimal11
    val editText3: EditText = binding.editTextNumberDecimal12
    val editText4: EditText = binding.editTextNumberDecimal13
    val linearLayoutBisel: LinearLayout = binding.linearLayoutBisel
    linearLayoutBisel.visibility = View.GONE
    binding.textView135.setOnClickListener {
        if (linearLayoutBisel.visibility == View.GONE) {
            linearLayoutBisel.visibility = View.VISIBLE
        } else {
            linearLayoutBisel.visibility = View.GONE
        }
    }
    val layoutBisel: ConstraintLayout = binding.dibujoBisel
    val editText5: EditText = binding.editTextNumberDecimal14
    val editText6: EditText = binding.editTextNumberDecimal15
    val editText7: EditText = binding.editTextNumberDecimal16
    val editText8: EditText = binding.editTextNumberDecimal17
    val linearLayoutEsquinasCortadas: LinearLayout =
        binding.linearlayoutEsquinasCortadas
    linearLayoutEsquinasCortadas.visibility = View.GONE
    binding.textViewesquinascortadas.setOnClickListener {
        if (linearLayoutEsquinasCortadas.visibility == View.GONE) {
            linearLayoutEsquinasCortadas.visibility = View.VISIBLE
        } else {
            linearLayoutEsquinasCortadas.visibility = View.GONE
        }
    }
    val layoutEsquinasCortadas: ConstraintLayout = binding.dibujoEsquinasCortadas
    val editText9: EditText = binding.editTextNumberDecimal18
    val editText10: EditText = binding.editTextNumberDecimal19
    val editText11: EditText = binding.editTextNumberDecimal20
    val editText12: EditText = binding.editTextNumberDecimal21
    val editText13: EditText = binding.editTextNumberDecimal22
    val editText14: EditText = binding.editTextNumberDecimal23
    val editText15: EditText = binding.editTextNumberDecimal24
    val editText16: EditText = binding.editTextNumberDecimal25
    val editText: EditText = binding.editTextNumberDecimal
    val editText17: EditText = binding.editTextNumberDecimal2
    val editText18: EditText = binding.editTextNumberDecimal3
    val editText19: EditText = binding.editTextNumberDecimal4
    val checkBoxCP: CheckBox = binding.checkBoxf50051
    val checkBoxCPInd: CheckBox = binding.checkBoxf50054
    val checkBoxEncaja: CheckBox = binding.checkBox61
    val checkBoxEM: CheckBox = binding.checkBoxf50052
    val checkBoxEMilla: CheckBox = binding.checkBoxf50053
    val editTextCurvas: EditText = binding.editTextf50051
    val editTextBisel: EditText = binding.editTextf50053
    val checkBoxCircular: CheckBox = binding.checkBoxVidrioCircular
    val checkBoxTemplado: CheckBox = binding.checkBoxf50058
    val checkBoxPlantilla: CheckBox = binding.checkBoxf500515
    val checkBoxFormasCajas: CheckBox = binding.checkBoxf500516
    val checkBoxUva: CheckBox = binding.checkBoxf500510
    val checkBoxMuecas: CheckBox = binding.checkBoxf50059
    val checkBoxPechoPaloma: CheckBox = binding.checkBoxf500511
    val editTextTaladros: EditText = binding.editTextf50052
    val checkBoxTaladros: CheckBox = binding.checkBoxf50056
    val checkBoxParrillas: CheckBox = binding.checkBox12
    val editTextParrillas: EditText = binding.editTextTextPersonName2
    val checkBoxSESellado: CheckBox = binding.checkBoxf500512
    val checkBoxSEPegado: CheckBox = binding.checkBoxf500513
    val checkBoxSSDA: CheckBox = binding.checkBoxf500514
    val editTextRecogido: EditText = binding.editTextf50054
    val checkBoxRecogido: CheckBox = binding.checkBoxf500517
    val checkBoxInfoAdi: CheckBox = binding.checkBoxf500518
    val editTextInfoAdi: EditText = binding.editTextf50055
    val checkBoxArena: CheckBox = binding.checkBoxf500519
    val checkBoxAcido: CheckBox = binding.checkBoxf500520
    val checkBoxPintura: CheckBox = binding.checkBoxf500521
    val editTextPintura: EditText = binding.editTextTextPersonName
    val checkBoxTalla: CheckBox = binding.checkBoxf500522
    val checkBoxViniloEspejo: CheckBox = binding.checkBoxf500523
    val checkBoxRotulacion: CheckBox = binding.checkBoxf500524
    val linearOpciones: LinearLayout = binding.linearMasOpciones
    linearOpciones.visibility = View.GONE
    val textViewOpciones: TextView = binding.textView15
    textViewOpciones.setOnClickListener { v: View? ->
        if (linearOpciones.visibility == View.GONE) {
            linearOpciones.visibility = View.VISIBLE
        } else {
            linearOpciones.visibility = View.GONE
        }
    }
    val atras: Button = binding.botonAtras
    val cancelar: Button = binding.botonCancelar
    val siguiente: Button = binding.botonSiguiente

    atras.setOnClickListener {
        servicio.faseFormulario = 11
        activity.ubicacion()
    }
    cancelar.setOnClickListener {
        activity.dialogCancelar()
    }
    siguiente.setOnClickListener {
        servicio.manufacturaA = ""
        if (checkBoxCP.isChecked) {
            servicio.manufacturaA = servicio.manufacturaA + "Cantos Pulidos "
        }
        if (checkBoxCPInd.isChecked) {
            servicio.manufacturaA = servicio.manufacturaA + "Canto Pulido Industrial "
        }
        if (checkBoxEncaja.isChecked) {
            servicio.manufacturaA = servicio.manufacturaA + "Encaja "
        }
        if (checkBoxEM.isChecked) {
            servicio.manufacturaA = servicio.manufacturaA + "Esquinas Matadas "
        }
        if (checkBoxEMilla.isChecked) {
            servicio.manufacturaA = servicio.manufacturaA + "Esquinas Milla "
        }
        if (editTextCurvas.text.toString().length > 0) {
            servicio.manufacturaA =
                servicio.manufacturaA + " 4 Curvas de " + editTextCurvas.text.toString()
        }
        if (editTextBisel.text.toString().length > 0) {
            servicio.manufacturaA =
                servicio.manufacturaA + "4 Lados con Bisel de " + editTextBisel.text
                    .toString()
        }
        if (checkBoxCircular.isChecked) {
            servicio.manufacturaA = servicio.manufacturaA + "Vidrio Circular "
        }
        if (checkBoxTemplado.isChecked) {
            servicio.manufacturaA = servicio.manufacturaA + "Templado "
        }
        if (checkBoxPlantilla.isChecked) {
            servicio.manufacturaA = servicio.manufacturaA + "Plantilla "
        }
        if (checkBoxFormasCajas.isChecked) {
            servicio.manufacturaA = servicio.manufacturaA + "Vidrio con formas o cajas "
        }
        if (checkBoxUva.isChecked) {
            servicio.manufacturaA = servicio.manufacturaA + "Uva "
        }
        if (checkBoxMuecas.isChecked) {
            servicio.manufacturaA = servicio.manufacturaA + "Muecas "
        }
        if (checkBoxPechoPaloma.isChecked) {
            servicio.manufacturaA = servicio.manufacturaA + "Pecho Paloma "
        }
        if (checkBoxTaladros.isChecked || editTextTaladros.text.toString().length > 0) {
            servicio.manufacturaA =
                servicio.manufacturaA + "Taladros de " + editTextTaladros.text.toString()
        }
        if (checkBoxParrillas.isChecked || editTextParrillas.text
                .toString().length > 0
        ) {
            servicio.manufacturaA =
                servicio.manufacturaA + "2 Parrillas de espejo de: " + editTextParrillas.text
                    .toString() + "\n"
        }
        if (checkBoxSESellado.isChecked) {
            servicio.manufacturaA =
                servicio.manufacturaA + "Silicona Estructural de Sellado "
        }
        if (checkBoxSEPegado.isChecked) {
            servicio.manufacturaA =
                servicio.manufacturaA + "Silicona Estructural de Pegado "
        }
        if (checkBoxSSDA.isChecked) {
            servicio.manufacturaA =
                servicio.manufacturaA + "Silicona Sellado Neutro - Doble Acristalamiento "
        }
        if (checkBoxInfoAdi.isChecked || editTextInfoAdi.text.toString().length > 0) {
            servicio.manufacturaA =
                servicio.manufacturaA + "Informacion Adicional: " + editTextInfoAdi.text
                    .toString() + "\n"
        }
        if (checkBoxRecogido.isChecked || editTextRecogido.text.toString().length > 0) {
            servicio.manufacturaA =
                servicio.manufacturaA + "Recogido: " + editTextRecogido.text.toString() + " "
        }
        if (checkBoxArena.isChecked) {
            servicio.manufacturaA = servicio.manufacturaA + "Arena "
        }
        if (checkBoxAcido.isChecked) {
            servicio.manufacturaA = servicio.manufacturaA + "Ácido "
        }
        if (checkBoxPintura.isChecked || editTextPintura.text.toString().length > 0) {
            servicio.manufacturaA =
                servicio.manufacturaA + "Pintura: " + editTextPintura.text.toString() + " "
        }
        if (checkBoxTalla.isChecked) {
            servicio.manufacturaA = servicio.manufacturaA + "Talla "
        }
        if (checkBoxViniloEspejo.isChecked) {
            servicio.manufacturaA = servicio.manufacturaA + "Vinilo para Espejos "
        }
        if (checkBoxRotulacion.isChecked) {
            servicio.manufacturaA = servicio.manufacturaA + "Rotulación "
        }
        if (ladocortoizquiero.isChecked || ladocortoderecho.isChecked || ladolargosuperior.isChecked || ladolargoinferior.isChecked) {
            layoutCantoPulido.isDrawingCacheEnabled = true
            layoutCantoPulido.measure(
                View.MeasureSpec.makeMeasureSpec(
                    0,
                    View.MeasureSpec.UNSPECIFIED
                ),
                View.MeasureSpec.makeMeasureSpec(
                    0,
                    View.MeasureSpec.UNSPECIFIED
                )
            )
            layoutCantoPulido.layout(
                0,
                0,
                layoutCantoPulido.measuredWidth,
                layoutCantoPulido.measuredHeight
            )
            layoutCantoPulido.buildDrawingCache(true)
            val bitmapCP = Bitmap.createBitmap(layoutCantoPulido.drawingCache)
            layoutCantoPulido.isDrawingCacheEnabled = false
            try {
                val dibujo = GuardarDibujo(view, servicio,bitmapCP, "Manufactura", "Canto Pulido")
                activity.formularioViewModel.insertarImagen(dibujo)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        if (editText1.text.toString().isNotEmpty() || editText2.text
                .toString().isNotEmpty() || editText3.text
                .toString().isNotEmpty() || editText4.text.toString().isNotEmpty()
        ) {
            layoutCurvas.isDrawingCacheEnabled = true
            layoutCurvas.measure(
                View.MeasureSpec.makeMeasureSpec(
                    0,
                    View.MeasureSpec.UNSPECIFIED
                ),
                View.MeasureSpec.makeMeasureSpec(
                    0,
                    View.MeasureSpec.UNSPECIFIED
                )
            )
            layoutCurvas.layout(
                0,
                0,
                layoutCurvas.measuredWidth,
                layoutCurvas.measuredHeight
            )
            layoutCurvas.buildDrawingCache(true)
            val bitmapCurvas = Bitmap.createBitmap(layoutCurvas.drawingCache)
            layoutCurvas.isDrawingCacheEnabled = false
            try {
                val dibujo = GuardarDibujo(view, servicio,bitmapCurvas, "Manufactura", "Curvas")
                activity.formularioViewModel.insertarImagen(dibujo)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        if (editText5.text.toString().isNotEmpty() || editText6.text.toString().isNotEmpty() ||
            editText7.text.toString().isNotEmpty() || editText8.text.toString().isNotEmpty()) {
            layoutBisel.isDrawingCacheEnabled = true
            layoutBisel.measure(
                View.MeasureSpec.makeMeasureSpec(
                    0,
                    View.MeasureSpec.UNSPECIFIED
                ),
                View.MeasureSpec.makeMeasureSpec(
                    0,
                    View.MeasureSpec.UNSPECIFIED
                )
            )
            layoutBisel.layout(
                0,
                0,
                layoutBisel.measuredWidth,
                layoutBisel.measuredHeight
            )
            layoutBisel.buildDrawingCache(true)
            val bitmapBisel = Bitmap.createBitmap(layoutBisel.drawingCache)
            layoutBisel.isDrawingCacheEnabled = false
            try {
                val dibujo = GuardarDibujo(view, servicio,bitmapBisel, "Manufactura", "Bisel")
                activity.formularioViewModel.insertarImagen(dibujo)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        if (editText9.text.toString().length > 0 || editText10.text
                .toString().length > 0 || editText11.text
                .toString().length > 0 || editText12.text.toString().length > 0 || editText13.text.toString().length > 0 || editText14.text
                .toString().length > 0 || editText15.text
                .toString().length > 0 || editText16.text.toString().length > 0 || editText.text.toString().length > 0 || editText17.text
                .toString().length > 0 || editText18.text
                .toString().length > 0 || editText19.text.toString().length > 0
        ) {
            linearLayoutEsquinasCortadas.isDrawingCacheEnabled = true
            linearLayoutEsquinasCortadas.measure(
                View.MeasureSpec.makeMeasureSpec(
                    0,
                    View.MeasureSpec.UNSPECIFIED
                ),
                View.MeasureSpec.makeMeasureSpec(
                    0,
                    View.MeasureSpec.UNSPECIFIED
                )
            )
            linearLayoutEsquinasCortadas.layout(
                0,
                0,
                linearLayoutEsquinasCortadas.measuredWidth,
                linearLayoutEsquinasCortadas.measuredHeight
            )
            linearLayoutEsquinasCortadas.buildDrawingCache(true)
            val bitmapEC =
                Bitmap.createBitmap(linearLayoutEsquinasCortadas.drawingCache)
            linearLayoutEsquinasCortadas.isDrawingCacheEnabled = false
            try {

                val dibujo = GuardarDibujo(view, servicio,bitmapEC, "Manufactura", "EsquinasCortadas")
                activity.formularioViewModel.insertarImagen(dibujo)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        servicio.faseFormulario = 13
        activity.formularioViewModel.modificarServicio(servicio)
        activity.ubicacion()
    }
}
