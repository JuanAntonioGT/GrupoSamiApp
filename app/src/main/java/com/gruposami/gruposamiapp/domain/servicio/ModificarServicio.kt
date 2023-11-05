package com.gruposami.gruposamiapp.domain.servicio

import com.gruposami.gruposamiapp.data.database.entities.toDatabase
import com.gruposami.gruposamiapp.data.repositories.OrdenRepository
import com.gruposami.gruposamiapp.data.repositories.ServicioRepository
import com.gruposami.gruposamiapp.domain.servicio.model.Servicio
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class ModificarServicio @Inject constructor(
    private val servicioRepository: ServicioRepository,
    private val ordenRepository: OrdenRepository,
) {
    suspend operator fun invoke(servicio: Servicio) {
//        servicio.fechaModificacion = timestamp()
//        servicioRepository.modificarServicio(servicio.toDatabase())
//        ordenRepository.modi(servicio.ordenId, timestamp())
    }


}