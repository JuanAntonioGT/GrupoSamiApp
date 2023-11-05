package com.gruposami.gruposamiapp.domain.servicio

import com.gruposami.gruposamiapp.data.repositories.ServicioRepository
import com.gruposami.gruposamiapp.domain.servicio.model.Servicio
import javax.inject.Inject

class InsertarServicio @Inject constructor(
    private val servicioRepository: ServicioRepository,
) {
    suspend operator fun invoke(servicio: Servicio) {
        servicioRepository.insertarServicioBBDD(servicio)
    }
}