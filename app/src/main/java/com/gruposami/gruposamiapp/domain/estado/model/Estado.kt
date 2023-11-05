package com.gruposami.gruposamiapp.domain.estado.model


import com.gruposami.gruposamiapp.data.database.entities.EstadoEntity
import com.gruposami.gruposamiapp.data.network.orden.model.EstadoResponse



data class Estado(
    var id: Int,
    var estado: String?,
    val creadoPor: Int?,
    val fechaCreacion: String?,
    val fechaModificacion: String?,
)

fun EstadoResponse.toDomain() = Estado(
    id = id,
    estado = estado,
    creadoPor = creadoPor,
    fechaCreacion = fechaCreacion,
    fechaModificacion = fechaModificacion,
)

fun EstadoEntity.toDomain() = Estado(
    id = id,
    estado = estado,
    creadoPor = creadoPor,
    fechaCreacion = fechaCreacion,
    fechaModificacion = fechaModificacion,
)
