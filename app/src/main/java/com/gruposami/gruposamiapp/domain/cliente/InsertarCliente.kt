package com.gruposami.gruposamiapp.domain.cliente

import com.gruposami.gruposamiapp.data.network.orden.ClienteResponse
import com.gruposami.gruposamiapp.data.repositories.ClienteRepository
import com.gruposami.gruposamiapp.domain.cliente.model.toDomain
import javax.inject.Inject

class InsertarCliente @Inject constructor(
    private val clienteRepository: ClienteRepository,

) {
    suspend operator fun invoke(clienteResponse: ClienteResponse) {
        clienteRepository.insertarCliente(clienteResponse.toDomain())
    }
}