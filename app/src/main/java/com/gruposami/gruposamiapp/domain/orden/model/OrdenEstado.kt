package com.gruposami.gruposamiapp.domain.orden.model

import com.gruposami.gruposamiapp.data.database.entities.OrdenEstadoEntity


data class OrdenEstado(
    val ordenId: Int,
    val estadoId: Int
)

fun OrdenEstadoEntity.toDomain() = OrdenEstado(
    ordenId = ordenId,
    estadoId = estadoId
)
