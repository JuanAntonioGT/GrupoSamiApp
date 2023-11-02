package com.gruposami.gruposamiapp.domain.orden

import com.gruposami.gruposamiapp.data.network.orden.OrdenResponse
import com.gruposami.gruposamiapp.data.repositories.OrdenRepository
import com.gruposami.gruposamiapp.domain.orden.model.toDomain
import javax.inject.Inject

class InsertarOrden @Inject constructor(
    private val ordenRepository: OrdenRepository
) {
    suspend operator fun invoke(ordenResponse: OrdenResponse) {
        ordenRepository.insertarOrden(ordenResponse.toDomain())
    }
}