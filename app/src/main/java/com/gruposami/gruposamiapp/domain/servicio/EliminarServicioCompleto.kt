package com.gruposami.gruposamiapp.domain.servicio

import com.gruposami.gruposamiapp.data.repositories.ServicioRepository
import com.gruposami.gruposamiapp.domain.estado.EliminarEstadoServicio
import com.gruposami.gruposamiapp.domain.servicio.model.ServicioCompleto
import javax.inject.Inject

class EliminarServicioCompleto @Inject constructor(
    private val servicioRepository: ServicioRepository,
    private val eliminarEstadoServicio: EliminarEstadoServicio,
) {
    suspend operator fun invoke(servicioCompleto: ServicioCompleto) {
        servicioRepository.eliminarServicio(servicioCompleto.servicio.id)

        for (estado in servicioCompleto.estado) {
            if (estado != null) {
                eliminarEstadoServicio.invoke(estado.id)
            }
        }

        // ELIMINAS LAS FIRMAS Y LOS MULTIMEDIAS.
    }
}