package com.gruposami.gruposamiapp.data.repositories

import com.gruposami.gruposamiapp.data.database.dao.ClienteDao
import com.gruposami.gruposamiapp.data.database.entities.toDatabase
import com.gruposami.gruposamiapp.domain.cliente.model.Cliente
import javax.inject.Inject

class ClienteRepository @Inject constructor(
    private val clienteDao: ClienteDao
) {

    suspend fun insertarCliente(cliente: Cliente) {
        clienteDao.insertarCliente(cliente.toDatabase())
    }

    suspend fun eliminarCliente(idCliente: Int) {
        clienteDao.eliminarCliente(idCliente)
    }

}
