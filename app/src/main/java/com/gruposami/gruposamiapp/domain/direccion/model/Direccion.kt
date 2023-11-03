package com.gruposami.gruposamiapp.domain.direccion.model

import com.gruposami.gruposamiapp.data.database.entities.DireccionEntity
import com.gruposami.gruposamiapp.data.network.orden.model.DireccionResponse

data class Direccion(
    val id: Int,
    val direccion: String?,
    val poblacion: String?,
    val codigoPostal: String?,
    val actual: Boolean?,
    val clienteId: Int?,
    val fechaCreacion: String?,
    val fechaModificacion: String?,
)

fun DireccionResponse.toDomain(clienteId: Int) = Direccion(
    id,
    direccion,
    poblacion,
    codigoPostal,
    actual,
    clienteId,
    fechaCreacion,
    fechaModificacion,
)

fun DireccionEntity.toDomain() = Direccion(
    id,
    direccion,
    poblacion,
    codigoPostal,
    actual,
    clienteId,
    fechaCreacion,
    fechaModificacion,
)