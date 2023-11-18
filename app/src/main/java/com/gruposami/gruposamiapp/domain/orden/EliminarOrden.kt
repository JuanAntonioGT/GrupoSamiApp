package com.gruposami.gruposamiapp.domain.orden

import com.gruposami.gruposamiapp.data.repositories.OrdenRepository
import com.gruposami.gruposamiapp.domain.orden.model.Orden
import javax.inject.Inject

class EliminarOrden @Inject constructor(
    private val ordenRepository: OrdenRepository,

    ) {
    suspend operator fun invoke(orden: Orden) {
        ordenRepository.eliminarOrden(orden)
    }
}