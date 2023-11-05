package com.gruposami.gruposamiapp.domain.servicio

import com.gruposami.gruposamiapp.data.repositories.ServicioRepository
import com.gruposami.gruposamiapp.domain.orden.model.CambioId
import javax.inject.Inject

class ModificarServicioId @Inject constructor(
    private val servicioRepository: ServicioRepository,
) {
    suspend operator fun invoke(servicios: List<CambioId?>) {
        servicios.map { servicioRepository.modificarServicioId(it) }
    }
}