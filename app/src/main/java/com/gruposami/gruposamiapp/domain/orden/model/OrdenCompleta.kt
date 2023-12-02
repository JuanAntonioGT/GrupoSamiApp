package com.gruposami.gruposamiapp.domain.orden.model

import com.gruposami.gruposamiapp.data.database.entities.OrdenCompletaEntity
import com.gruposami.gruposamiapp.domain.cliente.model.Cliente
import com.gruposami.gruposamiapp.domain.cliente.model.toDomain
import com.gruposami.gruposamiapp.domain.contacto.model.Contacto
import com.gruposami.gruposamiapp.domain.contacto.model.toDomain
import com.gruposami.gruposamiapp.domain.direccion.model.Direccion
import com.gruposami.gruposamiapp.domain.direccion.model.toDomain
import com.gruposami.gruposamiapp.domain.estado.model.Estado
import com.gruposami.gruposamiapp.domain.estado.model.toDomain
import com.gruposami.gruposamiapp.domain.servicio.model.ServicioCompleto
import com.gruposami.gruposamiapp.domain.servicio.model.toDomain


data class OrdenCompleta(
    val orden: Orden,
    val cliente: Cliente,
    val direccion: List<Direccion>,
    val contacto: List<Contacto>,
    val estado: List<Estado>, // Revisar que sea el LAST ID
    val servicioCompleto: List<ServicioCompleto>,
    var horaMovil: String? = null,
)

data class OrdenCompletaResponse(
    val mensaje: String?,
    val direccion: List<CambioId?>,
    val contacto: List<CambioId?>,
    val estado: List<CambioId?>,
    val servicio: List<CambioId?>,
    val firma: List<CambioId?>,
    val multimedia: List<CambioId?>,
    val empleadoMedido: List<CambioId?>,
    val empleadoMontado: List<CambioId?>,
    val empleadoPendiente: List<CambioId?>,
)

data class CambioId(
    val anteriorId: Int,
    val nuevaId: Int
)

fun OrdenCompletaEntity.toDomain() = OrdenCompleta(
    orden = ordenEntity!!.toDomain(),
    cliente = clienteEntity?.toDomain() ?: Cliente(0, null, null, null, null, null, null),
    direccion = direccionEntity.map { it!!.toDomain()  },
    contacto = contactoEntity.map { it!!.toDomain() },
    estado = estadoEntity.map { it!!.toDomain() },
    servicioCompleto = servicioCompletoEntity.map { it!!.toDomain() }
)
