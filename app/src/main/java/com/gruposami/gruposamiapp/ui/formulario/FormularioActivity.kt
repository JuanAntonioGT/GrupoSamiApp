package com.gruposami.gruposamiapp.ui.formulario

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.media.MediaScannerConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.core.content.FileProvider
import com.gruposami.gruposamiapp.R
import com.gruposami.gruposamiapp.databinding.ActivityFormularioBinding
import com.gruposami.gruposamiapp.domain.servicio.model.Servicio
import com.gruposami.gruposamiapp.domain.sesion.model.Sesion
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class FormularioActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFormularioBinding
    val formularioViewModel: FormularioViewModel by viewModels()
    private lateinit var servicio: Servicio

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityFormularioBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        val idServicio = intent.getIntExtra("servicioId", 0)
//
//        formularioViewModel.onCreate(idServicio)
//        formularioViewModel.servicio.observe(this) {
//            servicio = it
//            ubicacion()
//        }
//        backPressed()
//
//    }
//
//    private fun backPressed() {
//        onBackPressedDispatcher.addCallback(
//            this, object : OnBackPressedCallback(true) {
//                override fun handleOnBackPressed() {
//                    // Desactivado
//                }
//            }
//        )
//    }
//
//
//    // Esta es la guia de secciones de un formulario de MEDICION
//    fun ubicacion() {
//        formularioViewModel.modificarServicio(servicio)
//        if (Sesion.filtro_estado == "Medir")
//
//            when (servicio.faseFormulario) {
//                null, 0, 1 -> descripcionUno(this, servicio)
//                2 -> descripcionDos(this, servicio)
//                3 -> descripcionTres(this, servicio)
//                4 -> descripcionCuatro(this, servicio)
//                5 -> descripcionCinco(this, servicio)
//                6 -> desperfectos(this, servicio)
//                7 -> fotografias(this, servicio)
//                8 -> restosVidrio(this, servicio)
//                9 -> seleccionVidrio(this, servicio)
//                10 -> medidas(this, servicio)
//                11 -> sellado(this, servicio)
//                12 -> manufactura(this, servicio)
//                13 -> sujerencias(this, servicio)
//                14 -> resumenMedicion(this, servicio)
//                15 -> pasarelaMedicion(this, servicio)
//            }
//
//        else if (Sesion.filtro_estado == "Montar")
//            when (servicio.faseFormulario) {
////                null, 0, 1 -> descripcionUno(this, servicio)
////                2 -> descripcionDos(this, servicio)
////                3 -> descripcionTres(this, servicio)
////                4 -> descripcionCuatro(this, servicio)
////                5 -> descripcionCinco(this, servicio)
////                1 -> desperfectos(this, servicio)
//                null, 0, 7 -> fotografias(this, servicio)
////                8 -> restosVidrio(this, servicio)
////                9 -> seleccionVidrio(this, servicio)
////                10 -> medidas(this, servicio)
////                11 -> sellado(this, servicio)
////                12 -> manufactura(this, servicio)
////                13 -> sujerencias(this, servicio)
////                2 -> resumenMedicion(this, servicio)
//                15 -> pasarelaMedicion(this, servicio)
//            }
//    }
//
//    fun timestamp(): String? {
//        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
//        return LocalDateTime.now().format(formatter)
//    }
//
//    fun dialogCancelar() {
//        AlertDialog.Builder(this)
//            .setTitle("Salir")
//            .setMessage("\n ¿Quieres salir de la medición?")
//            .setPositiveButton("Si") { _: DialogInterface?, _: Int ->
//                startActivity(Intent(this, OrdenActivity::class.java))
//            }
//            .setNegativeButton("No") { _: DialogInterface?, _: Int -> }.show()
//    }
//
//    fun fotografia(tipo: String): File {
//        val nombre = "${servicio.id} ${tipo}.jpg"
//        val directorio =
//            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES + "/Grupo Sami")
//        if (!directorio.exists()) {
//            directorio.mkdirs()
//        }
//        val archivoFotografia = if (File(directorio, nombre).exists()) {
//            var contador = 0
//            while (File(directorio, "${servicio.id} ${tipo}(${contador}).jpg").exists()) {
//                contador++
//            }
//            File(directorio, "${servicio.id} ${tipo} (${contador}).jpg")
//        } else {
//            File(directorio, nombre)
//        }
//        val uri = FileProvider.getUriForFile(
//            this,
//            "com.gruposami.iris.fileprovider",
//            archivoFotografia
//        )
//
//        val capturaFotografia = Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
//            putExtra("tipo", tipo)
//        }
//        capturaFotografia.putExtra(MediaStore.EXTRA_OUTPUT, uri)
//        fotografiaActivityResultLauncher.launch(capturaFotografia)
//        MediaScannerConnection.scanFile(
//            this, arrayOf(archivoFotografia.toString()), null, null
//        )
//
//        val multimedia = Multimedia(
//            id = System.currentTimeMillis().toInt(),
//            fecha_creacion = timestamp(),
//            fecha_modificacion = timestamp(),
//            ruta = archivoFotografia.toString(),
//            tipo_archivo = "Imagen",
//            categoria_archivo = "Medicion",
//            etiqueta_archivo = tipo,
//            servicio_id = servicio.id
//        )
//        formularioViewModel.insertarImagen(multimedia = multimedia)
//        return archivoFotografia
//    }
//
//    private
//    var fotografiaActivityResultLauncher =
//        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//            if (result.resultCode == RESULT_OK) {
//                val intent = result.data
//                val tipo = intent?.extras?.getString("tipo")
//            }
//        }
//
//    fun video(etiqueta: String) {
//        val nombre = "${servicio.id} ${etiqueta}.mp4"
//        val directorio =
//            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES + "/Grupo Sami")
//        if (!directorio.exists()) {
//            directorio.mkdirs()
//        }
//
//        val archivoVideo = if (File(directorio, nombre).exists()) {
//            var contador = 0
//            while (File(
//                    directorio,
//                    "${servicio.id} ${etiqueta}(${contador}).mp4"
//                ).exists()
//            ) {
//                contador++
//            }
//            File(directorio, "${servicio.id} ${etiqueta} (${contador}).mp4")
//        } else {
//            File(directorio, nombre)
//        }
//
//        val uriComplementaria = FileProvider.getUriForFile(
//            this,
//            "com.gruposami.iris.fileprovider",
//            archivoVideo
//        )
//
//        val capturaVideo = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
//        capturaVideo.putExtra(MediaStore.EXTRA_OUTPUT, uriComplementaria)
//        videoActiveResultLauncher.launch(capturaVideo)
//        MediaScannerConnection.scanFile(
//            this, arrayOf(archivoVideo.toString()), null, null
//        )
//
//        val multimedia = Multimedia(
//            id = System.currentTimeMillis().toInt(),
//            fecha_creacion = timestamp(),
//            fecha_modificacion = timestamp(),
//            ruta = archivoVideo.toString(),
//            tipo_archivo = "Video",
//            categoria_archivo = "Medicion",
//            etiqueta_archivo = etiqueta,
//            servicio_id = servicio.id
//        )
//        formularioViewModel.insertarImagen(multimedia)
//    }
//
//    private
//    var videoActiveResultLauncher =
//        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//            if (result.resultCode == RESULT_OK) {
//            }
//        }
//
//    fun dibujoCristal() {
//        val binding: Formulario07Binding =
//            Formulario07Binding.inflate(layoutInflater)
//        val view: View = binding.root
//        setContentView(view)
//
//        val botonLimpiarFirma: Button = binding.botonLimpiarFirma
//        val signaturePad: SignaturePad = binding.signaturepad
//        val botonSiguiente: Button = binding.botonGuardarFirma
//        val botonAtras: Button = binding.botonAtrasFirma
//        signaturePad.setOnSignedListener(object :
//            SignaturePad.OnSignedListener {
//            override fun onStartSigning() {}
//            override fun onSigned() {
//                botonLimpiarFirma.isEnabled = true
//            }
//
//            override fun onClear() {
//                botonLimpiarFirma.isEnabled = false
//            }
//        })
//        botonLimpiarFirma.setOnClickListener { signaturePad.clear() }
//        botonAtras.setOnClickListener { ubicacion() }
//        botonSiguiente.setOnClickListener {
//            guardarDibujo(
//                servicio,
//                signaturePad.signatureBitmap,
//                "Medicion",
//                "Dibujo"
//            )
//            ubicacion()
//        }
//    }
//
//    fun guardarDibujo(servicio: Servicio, signature: Bitmap, categoria: String, etiqueta: String) {
//        val nombre = "${servicio.id} ${etiqueta}.jpg"
//        val directorio =
//            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES + "/Grupo Sami")
//        if (!directorio.exists()) {
//            directorio.mkdirs()
//        }
//        val archivoDibujo = if (File(directorio, nombre).exists()) {
//            var contador = 0
//            while (File(
//                    directorio,
//                    "${servicio.id} ${etiqueta}(${contador}).jpg"
//                ).exists()
//            ) {
//                contador++
//            }
//            File(directorio, "${servicio.id} ${etiqueta} (${contador}).jpg")
//        } else {
//            File(directorio, nombre)
//        }
//
//        val comprimido = comprimirBitmapJpg(signature, archivoDibujo)
//        MediaScannerConnection.scanFile(
//            this, arrayOf(comprimido.toString()), null, null
//        )
//
//        val multimedia = Multimedia(
//            id = System.currentTimeMillis().toInt(),
//            fecha_creacion = timestamp(),
//            fecha_modificacion = timestamp(),
//            ruta = archivoDibujo.toString(),
//            tipo_archivo = "Imagen",
//            categoria_archivo = categoria,
//            etiqueta_archivo = etiqueta,
//            servicio_id = servicio.id
//        )
//        formularioViewModel.insertarImagen(multimedia = multimedia)
//
//    }
//
//    @Throws(IOException::class)
//    fun comprimirBitmapJpg(imagefirmabitmap: Bitmap, dibujoCristal: File?): File? {
//        val newBitmap = Bitmap.createBitmap(
//            imagefirmabitmap.width,
//            imagefirmabitmap.height,
//            Bitmap.Config.ARGB_8888
//        )
//        val canvas = Canvas(newBitmap)
//        canvas.drawColor(-1)
//        canvas.drawBitmap(imagefirmabitmap, 0.0f, 0.0f, null as Paint?)
//        val stream: OutputStream = FileOutputStream(dibujoCristal)
//        newBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
//        stream.close()
//        return dibujoCristal
//    }

}