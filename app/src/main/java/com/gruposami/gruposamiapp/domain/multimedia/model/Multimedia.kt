package com.gruposami.gruposamiapp.domain.multimedia.model

import com.gruposami.gruposamiapp.data.database.entities.MultimediaEntity
import com.gruposami.gruposamiapp.data.network.multimedia.model.MultimediaResponse


data class Multimedia(
    var id: Int?,
    val ruta: String?,
    val tipoArchivo: String?,
    val categoriaArchivo: String?,
    val etiquetaArchivo: String?,
    val servicioId: Int?,
    val fechaCreacion: String?,
    val fechaModificacion: String?,

)

fun MultimediaResponse.toDomain(servicio_id: Int?) = Multimedia(
    id = id,
    ruta = ruta,
    tipoArchivo = tipoArchivo,
    categoriaArchivo = categoriaArchivo,
    etiquetaArchivo = etiquetaArchivo,
    servicioId = servicioId,
    fechaCreacion = fechaCreacion,
    fechaModificacion = fechaModificacion,
)

fun MultimediaEntity.toDomain() = Multimedia(
    id = id,
    ruta = ruta,
    tipoArchivo = tipoArchivo,
    categoriaArchivo = categoriaArchivo,
    etiquetaArchivo = etiquetaArchivo,
    servicioId = servicioId,
    fechaCreacion = fechaCreacion,
    fechaModificacion = fechaModificacion,
)