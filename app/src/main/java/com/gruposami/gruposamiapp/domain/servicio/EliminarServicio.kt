package com.gruposami.gruposamiapp.domain.servicio

import com.gruposami.gruposamiapp.data.network.orden.model.ServicioResponse
import com.gruposami.gruposamiapp.data.repositories.ServicioRepository
import javax.inject.Inject

class EliminarServicio @Inject constructor(
    private val servicioRepository: ServicioRepository)
{
    suspend operator fun invoke(servicioId: Int) {
        servicioRepository.eliminarServicio(servicioId)
    }
}