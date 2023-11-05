package com.gruposami.gruposamiapp.domain.servicio.model

import com.gruposami.gruposamiapp.data.database.entities.ServicioMontadoEntity

data class ServicioMontado(
    val servicioId: Int,
    val userId: Int
)

fun ServicioMontadoEntity.toDomain() = ServicioMontado(
    servicioId = servicioId,
    userId = userId
)