package com.gruposami.gruposamiapp.domain.servicio



import com.gruposami.gruposamiapp.data.network.orden.model.ServicioResponse
import com.gruposami.gruposamiapp.data.repositories.ServicioRepository
import com.gruposami.gruposamiapp.domain.estado.InsertarEstado
import com.gruposami.gruposamiapp.domain.estado.model.toDomain
import com.gruposami.gruposamiapp.domain.firma.InsertarFirma
import com.gruposami.gruposamiapp.domain.firma.model.toDomain
import com.gruposami.gruposamiapp.domain.multimedia.InsertarMultimedia
import com.gruposami.gruposamiapp.domain.multimedia.model.toDomain
import com.gruposami.gruposamiapp.domain.servicio.model.ServicioMedido
import com.gruposami.gruposamiapp.domain.servicio.model.ServicioMontado
import com.gruposami.gruposamiapp.domain.servicio.model.ServicioPendiente
import com.gruposami.gruposamiapp.domain.servicio.model.toDomain
import javax.inject.Inject

class InsertarListaServicio @Inject constructor(
    private val servicioRepository: ServicioRepository,
    private val insertarFirma: InsertarFirma,
    private val insertarMultimediaUseCase: InsertarMultimedia,
    private val insertarEstado: InsertarEstado,
) {
    suspend operator fun invoke(listaServicios: List<ServicioResponse?>){
        for (servicio in listaServicios){
            if (servicio != null) {

                if (servicio.estado != null){
                    println(servicio.estado)
                    insertarEstado.invoke(servicio.estado!!.toDomain())

                }

                // Insertar los datos básicos del servicio
                servicioRepository.insertarServicioBBDD(servicio.toDomain())



                // Insertar el listado de estados que tenga (por ahora siempre 0)
//                if (servicio.estado.isNotEmpty()){
//                    for (estado in servicio.estado){
                // CAMBIO EL DIA 4/7/23 Para que solo apunte un estado la app
//                servicioRepository.insertarServicioEstado(ServicioEstado(servicio.id, servicio.estado?.id))
////                    }
////                }

                // Insertamos los usuarios que tienen que ver con el usuario
                if (servicio.medidoPor.isNotEmpty()){
                    for (medidoPor in servicio.medidoPor){
                        servicioRepository.insertarServicioMedido(ServicioMedido(servicio.id, medidoPor?.id!!))
                    }
                }

                if (servicio.montadoPor.isNotEmpty()){
                    for (montadoPor in servicio.montadoPor){
                        servicioRepository.insertarServicioMontado(ServicioMontado(servicio.id, montadoPor?.id!!))
                    }
                }

                if (servicio.pendientePor.isNotEmpty()){
                    for (pendientePor in servicio.pendientePor){
                        servicioRepository.insertarServicioPendiente(ServicioPendiente(servicio.id, pendientePor?.id!!))
                    }
                }

                // Insertamos las firmas que tenga
                if (servicio.firma.isNotEmpty()){
                    for (firma in servicio.firma){
                        if (firma != null){
                            insertarFirma(firma.toDomain(servicio.id))
                        }
                    }
                }

                // Insertar las imagenes que enlazar
                if (servicio.multimedia.isNotEmpty()){
                    for (multimedia in servicio.multimedia){
                        if (multimedia != null){
                            //
//                            insertarMultimediaUseCase(multimedia.toDomain(servicio.id))
                        }
                    }
                }

            }
        }
    }
}