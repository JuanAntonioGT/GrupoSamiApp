package com.gruposami.gruposamiapp.domain.orden

import com.gruposami.gruposamiapp.data.repositories.OrdenRepository
import com.gruposami.gruposamiapp.domain.orden.model.Orden
import com.gruposami.gruposamiapp.utils.timestamp

import javax.inject.Inject

class ModificarOrden @Inject constructor(
    private val ordenRepository: OrdenRepository,
) {
    suspend operator fun invoke(orden: Orden) {
        orden.fechaModificacion = timestamp()
        ordenRepository.insertarOrden(orden)
    }
}