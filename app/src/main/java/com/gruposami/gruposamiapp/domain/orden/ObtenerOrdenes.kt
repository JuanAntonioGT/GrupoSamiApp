package com.gruposami.gruposamiapp.domain.orden

import com.gruposami.gruposamiapp.data.network.orden.OrdenResponse
import com.gruposami.gruposamiapp.data.repositories.ClienteRepository
import com.gruposami.gruposamiapp.data.repositories.OrdenRepository
import com.gruposami.gruposamiapp.domain.cliente.model.toDomain
import com.gruposami.gruposamiapp.domain.orden.model.toDomain
import com.gruposami.gruposamiapp.ui.login.model.Comprobacion
import javax.inject.Inject

class ObtenerOrdenes @Inject constructor(
    private val ordenRepository: OrdenRepository,
    private val clienteRepository: ClienteRepository,
) {
    suspend operator fun invoke(): Comprobacion {
        val serviceManagement = ordenRepository.obtenerOrdenesApi()
        val comprobacion = Comprobacion(serviceManagement.comprobacion, serviceManagement.mensaje)

        if (serviceManagement.comprobacion && serviceManagement.response != null) {
            val listadoOrdenes: List<OrdenResponse> = serviceManagement.response.body()!!
            for (orden in listadoOrdenes) {
                ordenRepository.insertarOrden(orden.toDomain())
                if (orden.clienteResponse != null) {
                    clienteRepository.insertarCliente(orden.clienteResponse!!.toDomain())
                }
            }
        }
        return comprobacion
    }
}
