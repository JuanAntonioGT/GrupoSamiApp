package com.gruposami.gruposamiapp.domain.servicio

import com.gruposami.gruposamiapp.data.repositories.ServicioRepository
import com.gruposami.gruposamiapp.domain.servicio.model.Servicio
import com.gruposami.gruposamiapp.domain.servicio.model.ServicioCompleto
import javax.inject.Inject

class ObtenerServicio @Inject constructor(
    private val servicioRepository: ServicioRepository,
) {
    suspend operator fun invoke(idServicio: Int): Servicio {
        return servicioRepository.obtenerServicioPorId(idServicio)
    }
}