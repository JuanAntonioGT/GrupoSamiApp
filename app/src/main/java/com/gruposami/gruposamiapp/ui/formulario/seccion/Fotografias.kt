package com.gruposami.gruposamiapp.ui.formulario.seccion

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.gruposami.gruposamiapp.databinding.Formulario03Binding
import com.gruposami.gruposamiapp.domain.multimedia.model.Multimedia
import com.gruposami.gruposamiapp.domain.servicio.model.Servicio
import com.gruposami.gruposamiapp.domain.sesion.model.Sesion
import com.gruposami.gruposamiapp.ui.formulario.FormularioActivity
import com.gruposami.gruposamiapp.utils.NuevaFotografia
import com.gruposami.gruposamiapp.utils.NuevoVideo
import java.io.File
import java.util.concurrent.atomic.AtomicInteger

// 7
fun fotografias(activity: FormularioActivity, servicio: Servicio) {
    val binding: Formulario03Binding =
        Formulario03Binding.inflate(activity.layoutInflater)
    val view: View = binding.root
    activity.setContentView(view)
    servicio.faseFormulario = 7
//    if (Sesion.filtroEstado == "Medir") {
//
//    } else {
//        servicio.faseFormulario = 0
//    }

    activity.formularioViewModel.modificarServicio(servicio)

    val fotografiaLejos: Button = binding.boton1
    val imageViewLejos: ImageView = binding.imageView1
    val fotografiaIntermedia: Button = binding.boton2
    val imageViewIntermedia = binding.imageView2
    val fotografiaCerca: Button = binding.boton3
    val imageViewCerca = binding.imageView3
    val anadirFotos: Button = binding.boton4
    val anadirVideos: Button = binding.boton5
    val textViewContadorFotos: TextView = binding.textView1
    val textViewContadorVideos: TextView = binding.textView2
    val botonAtras: Button = binding.botonAtras
    val botonCancelar: Button = binding.botonCancelar
    val botonSiguiente: Button = binding.botonSiguiente
    val contadorFotos = AtomicInteger()
    val contadorVideos = AtomicInteger()

    var photoUriLejos: String? = null
    var photoUriIntermedia: String? = null
    var photoUriCerca: String? = null

    if (photoUriLejos.isNullOrEmpty()) {
        activity.formularioViewModel.obtenerMultimedia(
            Multimedia(
                id = null,
                fechaCreacion = null,
                fechaModificacion = null,
                ruta = null,
                tipoArchivo = "Imagen",
                categoriaArchivo = Sesion.filtroEstado,
                etiquetaArchivo = "Lejos",
                servicioId = servicio.id
            )
        )
    }
    if (photoUriIntermedia.isNullOrEmpty()) {
        activity.formularioViewModel.obtenerMultimedia(
            Multimedia(
                id = null,
                fechaCreacion = null,
                fechaModificacion = null,
                ruta = null,
                tipoArchivo = "Imagen",
                categoriaArchivo = Sesion.filtroEstado,
                etiquetaArchivo = "Intermedia",
                servicioId = servicio.id
            )
        )
    }
    if (photoUriCerca.isNullOrEmpty()) {
        activity.formularioViewModel.obtenerMultimedia(
            Multimedia(
                id = null,
                fechaCreacion = null,
                fechaModificacion = null,
                ruta = null,
                tipoArchivo = "Imagen",
                categoriaArchivo = Sesion.filtroEstado,
                etiquetaArchivo = "Cerca",
                servicioId = servicio.id
            )
        )
    }

    activity.formularioViewModel.multimediaLejosMutable.observe(
        activity,
        Observer {
            if (it != null) {
                if (it.etiquetaArchivo.equals("Lejos") && (it.categoriaArchivo == Sesion.filtroEstado)) {
                    photoUriLejos = it.ruta.toString()
                    Glide.with(activity)
                        .load(File(it.ruta.toString()))
                        .into(imageViewLejos)
                }
            }
        })

    activity.formularioViewModel.multimediaIntermediaMutable.observe(
        activity,
        Observer {
            if (it != null) {
                if (it.etiquetaArchivo.equals("Intermedia") && (it.categoriaArchivo == Sesion.filtroEstado)) {
                    photoUriIntermedia = it.ruta.toString()
                    Glide.with(activity)
                        .load(File(it.ruta.toString()))
                        .into(imageViewIntermedia)
                }
            }
        })

    activity.formularioViewModel.multimediaCercaMutable.observe(
        activity,
        Observer {
            if (it != null) {
                if (it.etiquetaArchivo.equals("Cerca") && (it.categoriaArchivo == Sesion.filtroEstado)) {
                    photoUriCerca = it.ruta.toString()
                    Glide.with(activity)
                        .load(File(it.ruta.toString()))
                        .into(imageViewCerca)
                }
            }
        })

    fotografiaLejos.setOnClickListener {
        val fotografiaLejos = NuevaFotografia(view, servicio, "Lejos")
        activity.formularioViewModel.insertarImagen(fotografiaLejos)
        photoUriLejos = fotografiaLejos.ruta.toString()
        Glide.with(activity).load(File(photoUriLejos.toString())).into(imageViewLejos)
    }
    fotografiaIntermedia.setOnClickListener {
        val fotografiaIntermedia = NuevaFotografia(view, servicio, "Intermedia")
        activity.formularioViewModel.insertarImagen(fotografiaIntermedia)
        photoUriIntermedia = fotografiaIntermedia.ruta.toString()
        Glide.with(activity).load(File(photoUriIntermedia.toString())).into(imageViewIntermedia)
    }
    fotografiaCerca.setOnClickListener {
        val fotografiaCerca = NuevaFotografia(view, servicio, "Cerca")
        activity.formularioViewModel.insertarImagen(fotografiaCerca)
        photoUriCerca = fotografiaCerca.ruta.toString()
        Glide.with(activity).load(photoUriCerca).into(imageViewCerca)
    }

    anadirFotos.setOnClickListener {
        val imagenComplementaria = NuevaFotografia(view, servicio, "Complementaria")
        activity.formularioViewModel.insertarImagen(imagenComplementaria)
        contadorFotos.getAndIncrement()
        textViewContadorFotos.text = "Fotos Añadidas: $contadorFotos"
    }
    anadirVideos.setOnClickListener {
        val videoComplementario = NuevoVideo(view, servicio, "Complementario")
        activity.formularioViewModel.insertarImagen(videoComplementario)
        contadorVideos.getAndIncrement()
        textViewContadorVideos.text = "Vídeos Añadidos: $contadorVideos"
    }

    if (Sesion.filtroEstado.equals("Medir")) {
        botonAtras.setOnClickListener {
            servicio.faseFormulario = 6
            activity.ubicacion()
        }
    } else {
        botonAtras.visibility = View.GONE
    }

    botonCancelar.setOnClickListener { activity.dialogCancelar() }
    botonSiguiente.setOnClickListener {
        if (photoUriIntermedia != null && photoUriCerca != null && photoUriLejos != null) {

            if (Sesion.filtroEstado == "Medir") {
                servicio.faseFormulario = 8
            } else {
                servicio.faseFormulario = 14
            }

            activity.ubicacion()
        } else {
            Toast.makeText(
                activity,
                "Debes hacer todas las fotos",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

}
