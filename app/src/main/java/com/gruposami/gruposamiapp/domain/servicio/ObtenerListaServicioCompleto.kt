package com.gruposami.gruposamiapp.domain.servicio

import com.gruposami.gruposamiapp.data.repositories.ServicioRepository
import com.gruposami.gruposamiapp.domain.servicio.model.ServicioCompleto
import javax.inject.Inject

class ObtenerListaServicioCompleto @Inject constructor(
    private val servicioRepository: ServicioRepository,
) {
    suspend operator fun invoke(orden_id: Int): List<ServicioCompleto>{
        return servicioRepository.obtenerServicioPorOrden(orden_id)
    }
}