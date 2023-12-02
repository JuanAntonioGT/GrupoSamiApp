package com.gruposami.gruposamiapp.ui.formulario.seccion

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.gcacace.signaturepad.views.SignaturePad
import com.gruposami.gruposamiapp.databinding.FormularioFirmaBinding
import com.gruposami.gruposamiapp.domain.firma.model.Firma
import com.gruposami.gruposamiapp.domain.multimedia.model.Multimedia
import com.gruposami.gruposamiapp.domain.servicio.model.Servicio
import com.gruposami.gruposamiapp.domain.servicio.model.ServicioCompleto
import com.gruposami.gruposamiapp.ui.formulario.FormularioActivity
import com.gruposami.gruposamiapp.ui.orden.OrdenActivity
import com.gruposami.gruposamiapp.ui.orden.adapters.ServicioAdapter
import com.gruposami.gruposamiapp.utils.GuardarDibujo
import com.gruposami.gruposamiapp.utils.timestamp

fun firmaMedicion(activity: FormularioActivity, idOrden: Int) {
    val binding: FormularioFirmaBinding = FormularioFirmaBinding.inflate(activity.layoutInflater)
    val view: View = binding.root
    activity.setContentView(view)

    val button = binding.botonLimpiar
    val checkBoxConformidad: CheckBox = binding.checkBox1
    val checkBoxEstetica: CheckBox = binding.checkBox2
    val checkBoxIndemnizacion: CheckBox = binding.checkBox3
    val editText: EditText = binding.editText
    val cancelar: Button = binding.botonCancelar
    val siguiente: Button = binding.botonSiguiente
    binding.textView3.text = (
            "El cliente declara estar conforme con el modelo de vidrio, manufactura y medidas tomadas por el reparador.")
    val signaturePad = binding.signaturepad

    signaturePad.setOnSignedListener(object : SignaturePad.OnSignedListener {
        override fun onStartSigning() {}
        override fun onSigned() {}
        override fun onClear() {}
    })
    button.setOnClickListener { signaturePad.clear() }

    val uriFirma: Uri? = null
    var bitmap: Bitmap? = null

    var conformidad: Boolean = false
    var estetica: Boolean = false
    var indemnizacion: Boolean = false
    var comentario: String = ""

    lateinit var adapter: ServicioAdapter
    lateinit var listaServicios: List<ServicioCompleto>
    activity.formularioViewModel.listaServicios.observe(activity) { primero ->

        listaServicios = primero

        for (serv in listaServicios) {
            activity.formularioViewModel.obtenerMultimedia(
                Multimedia(
                    id = null,
                    fechaCreacion = null,
                    fechaModificacion = null,
                    ruta = null,
                    tipoArchivo = "Imagen",
                    categoriaArchivo = "Medicion",
                    etiquetaArchivo = "Intermedia",
                    servicioId = serv.servicio.id
                )
            )
            activity.formularioViewModel.multimediaIntermediaMutable.observe(
                activity
            ) { segundo ->
                if (segundo != null) {
                    // println("Segundo ${segundo.toString()}")
                    adapter = ServicioAdapter(primero, false, null)
                    binding.recyclerFirmando.layoutManager = LinearLayoutManager(activity)
                    binding.recyclerFirmando.itemAnimator = DefaultItemAnimator()
                }
            }
        }
    }

    if (!checkBoxConformidad.isChecked) {
        checkBoxConformidad.isChecked = true
    }


    cancelar.setOnClickListener {
        AlertDialog.Builder(activity)
            .setTitle("Cancelar")
            .setMessage("\n ¿Seguro que quieres cancelar la medidion?")
            .setPositiveButton(
                "Confirmar"
            ) { _: DialogInterface?, _: Int ->
                activity.startActivity(Intent(activity, OrdenActivity::class.java))
            }
            .setNegativeButton(
                "Cancelar"
            ) { _: DialogInterface?, _: Int -> }.show()
    }

    siguiente.setOnClickListener {



        if (checkBoxConformidad.isChecked) {
            conformidad = true
        }
        if (!checkBoxConformidad.isChecked) {
            conformidad = false
        }
        if (checkBoxEstetica.isChecked) {
            estetica = true
        }
        if (!checkBoxEstetica.isChecked) {
            estetica = false
        }
        if (checkBoxIndemnizacion.isChecked) {
            indemnizacion = true
        }
        if (!checkBoxIndemnizacion.isChecked) {
            indemnizacion = false
        }
        comentario = if (editText.length() > 1) {
            editText.text.toString()
        } else {
            ""
        }

        for (serv in listaServicios) {
            val nuevaFirma = GuardarDibujo(
                view,
                serv.servicio,
                signaturePad.signatureBitmap,
                "Firma",
                "Medicion",
            )
            activity.formularioViewModel.insertarImagen(multimedia = nuevaFirma)
            if (serv.servicio.medido == true) {
                val firma = Firma(
                    id = serv.servicio.id,
                    tipoFirma= "Medición",
                    conformidad = conformidad,
                    estetica = estetica,
                    indemnizacion = indemnizacion,
                    comentario = comentario,
                    multimedia = null,
                    fechaCreacion = timestamp(),
                    fechaModificacion = timestamp(),
                    servicioId = serv.servicio.id
                )
                activity.formularioViewModel.insertarFirma(firma)
            }
        }
        activity.startActivity(Intent(activity, OrdenActivity::class.java))
    }


}
