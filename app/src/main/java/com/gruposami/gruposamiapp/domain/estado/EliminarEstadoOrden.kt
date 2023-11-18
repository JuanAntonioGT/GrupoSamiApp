package com.gruposami.gruposamiapp.domain.estado

import com.gruposami.gruposamiapp.data.repositories.EstadoRepository
import javax.inject.Inject

class EliminarEstadoOrden @Inject constructor(
    private val estadoRepository: EstadoRepository,
) {
    suspend operator fun invoke(estadoId: Int) {
        estadoRepository.eliminarEstado(estadoId)
        estadoRepository.eliminarEstadoOrden(estadoId)
    }

}