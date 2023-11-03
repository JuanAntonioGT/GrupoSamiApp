package com.gruposami.gruposamiapp.domain.contacto.model

import com.gruposami.gruposamiapp.data.database.entities.ContactoEntity
import com.gruposami.gruposamiapp.data.network.orden.model.ContactoResponse

data class Contacto(
    val id: Int,
    var contacto: String?,
    val tipoContacto: String?,
    var nombre: String?,
    var principal: Boolean?,
    val activo: Boolean?,
    val clienteId: Int?,
    var fechaCreacion: String?,
    var fechaModificacion: String?,
)

fun ContactoResponse.toDomain(clienteId: Int) = Contacto(
    id = id,
    contacto = contacto,
    tipoContacto = tipoContacto,
    nombre = nombre,
    principal = principal,
    activo = activo,
    clienteId = clienteId,
    fechaCreacion = fechaCreacion,
    fechaModificacion = fechaModificacion
)

fun ContactoEntity.toDomain() = Contacto(
    id = id,
    contacto = contacto,
    tipoContacto = tipoContacto,
    nombre = nombre,
    principal = principal,
    activo = activo,
    clienteId = clienteId,
    fechaCreacion = fechaCreacion,
    fechaModificacion = fechaModificacion,
)