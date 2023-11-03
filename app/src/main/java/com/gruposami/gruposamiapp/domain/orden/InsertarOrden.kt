package com.gruposami.gruposamiapp.domain.orden

import com.gruposami.gruposamiapp.data.network.orden.model.OrdenResponse
import com.gruposami.gruposamiapp.data.repositories.OrdenRepository
import com.gruposami.gruposamiapp.domain.cliente.InsertarCliente
import com.gruposami.gruposamiapp.domain.orden.model.toDomain
import javax.inject.Inject

class InsertarOrden @Inject constructor(
    private val ordenRepository: OrdenRepository,
    private val insertarCliente: InsertarCliente,
) {
    suspend operator fun invoke(ordenResponse: OrdenResponse) {
        ordenRepository.insertarOrden(ordenResponse.toDomain())

        if (ordenResponse.clienteResponse != null) {
            insertarCliente.invoke(ordenResponse.clienteResponse!!)
        }

    }
}