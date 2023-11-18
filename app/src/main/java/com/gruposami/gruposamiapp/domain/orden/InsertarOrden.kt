package com.gruposami.gruposamiapp.domain.orden

import com.gruposami.gruposamiapp.data.network.orden.model.OrdenResponse
import com.gruposami.gruposamiapp.data.repositories.OrdenRepository
import com.gruposami.gruposamiapp.domain.cliente.InsertarCliente
import com.gruposami.gruposamiapp.domain.estado.InsertarEstado
import com.gruposami.gruposamiapp.domain.estado.model.toDomain
import com.gruposami.gruposamiapp.domain.orden.model.OrdenEstado
import com.gruposami.gruposamiapp.domain.orden.model.toDomain
import com.gruposami.gruposamiapp.domain.servicio.InsertarServicio
import javax.inject.Inject

class InsertarOrden @Inject constructor(
    private val ordenRepository: OrdenRepository,
    private val insertarCliente: InsertarCliente,
    private val insertarEstado: InsertarEstado,
    private val insertarServicio: InsertarServicio,
) {
    suspend operator fun invoke(ordenResponse: OrdenResponse) {

        ordenRepository.insertarOrden(ordenResponse.toDomain())

        if (ordenResponse.estado != null) {
            insertarEstado.invoke(ordenResponse.estado!!.toDomain())
            ordenRepository.insertarOrdenEstado(OrdenEstado(ordenResponse.id, ordenResponse.estado!!.id))
        }

        if (ordenResponse.clienteResponse != null) {
            insertarCliente.invoke(ordenResponse.clienteResponse!!)
        }

        if (ordenResponse.servicio.isNotEmpty()) {
            ordenResponse.servicio.map {  insertarServicio.invoke(it) }
        }

    }
}