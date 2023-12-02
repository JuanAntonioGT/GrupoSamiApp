package com.gruposami.gruposamiapp.domain.servicio

import com.gruposami.gruposamiapp.data.repositories.ServicioRepository
import com.gruposami.gruposamiapp.domain.estado.EliminarEstadoServicio
import com.gruposami.gruposamiapp.domain.firma.EliminarFirma
import com.gruposami.gruposamiapp.domain.multimedia.EliminarMultimedia
import com.gruposami.gruposamiapp.domain.servicio.model.ServicioCompleto
import javax.inject.Inject

class EliminarServicioCompleto @Inject constructor(
    private val servicioRepository: ServicioRepository,
    private val eliminarEstadoServicio: EliminarEstadoServicio,
    private val eliminarFirmaUseCase: EliminarFirma,
    private val eliminarMultimediaUseCase: EliminarMultimedia,
) {
    suspend operator fun invoke(servicioCompleto: ServicioCompleto) {
        servicioRepository.eliminarServicio(servicioCompleto.servicio.id)

        for (estado in servicioCompleto.estado) {
            if (estado != null) {
                println("Eliminar el serivcio del estado ${estado.id}")
                eliminarEstadoServicio.invoke(estado.id)

            }
        }

        for (firma in servicioCompleto.firma) {
            if (firma != null) {
                eliminarFirmaUseCase.invoke(firma)
            }
        }

        for (multimedia in servicioCompleto.multimedia) {
            if (multimedia != null) {
                eliminarMultimediaUseCase.invoke(multimedia)
            }
        }

    }
}