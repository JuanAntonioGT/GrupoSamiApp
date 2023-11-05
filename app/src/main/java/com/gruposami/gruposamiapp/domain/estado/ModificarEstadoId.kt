package com.gruposami.gruposamiapp.domain.estado


import com.gruposami.gruposamiapp.data.repositories.EstadoRepository
import com.gruposami.gruposamiapp.domain.orden.model.CambioId
import javax.inject.Inject

class ModificarEstadoId @Inject constructor(
    private val estadoRepository: EstadoRepository,
) {

    suspend operator fun invoke(estados: List<CambioId>) {
        estados.map { estadoRepository.modificarEstadoId(it) }
    }

}