package com.gruposami.gruposamiapp.domain.orden

import com.gruposami.gruposamiapp.data.network.orden.OrdenManagementEnviar
import com.gruposami.gruposamiapp.data.repositories.OrdenRepository
import com.gruposami.gruposamiapp.domain.contacto.ModificarContactoId
import com.gruposami.gruposamiapp.domain.direccion.ModificarDireccionId
import com.gruposami.gruposamiapp.domain.estado.ModificarEstadoId
import com.gruposami.gruposamiapp.domain.firma.ModificarFirmaId
import com.gruposami.gruposamiapp.domain.multimedia.ModificarMultimediaId
import com.gruposami.gruposamiapp.domain.orden.model.OrdenCompleta
import com.gruposami.gruposamiapp.domain.servicio.ModificarServicioId
import javax.inject.Inject


class EnviarOrden @Inject constructor(
    private val ordenRepository: OrdenRepository,
    private val modificarDireccionIdUseCase: ModificarDireccionId,
    private val modificarContactoIdUseCase: ModificarContactoId,
    private val modificarEstadoIdUseCase: ModificarEstadoId,
    private val modificarServicioIdUseCase: ModificarServicioId,
    private val modificarFirmaIdUseCase: ModificarFirmaId,
    private val modificarMultimediaIdUseCase: ModificarMultimediaId,
) {
    suspend operator fun invoke(ordenCompleta: OrdenCompleta) {
        val ordenManagementEnviar: OrdenManagementEnviar = ordenRepository.enviarOrdenApi(ordenCompleta)
        // 1º Enviar la orden que teníamos almacenada

        // 2º Podría devolvernos una respuestas para actualizar las PK
        if (ordenManagementEnviar.comprobacion){
            if (ordenManagementEnviar.response != null){
                val response = ordenManagementEnviar.response.body()
                if (response != null) {
                    if (response.direccion.isNotEmpty()){
                        modificarDireccionIdUseCase.invoke(response.direccion)
                    }
                    if (response.contacto.isNotEmpty()){
                        modificarContactoIdUseCase.invoke(response.contacto)
                    }
                    if (response.estado.isNotEmpty()){
                        modificarEstadoIdUseCase.invoke(response.estado)
                    }
                    if (response.servicio.isNotEmpty()){
                        modificarServicioIdUseCase.invoke(response.servicio)
                    }
                    if (response.firma.isNotEmpty()){
                        modificarFirmaIdUseCase.invoke(response.firma)
                    }
                    if (response.multimedia.isNotEmpty()){
                        modificarMultimediaIdUseCase.invoke(response.multimedia)
                    }
                    if (response.empleadoMedido.isNotEmpty()){

                    }
                    if (response.empleadoMontado.isNotEmpty()){

                    }
                    if (response.empleadoPendiente.isNotEmpty()){

                    }
                }
            }
        }

        //////////////////////////////////////////////////////////////
                //         val mensaje: String?,
                //    val direccion: List<CambioId?>,
                //    val contacto: List<CambioId?>,
                //    val estado: List<CambioId?>,
                //    val servicio: List<CambioId?>,
                //    val firma: List<CambioId?>,
                //    val multimedia: List<CambioId?>,
                //    val empleadoMedido: List<CambioId?>,
                //    val empleadoMontado: List<CambioId?>,
                //    val empleadoPendiente: List<CambioId?>,
        //////////////////////////////////////////////////////////////

//            if (response.body()?.estado?.isNotEmpty() == true){
//                modificarEstadoIdUseCase(response.body()!!.estado)
//            }
//            if (response.body()?.contacto?.isNotEmpty() == true){
//                modificarContactoIdUseCase(response.body()!!.contacto)
//            }
//            if (response.body()?.servicio?.isNotEmpty() == true){
//                modificarServicioIdUseCase(response.body()!!.servicio)
//            }
//            if (response.body()?.firma?.isNotEmpty() == true){
//                modificarServicioIdUseCase(response.body()!!.servicio)
//            }
//            if (response.body()?.multimedia?.isNotEmpty() == true){
//                println("Multimedia tiene fotos que cambiar")
//                // Este, a diferencia del resto, si viene, será para enviar en segundo plano las imagenes.
//                modificarMultimediaUseCase(response.body()!!.multimedia)
//            }
//            if (response.body()?.empleadoMedido?.isNotEmpty() == true){
//                // No implementado aún
////                modificarServicioIdUseCase(response.body()!!.servicio)
//            }
//            if (response.body()?.empleadoMontado?.isNotEmpty() == true){
//                // No implementado aún
////                modificarServicioIdUseCase(response.body()!!.servicio)
//            }
//            if (response.body()?.empleadoPendiente?.isNotEmpty() == true) {
////                modificarServicioIdUseCase(response.body()!!.servicio)
//            }

    }
}