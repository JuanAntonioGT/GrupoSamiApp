package com.gruposami.gruposamiapp.domain.servicio

import com.gruposami.gruposamiapp.data.repositories.ServicioRepository
import com.gruposami.gruposamiapp.domain.estado.InsertarEstado
import com.gruposami.gruposamiapp.domain.firma.InsertarFirma
import com.gruposami.gruposamiapp.domain.multimedia.InsertarMultimedia
import com.gruposami.gruposamiapp.domain.servicio.model.ServicioCompleto
import com.gruposami.gruposamiapp.domain.servicio.model.ServicioEstado
import com.gruposami.gruposamiapp.domain.servicio.model.ServicioMedido
import com.gruposami.gruposamiapp.domain.servicio.model.ServicioMontado
import com.gruposami.gruposamiapp.domain.servicio.model.ServicioPendiente
import javax.inject.Inject

class InsertarServicioCompleto @Inject constructor(
    private val servicioRepository: ServicioRepository,
    private val insertarEstado: InsertarEstado,
    private val insertarFirma: InsertarFirma,
    private val insertarMultimedia: InsertarMultimedia,
) {
    suspend operator fun invoke(servicioCompleto: ServicioCompleto) {

        servicioRepository.insertarServicioBBDD(servicioCompleto.servicio)

        for (estado in servicioCompleto.estado) {
            if (estado != null) {
                insertarEstado.invoke(estado)
                servicioRepository.insertarServicioEstado(
                    ServicioEstado(servicioCompleto.servicio.id, estado.id)
                )
            }
        }

        // Insertamos las firmas que tenga
        if (servicioCompleto.firma.isNotEmpty()) {
            for (firma in servicioCompleto.firma) {
                if (firma != null) {
                    insertarFirma.invoke(firma)
                }
            }
        }

        // Insertar las imagenes que enlazar
        if (servicioCompleto.multimedia.isNotEmpty()) {
            for (multimedia in servicioCompleto.multimedia) {
                if (multimedia != null) {
                    insertarMultimedia(multimedia)
                }
            }
        }

        // Insertamos los usuarios que tienen que ver con el usuario
        if (servicioCompleto.empleadoMedido.isNotEmpty()) {
            for (medidoPor in servicioCompleto.empleadoMedido) {
                if (medidoPor != null) {
                    servicioRepository.insertarServicioMedido(
                        ServicioMedido(servicioCompleto.servicio.id, medidoPor.id!!)
                    )
                }
            }
        }

        if (servicioCompleto.empleadoMontado.isNotEmpty()) {
            for (montadoPor in servicioCompleto.empleadoMontado) {
                if (montadoPor != null) {
                    servicioRepository.insertarServicioMontado(
                        ServicioMontado(servicioCompleto.servicio.id, montadoPor.id!!)
                    )
                }
            }
        }

        if (servicioCompleto.empleadoPendiente.isNotEmpty()) {
            for (pendientePor in servicioCompleto.empleadoPendiente) {
                if (pendientePor != null) {
                    servicioRepository.insertarServicioPendiente(
                        ServicioPendiente(servicioCompleto.servicio.id, pendientePor.id!!)
                    )
                }
            }
        }

    }
}