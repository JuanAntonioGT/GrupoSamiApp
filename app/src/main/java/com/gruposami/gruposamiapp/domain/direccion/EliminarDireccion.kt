package com.gruposami.gruposamiapp.domain.direccion

import com.gruposami.gruposamiapp.data.repositories.DireccionRepository
import javax.inject.Inject

class EliminarDireccion @Inject constructor(
    private val direccionRepository: DireccionRepository
) {
    suspend operator fun invoke(direccionId: Int) {
        direccionRepository.eliminarDireccion(direccionId)
    }
}
