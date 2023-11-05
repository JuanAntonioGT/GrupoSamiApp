package com.gruposami.gruposamiapp.data.repositories

import com.gruposami.gruposamiapp.data.database.dao.ContactoDao
import com.gruposami.gruposamiapp.data.database.entities.toDatabase
import com.gruposami.gruposamiapp.domain.contacto.model.Contacto
import com.gruposami.gruposamiapp.domain.orden.model.CambioId
import javax.inject.Inject


class ContactoRepository @Inject constructor(
    private val contactoDao: ContactoDao
) {

    suspend fun insertarContacto(contacto: Contacto) {
        contactoDao.insertarContacto(contacto.toDatabase())
    }

    suspend fun modificarContacto(contacto: Contacto) {
        contactoDao.insertarContacto(contacto.toDatabase())
    }

    suspend fun eliminarContacto(id: Int) {
        contactoDao.eliminarContacto(id)
    }

    suspend fun modificarContactoId(contacto: CambioId) {
        contactoDao.modificarContactoId(contacto.anteriorId, contacto.nuevaId)
    }

//    suspend fun listaContactosLocales(clienteId: Int): List<Contacto> {
//        val response = contactoDao.obtenerListaContactos(clienteId)
//        return if (response.isEmpty()) {
//            emptyList()
//        } else {
//            response.map { it.toDomain() }
//        }
//    }

}