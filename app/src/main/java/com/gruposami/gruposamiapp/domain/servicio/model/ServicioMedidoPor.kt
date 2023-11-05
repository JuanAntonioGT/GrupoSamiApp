package com.gruposami.gruposamiapp.domain.servicio.model

import com.gruposami.gruposamiapp.data.database.entities.ServicioMedidoEntity

data class ServicioMedido(
    val servicioId: Int,
    val userId: Int,
)

fun ServicioMedidoEntity.toDomain() = ServicioMedido(
    servicioId = servicioId,
    userId = userId
)