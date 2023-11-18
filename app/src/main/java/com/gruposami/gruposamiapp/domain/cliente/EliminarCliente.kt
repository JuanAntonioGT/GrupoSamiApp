package com.gruposami.gruposamiapp.domain.cliente

import com.gruposami.gruposamiapp.data.repositories.ClienteRepository
import javax.inject.Inject

class EliminarCliente @Inject constructor(
    private val clienteRepository: ClienteRepository,

    ) {
    suspend operator fun invoke(clienteId: Int) {
        clienteRepository.eliminarCliente(clienteId)
    }
}