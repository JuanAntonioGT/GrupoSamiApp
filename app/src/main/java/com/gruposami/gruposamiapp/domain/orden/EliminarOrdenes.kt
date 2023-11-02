package com.gruposami.gruposamiapp.domain.orden

import com.gruposami.gruposamiapp.data.repositories.OrdenRepository
import javax.inject.Inject

class EliminarOrdenes @Inject constructor(
    private val ordenRepository: OrdenRepository,

    ) {
    suspend operator fun invoke() {
        ordenRepository.eliminarOrdenes()
    }
}