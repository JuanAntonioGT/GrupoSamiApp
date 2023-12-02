package com.gruposami.gruposamiapp.ui.formulario

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.github.gcacace.signaturepad.views.SignaturePad
import com.gruposami.gruposamiapp.databinding.ActivityFormularioBinding
import com.gruposami.gruposamiapp.databinding.Formulario07Binding
import com.gruposami.gruposamiapp.domain.servicio.model.Servicio
import com.gruposami.gruposamiapp.domain.sesion.model.Sesion
import com.gruposami.gruposamiapp.ui.formulario.seccion.descripcionCinco
import com.gruposami.gruposamiapp.ui.formulario.seccion.descripcionCuatro
import com.gruposami.gruposamiapp.ui.formulario.seccion.descripcionDos
import com.gruposami.gruposamiapp.ui.formulario.seccion.descripcionTres
import com.gruposami.gruposamiapp.ui.formulario.seccion.descripcionUno
import com.gruposami.gruposamiapp.ui.formulario.seccion.desperfectos
import com.gruposami.gruposamiapp.ui.formulario.seccion.fotografias
import com.gruposami.gruposamiapp.ui.formulario.seccion.manufactura
import com.gruposami.gruposamiapp.ui.formulario.seccion.medidas
import com.gruposami.gruposamiapp.ui.formulario.seccion.pasarelaMedicion
import com.gruposami.gruposamiapp.ui.formulario.seccion.restosVidrio
import com.gruposami.gruposamiapp.ui.formulario.seccion.resumenMedicion
import com.gruposami.gruposamiapp.ui.formulario.seccion.seleccionVidrio
import com.gruposami.gruposamiapp.ui.formulario.seccion.sellado
import com.gruposami.gruposamiapp.ui.formulario.seccion.sujerencias
import com.gruposami.gruposamiapp.ui.orden.OrdenActivity
import com.gruposami.gruposamiapp.utils.GuardarDibujo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FormularioActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFormularioBinding
    val formularioViewModel: FormularioViewModel by viewModels()
    private lateinit var servicio: Servicio

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormularioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val idServicio = intent.getIntExtra("servicioId", 0)

        formularioViewModel.onCreate(idServicio)
        formularioViewModel.servicio.observe(this) {
            servicio = it
            ubicacion()
        }
        backPressed()

    }

    private fun backPressed() {
        onBackPressedDispatcher.addCallback(
            this, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    // Desactivado
                }
            }
        )
    }


    // Esta es la guia de secciones de un formulario de MEDICION
    fun ubicacion() {
        formularioViewModel.modificarServicio(servicio)
        if (Sesion.filtroEstado == "Medir") {
            when (servicio.faseFormulario) {
                null, 0, 1 -> descripcionUno(this, servicio)
                2 -> descripcionDos(this, servicio)
                3 -> descripcionTres(this, servicio)
                4 -> descripcionCuatro(this, servicio)
                5 -> descripcionCinco(this, servicio)
                6 -> desperfectos(this, servicio)
                7 -> fotografias(this, servicio)
                8 -> restosVidrio(this, servicio)
                9 -> seleccionVidrio(this, servicio)
                10 -> medidas(this, servicio)
                11 -> sellado(this, servicio)
                12 -> manufactura(this, servicio)
                13 -> sujerencias(this, servicio)
                14 -> resumenMedicion(this, servicio)
                15 -> pasarelaMedicion(this, servicio)
            }
        } else if (Sesion.filtroEstado == "Montar") {
            when (servicio.faseFormulario) {
                // Todo: Hacer una vista que tenga las 3 imágenes, eso está bien PERO que luego
                //  te mande a poner comentarios de la instalación y que, en el futuro, añadir compañeros.

                null, 0, 7 -> fotografias(this, servicio)
                14 -> resumenMedicion(this, servicio)
                15 -> pasarelaMedicion(this, servicio)
            }
        }
    }

    fun dialogCancelar() {
        AlertDialog.Builder(this)
            .setTitle("Salir")
            .setMessage("\n ¿Quieres salir del formulario?")
            .setPositiveButton("Si") { _: DialogInterface?, _: Int ->
                startActivity(Intent(this, OrdenActivity::class.java))
            }
            .setNegativeButton("No") { _: DialogInterface?, _: Int -> }.show()
    }

    fun dibujoCristal() {
        val binding: Formulario07Binding =
            Formulario07Binding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        val botonLimpiarFirma: Button = binding.botonLimpiarFirma
        val signaturePad: SignaturePad = binding.signaturepad
        val botonSiguiente: Button = binding.botonGuardarFirma
        val botonAtras: Button = binding.botonAtrasFirma
        signaturePad.setOnSignedListener(object :
            SignaturePad.OnSignedListener {
            override fun onStartSigning() {}
            override fun onSigned() {
                botonLimpiarFirma.isEnabled = true
            }

            override fun onClear() {
                botonLimpiarFirma.isEnabled = false
            }
        })
        botonLimpiarFirma.setOnClickListener { signaturePad.clear() }
        botonAtras.setOnClickListener { ubicacion() }
        botonSiguiente.setOnClickListener {
            GuardarDibujo(
                view,
                servicio,
                signaturePad.signatureBitmap,
                "Medicion",
                "Dibujo"
            )
            ubicacion()
        }
    }

}
