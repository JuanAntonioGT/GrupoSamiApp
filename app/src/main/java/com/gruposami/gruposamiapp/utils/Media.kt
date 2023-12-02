package com.gruposami.gruposamiapp.utils

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.media.MediaScannerConnection
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import androidx.core.content.FileProvider
import com.gruposami.gruposamiapp.domain.multimedia.model.Multimedia
import com.gruposami.gruposamiapp.domain.servicio.model.Servicio
import com.gruposami.gruposamiapp.domain.sesion.model.Sesion
import com.gruposami.gruposamiapp.ui.formulario.FormularioViewModel
import com.gruposami.gruposamiapp.ui.orden.OrdenViewModel
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream


fun NuevaFotografia(itemView: View, servicio: Servicio, tipo: String): Multimedia {
    val nombre = "${servicio.id} ${tipo}.jpg"
    val directorio =
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES + "/Grupo Sami")
    if (!directorio.exists()) {
        directorio.mkdirs()
    }
    val archivoFotografia = if (File(directorio, nombre).exists()) {
        var contador = 0
        while (File(directorio, "${servicio.id} ${tipo}(${contador}).jpg").exists()) {
            contador++
        }
        File(directorio, "${servicio.id} ${tipo} (${contador}).jpg")
    } else {
        File(directorio, nombre)
    }
    val uri = FileProvider.getUriForFile(
        itemView.context,
        "com.gruposami.iris.fileprovider",
        archivoFotografia
    )

    val capturaFotografia = Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
        putExtra("tipo", tipo)
    }
    capturaFotografia.putExtra(MediaStore.EXTRA_OUTPUT, uri)
    itemView.context.startActivity(capturaFotografia)
    MediaScannerConnection.scanFile(
        itemView.context, arrayOf(archivoFotografia.toString()), null, null
    )

    val multimedia = Multimedia(
        id = System.currentTimeMillis().toInt(),
        fechaCreacion = timestamp(),
        fechaModificacion = timestamp(),
        ruta = archivoFotografia.toString(),
        tipoArchivo = "Imagen",
        categoriaArchivo = Sesion.filtroEstado,
        etiquetaArchivo = tipo,
        servicioId = servicio.id
    )
    return multimedia

}

fun NuevoVideo(itemView: View, servicio: Servicio, etiqueta: String): Multimedia {
    val nombre = "${servicio.id} ${etiqueta}.mp4"
    val directorio =
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES + "/Grupo Sami")
    if (!directorio.exists()) {
        directorio.mkdirs()
    }

    val archivoVideo = if (File(directorio, nombre).exists()) {
        var contador = 0
        while (File(
                directorio,
                "${servicio.id} ${etiqueta}(${contador}).mp4"
            ).exists()
        ) {
            contador++
        }
        File(directorio, "${servicio.id} ${etiqueta} (${contador}).mp4")
    } else {
        File(directorio, nombre)
    }

    val uri = FileProvider.getUriForFile(
        itemView.context,
        "com.gruposami.iris.fileprovider",
        archivoVideo
    )

    val capturaVideo = Intent(MediaStore.ACTION_VIDEO_CAPTURE).apply {
        putExtra("tipo", etiqueta)
    }
    capturaVideo.putExtra(MediaStore.EXTRA_OUTPUT, uri)
    itemView.context.startActivity(capturaVideo)
    MediaScannerConnection.scanFile(
        itemView.context, arrayOf(archivoVideo.toString()), null, null
    )

    val multimedia = Multimedia(
        id = System.currentTimeMillis().toInt(),
        fechaCreacion = timestamp(),
        fechaModificacion = timestamp(),
        ruta = archivoVideo.toString(),
        tipoArchivo = "Video",
        categoriaArchivo = Sesion.filtroEstado,
        etiquetaArchivo = etiqueta,
        servicioId = servicio.id
    )
    return multimedia
}

fun GuardarDibujo (
    view: View,
    servicio: Servicio,
    signature: Bitmap,
    categoria: String,
    etiqueta: String,

) : Multimedia{
    val nombre = "${servicio.id} ${etiqueta}.jpg"
    val directorio =
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES + "/Grupo Sami")
    if (!directorio.exists()) {
        directorio.mkdirs()
    }
    val archivoDibujo = if (File(directorio, nombre).exists()) {
        var contador = 0
        while (File(
                directorio,
                "${servicio.id} ${etiqueta}(${contador}).jpg"
            ).exists()
        ) {
            contador++
        }
        File(directorio, "${servicio.id} ${etiqueta} (${contador}).jpg")
    } else {
        File(directorio, nombre)
    }

    val comprimido = comprimirBitmapJpg(signature, archivoDibujo)
    MediaScannerConnection.scanFile(
        view.context, arrayOf(comprimido.toString()), null, null
    )

    val multimedia = Multimedia(
        id = System.currentTimeMillis().toInt(),
        fechaCreacion = timestamp(),
        fechaModificacion = timestamp(),
        ruta = archivoDibujo.toString(),
        tipoArchivo = "Imagen",
        categoriaArchivo = categoria,
        etiquetaArchivo = etiqueta,
        servicioId = servicio.id
    )
    return multimedia

}

@Throws(IOException::class)
fun comprimirBitmapJpg(imagefirmabitmap: Bitmap, dibujoCristal: File?): File? {
    val newBitmap = Bitmap.createBitmap(
        imagefirmabitmap.width,
        imagefirmabitmap.height,
        Bitmap.Config.ARGB_8888
    )
    val canvas = Canvas(newBitmap)
    canvas.drawColor(-1)
    canvas.drawBitmap(imagefirmabitmap, 0.0f, 0.0f, null as Paint?)
    val stream: OutputStream = FileOutputStream(dibujoCristal)
    newBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
    stream.close()
    return dibujoCristal
}

