package com.gruposami.gruposamiapp.domain.orden.model

import com.gruposami.gruposamiapp.data.database.entities.OrdenEntity
import com.gruposami.gruposamiapp.data.network.orden.model.OrdenResponse


data class Orden(
    val id: Int?,
    val clienteId: Int?,
    val codigoReferencia: String?,
    val numeroReferencia: String?,
    var comentario: String?,
    val finalizado: Boolean?,
    val fechaCreacion: String,
    var fechaModificacion: String,
)

fun OrdenResponse.toDomain() = Orden(
    id = id,
    clienteId = clienteResponse?.id,
    codigoReferencia = codigoReferencia,
    numeroReferencia = numeroReferencia,
    comentario = comentario,
    finalizado = finalizado,
    fechaCreacion = fechaCreacion,
    fechaModificacion = fechaModificacion,
)

fun OrdenEntity.toDomain() = Orden(
    id = id,
    clienteId = clienteId,
    codigoReferencia = codigoReferencia,
    numeroReferencia = numeroReferencia,
    comentario = comentario,
    finalizado = finalizado,
    fechaCreacion = fechaCreacion,
    fechaModificacion = fechaModificacion,
)
