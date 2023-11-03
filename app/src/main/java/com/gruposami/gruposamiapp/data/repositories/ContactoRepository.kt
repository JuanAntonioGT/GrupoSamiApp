package com.gruposami.gruposamiapp.data.repositories

import com.gruposami.gruposamiapp.data.database.dao.ContactoDao
import com.gruposami.gruposamiapp.data.database.entities.toDatabase
import com.gruposami.gruposamiapp.domain.contacto.model.Contacto
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

    suspend fun eliminarContacto(contacto: Contacto) {
        contactoDao.eliminarContacto(contacto.id)
    }

//    suspend fun modificarContactoId(contacto: CambioId?) {
//        contactoDao.modificarContactoId(contacto?.anterior_id, contacto?.nueva_id)
//    }

//    suspend fun listaContactosLocales(clienteId: Int): List<Contacto> {
//        val response = contactoDao.obtenerListaContactos(clienteId)
//        return if (response.isEmpty()) {
//            emptyList()
//        } else {
//            response.map { it.toDomain() }
//        }
//    }

}