package com.gruposami.gruposamiapp.domain.servicio

import com.gruposami.gruposamiapp.data.repositories.ServicioRepository
import com.gruposami.gruposamiapp.domain.servicio.model.ServicioEstado
import javax.inject.Inject

class InsertarEstadoServicio @Inject constructor(
    private val servicioRepository: ServicioRepository
) {
    suspend operator fun invoke(servicioEstado: ServicioEstado) {
        servicioRepository.insertarServicioEstado(servicioEstado)
    }
}