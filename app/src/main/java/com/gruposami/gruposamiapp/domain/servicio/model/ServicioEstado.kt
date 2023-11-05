package com.gruposami.gruposamiapp.domain.servicio.model

import com.gruposami.gruposamiapp.data.database.entities.ServicioEstadoEntity


data class ServicioEstado(
    val servicioId: Int,
    val estadoId: Int,
)

fun ServicioEstadoEntity.toDomain() = ServicioEstado(
    servicioId = servicioId,
    estadoId = estadoId
)
