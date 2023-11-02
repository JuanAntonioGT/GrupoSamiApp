package com.gruposami.gruposamiapp.domain.cliente.model

import com.gruposami.gruposamiapp.data.database.entities.ClienteEntity
import com.gruposami.gruposamiapp.data.network.orden.ClienteResponse

data class Cliente(
    val id: Int,
    val nombre: String?,
    val apellidos: String?,
    val razonSocial: String?,
    val numeroIdentificacion: String?,
    val fechaCreacion: String?,
    val fechaModificacion: String?,
)

fun ClienteResponse.toDomain() = Cliente(
    id = id,
    nombre = nombre,
    apellidos = apellidos,
    razonSocial = razonSocial,
    numeroIdentificacion = numeroIdentificacion,
    fechaCreacion = fechaCreacion,
    fechaModificacion = fechaModificacion
)

fun ClienteEntity.toDomain() = Cliente(
    id = id,
    nombre = nombre,
    apellidos = apellidos,
    razonSocial = razonSocial,
    numeroIdentificacion = numeroIdentificacion,
    fechaCreacion = fechaCreacion,
    fechaModificacion = fechaModificacion
)
