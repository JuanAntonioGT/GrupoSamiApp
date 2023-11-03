package com.gruposami.gruposamiapp.domain.cliente

import com.gruposami.gruposamiapp.data.network.orden.model.ClienteResponse
import com.gruposami.gruposamiapp.data.repositories.ClienteRepository
import com.gruposami.gruposamiapp.domain.cliente.model.toDomain
import com.gruposami.gruposamiapp.domain.contacto.InsertarContacto
import com.gruposami.gruposamiapp.domain.contacto.model.toDomain
import com.gruposami.gruposamiapp.domain.direccion.InsertarDireccion
import com.gruposami.gruposamiapp.domain.direccion.model.toDomain
import javax.inject.Inject

class InsertarCliente @Inject constructor(
    private val clienteRepository: ClienteRepository,
    private val insertarDireccion: InsertarDireccion,
    private val insertarContacto: InsertarContacto,

) {
    suspend operator fun invoke(clienteResponse: ClienteResponse) {
        clienteRepository.insertarCliente(clienteResponse.toDomain())

        if (clienteResponse.direccionSet.isNotEmpty()){
            for (direccion in clienteResponse.direccionSet){
                insertarDireccion.invoke(direccion!!.toDomain(clienteResponse.id))
            }
        }

        if (clienteResponse.contactoSet.isNotEmpty()){
            for (contacto in clienteResponse.contactoSet){
                insertarContacto.invoke(contacto!!.toDomain(clienteResponse.id))
            }
        }
    }
}