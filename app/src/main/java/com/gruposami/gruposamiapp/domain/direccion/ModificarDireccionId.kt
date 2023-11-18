package com.gruposami.gruposamiapp.domain.direccion

import com.gruposami.gruposamiapp.data.repositories.DireccionRepository
import com.gruposami.gruposamiapp.domain.orden.model.CambioId
import javax.inject.Inject

class ModificarDireccionId @Inject constructor(
    private val direccionRepository: DireccionRepository
) {
    suspend operator fun invoke(direcciones: List<CambioId?>) {
        direcciones.map {
            if (it != null) {
                direccionRepository.modificarDireccionId(it)
            }
        }
    }
}