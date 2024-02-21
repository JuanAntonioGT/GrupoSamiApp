package com.gruposami.gruposamiapp.domain.sincronizacion

import com.gruposami.gruposamiapp.data.network.orden.model.OrdenResponse
import com.gruposami.gruposamiapp.domain.cliente.EliminarCliente
import com.gruposami.gruposamiapp.domain.contacto.EliminarContacto
import com.gruposami.gruposamiapp.domain.direccion.EliminarDireccion
import com.gruposami.gruposamiapp.domain.estado.EliminarEstadoOrden
import com.gruposami.gruposamiapp.domain.orden.EliminarOrden
import com.gruposami.gruposamiapp.domain.orden.EnviarOrden
import com.gruposami.gruposamiapp.domain.orden.InsertarOrden
import com.gruposami.gruposamiapp.domain.orden.ObtenerOrdenesLocal
import com.gruposami.gruposamiapp.domain.orden.ObtenerOrdenesRemote
import com.gruposami.gruposamiapp.domain.orden.model.OrdenCompleta
import com.gruposami.gruposamiapp.domain.orden.model.toDomain
import com.gruposami.gruposamiapp.domain.servicio.EliminarServicioCompleto
import javax.inject.Inject
import kotlin.NullPointerException

class SincronizacionDatos @Inject constructor(
    private val obtenerOrdenesLocal: ObtenerOrdenesLocal,
    private val enviarOrdenUseCase: EnviarOrden,
    private val obtenerOrdenesRemote: ObtenerOrdenesRemote,
    private val insertarOrdenUseCase: InsertarOrden,
    private val eliminarOrdenUseCase: EliminarOrden,
    private val eliminarCliente: EliminarCliente,
    private val eliminarDireccion: EliminarDireccion,
    private val eliminarContacto: EliminarContacto,
    private val eliminarEstadoOrden: EliminarEstadoOrden,
    private val eliminarServicioCompleto: EliminarServicioCompleto,
) {

    suspend operator fun invoke(): String {
        /**
         * Nueva instrucción:
         * El móvil iniciará la petición de información pero antes comprobará si tiene cobertura
         *
         * 1º Enviar toda la información de la BBDD local a la API.
         *      Si está vacío o si esta lleno, se envia lo que haya para que el servidor se encarge de poner al día.
         *
         * 2º Pedir toda la información a la API con lo que ya había
         *
         * 3º Iterar la lista local sobre la lista remota para comprobar todos los elementos.
         *      Tanto para añadir como para eliminar.
         *
         **/

        // NO PUEDO OLVIDAR QUE, SI HAY UN FALLO, TENGO QUE NOTIFICAR AL USUARIO... Así que tengo que ir planteando
        // cómo voy a notificar al user si aquí hay fallos.

        // 1º Paso
        val localDataSource = obtenerOrdenesLocal.invoke()
        localDataSource.map { enviarOrdenUseCase.invoke(it) }

        // 2º Paso
        val ordenRemoteManagement = obtenerOrdenesRemote.invoke()

        var mensaje = "Correcto"
        // 3º Paso
        println(localDataSource)
        println(ordenRemoteManagement.response?.body())
        try {
//            if (localDataSource.isNotEmpty() && !ordenRemoteManagement.response?.body().isNullOrEmpty()){
                compararListas(localDataSource, ordenRemoteManagement.response?.body()!!)
//            } else {
//                mensaje = "Error al sincronizar los datos."
//            }
        } catch (e: NullPointerException){
            mensaje = "Error sincronizando los datos. Codigo error 02. $e"
        } catch (e: Exception){
            mensaje = "Error sincronizando los datos. Codigo error 01. $e"
        }
        println(mensaje)
        return mensaje
    }

    private suspend fun compararListas(localDataSource: List<OrdenCompleta>, remoteDataSource: List<OrdenResponse>) {
        // Iterar por cada entrada de los datos en LOCAL para comprobar si se encuentran en la lista remota.
        // Si se encuentra, se comprueba, si no se encuentra, se elimina de la tabla ORDEN solamente.
        val listadoOrdenesRemotasId: List<Int> = remoteDataSource.map { it.toDomain().id!! }

        for (local in localDataSource) {

            // Buscar los ID, porque el objeto no se puede igualar
            if (local.orden.id !in listadoOrdenesRemotasId) {
                println("NO Contiene la orden local: ${local.orden}")
                eliminarOrdenUseCase.invoke(local.orden)
                eliminarCliente.invoke(local.cliente.id)
                local.direccion.map { eliminarDireccion.invoke(it.id) }
                local.contacto.map { eliminarContacto.invoke(it.id) }
                local.estado.map { eliminarEstadoOrden.invoke(it.id) }
                local.servicioCompleto.map { eliminarServicioCompleto.invoke(it) }

                // Y que se borre el resto de elementos de al app
            } else {
//                println("Contiene la orden local: ${local.orden}")
                // Seguir comprobando elementos
                val clientesRemotos = remoteDataSource.map { it.clienteResponse }
                val estadosRemotos = remoteDataSource.map { it.estado }
                val serviciosResponseRemotos = remoteDataSource.map { it.servicio }

                for (direccionLocal in local.direccion) {
                    var flagDireccion = true
                    for (clienteRemoto in clientesRemotos) {
                        for (direccionRemota in clienteRemoto!!.direccionSet) {
                            if (direccionLocal.id == direccionRemota!!.id) {
                                // println("La dirección aparece en el listado remoto")
                                flagDireccion = false
                            }
                        }
                    }

                    if (flagDireccion) {
//                        println("La direccion NO aparece en el listado remoto. Eliminar ->")
                        eliminarDireccion.invoke(direccionLocal.id)
                    }
                }

                for (contactoLocal in local.contacto) {
                    var flagContacto = true
                    for (clienteRemoto in clientesRemotos) {
                        for (contactoRemota in clienteRemoto!!.contactoSet) {
                            if (contactoLocal.id == contactoRemota!!.id) {
//                                println("El contacto aparece en el listado remoto")
                                flagContacto = false
                            }
                        }
                    }

                    if (flagContacto) {
//                        println("El contacto NO aparece en el listado remoto. Eliminar ->")
                        eliminarContacto.invoke(contactoLocal.id)
                    }
                }

                for (estadoLocal in local.estado) {
                    var flagEstado = true
                    for (estadoRemoto in estadosRemotos) {
                        if (estadoLocal.id == estadoRemoto!!.id) {
//                            println("El estado aparece en el listado remoto")
                            flagEstado = false
                        }

                    }

                    if (flagEstado) {
//                        println("El estado NO aparece en el listado remoto. Eliminar ->")
                        eliminarEstadoOrden.invoke(estadoLocal.id)
                    }
                }

                for (servicioLocal in local.servicioCompleto) {
                    var flagServicio = true
                    for (servicioResponseRemoto in serviciosResponseRemotos) {
                        for (servicioRemoto in servicioResponseRemoto)
                            if (servicioLocal.servicio.id == servicioRemoto!!.id) {
//                                println("El servicio aparece en el listado remoto")
                                flagServicio = false
                            }
                    }

                    if (flagServicio) {
//                        println("El servicio NO aparece en el listado remoto. Eliminar ->")
                        eliminarServicioCompleto.invoke(servicioLocal)
                    }
                }


            }
        }

        for (remote in remoteDataSource) {
            insertarOrdenUseCase.invoke(remote)
        }

    }

}
