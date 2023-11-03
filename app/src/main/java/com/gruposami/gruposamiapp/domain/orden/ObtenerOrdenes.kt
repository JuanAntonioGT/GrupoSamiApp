package com.gruposami.gruposamiapp.domain.orden

import com.gruposami.gruposamiapp.data.network.orden.model.OrdenResponse
import com.gruposami.gruposamiapp.data.repositories.ClienteRepository
import com.gruposami.gruposamiapp.data.repositories.OrdenRepository
import com.gruposami.gruposamiapp.domain.cliente.model.toDomain
import com.gruposami.gruposamiapp.domain.orden.model.toDomain
import com.gruposami.gruposamiapp.ui.login.model.Comprobacion
import javax.inject.Inject

class ObtenerOrdenes @Inject constructor(
    private val ordenRepository: OrdenRepository,
    private val insertarOrden: InsertarOrden,
) {
    suspend operator fun invoke(): Comprobacion {
        val ordenManagement = ordenRepository.obtenerOrdenesApi()
        val comprobacion = Comprobacion(ordenManagement.comprobacion, ordenManagement.mensaje)

        if (ordenManagement.comprobacion && ordenManagement.response != null) {
            val listadoOrdenes: List<OrdenResponse>? = ordenManagement.response.body()
            if (listadoOrdenes != null) {
                for (orden in listadoOrdenes) {
                    insertarOrden.invoke(orden)
                }
            }
        }
        return comprobacion
    }
}
