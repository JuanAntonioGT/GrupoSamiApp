package com.gruposami.gruposamiapp.domain.orden

import com.gruposami.gruposamiapp.data.network.orden.OrdenManagement
import com.gruposami.gruposamiapp.data.repositories.OrdenRepository
import javax.inject.Inject

class ObtenerOrdenesRemote @Inject constructor(
    private val ordenRepository: OrdenRepository,
) {

    suspend operator fun invoke(): OrdenManagement {
        val ordenManagement = ordenRepository.obtenerOrdenesApi()

        // En su día yo llamaba desde la API y aquí mismo lo metía en la bbdd, pero para sincronizar me hace falta
        // pasar por la clase de sincronización.
        // val comprobacion = Comprobacion(ordenManagement.comprobacion, ordenManagement.mensaje)
        //
        // if (ordenManagement.comprobacion && ordenManagement.response != null) {
        //     val listadoOrdenes: List<OrdenResponse>? = ordenManagement.response.body()
        //     if (listadoOrdenes != null) {
        //         for (orden in listadoOrdenes) {
        //             insertarOrden.invoke(orden)
        //         }
        //     }
        // }

        return ordenManagement
    }
}
