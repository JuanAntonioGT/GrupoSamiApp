package com.gruposami.gruposamiapp.domain.servicio.model

import com.gruposami.gruposamiapp.data.database.entities.ServicioPendienteEntity

data class ServicioPendiente(
    val servicioId: Int,
    val userId: Int
)

fun ServicioPendienteEntity.toDomain() = ServicioPendiente(
    servicioId = servicioId,
    userId = userId
)