package com.gruposami.gruposamiapp.domain.servicio

import com.gruposami.gruposamiapp.data.network.orden.model.ServicioResponse
import com.gruposami.gruposamiapp.data.repositories.ServicioRepository
import com.gruposami.gruposamiapp.domain.estado.InsertarEstado
import com.gruposami.gruposamiapp.domain.estado.model.toDomain
import com.gruposami.gruposamiapp.domain.firma.InsertarFirma
import com.gruposami.gruposamiapp.domain.firma.model.toDomain
import com.gruposami.gruposamiapp.domain.multimedia.InsertarMultimedia
import com.gruposami.gruposamiapp.domain.multimedia.model.toDomain
import com.gruposami.gruposamiapp.domain.servicio.model.ServicioCompleto
import com.gruposami.gruposamiapp.domain.servicio.model.ServicioEstado
import com.gruposami.gruposamiapp.domain.servicio.model.ServicioMedido
import com.gruposami.gruposamiapp.domain.servicio.model.ServicioMontado
import com.gruposami.gruposamiapp.domain.servicio.model.ServicioPendiente
import com.gruposami.gruposamiapp.domain.servicio.model.toDomain
import javax.inject.Inject

class InsertarServicio @Inject constructor(
    private val servicioRepository: ServicioRepository,
    private val insertarEstado: InsertarEstado,
    private val insertarFirma: InsertarFirma,
    private val insertarMultimedia: InsertarMultimedia,
) {
    suspend operator fun invoke(servicioResponse: ServicioResponse?) {

        if (servicioResponse != null) {
            servicioRepository.insertarServicioBBDD(servicioResponse.toDomain())

            if (servicioResponse.estado != null){
                insertarEstado.invoke(servicioResponse.estado!!.toDomain())
                servicioRepository.insertarServicioEstado(ServicioEstado(servicioResponse.id, servicioResponse.estado!!.id))
            }

            // Insertamos las firmas que tenga
            if (servicioResponse.firma.isNotEmpty()){
                for (firma in servicioResponse.firma){
                    if (firma != null){
                        insertarFirma.invoke(firma.toDomain(servicioResponse.id))
                    }
                }
            }

            // Insertar las imagenes que enlazar
            if (servicioResponse.multimedia.isNotEmpty()){
                for (multimedia in servicioResponse.multimedia){
                    if (multimedia != null){
                        insertarMultimedia(multimedia.toDomain(servicioResponse.id))
                    }
                }
            }


            // Insertamos los usuarios que tienen que ver con el usuario
            if (servicioResponse.medidoPor.isNotEmpty()){
                for (medidoPor in servicioResponse.medidoPor){
                    if (medidoPor != null){
                        servicioRepository.insertarServicioMedido(ServicioMedido(servicioResponse.id, medidoPor.id!!))
                    }

                }
            }

            if (servicioResponse.montadoPor.isNotEmpty()){
                for (montadoPor in servicioResponse.montadoPor){
                    if (montadoPor != null) {
                        servicioRepository.insertarServicioMontado(ServicioMontado(servicioResponse.id, montadoPor.id!!))
                    }
                }
            }

            if (servicioResponse.pendientePor.isNotEmpty()){
                for (pendientePor in servicioResponse.pendientePor){
                    if (pendientePor != null) {
                        servicioRepository.insertarServicioPendiente(ServicioPendiente(servicioResponse.id, pendientePor.id!!))
                    }
                }
            }
        }
    }
}