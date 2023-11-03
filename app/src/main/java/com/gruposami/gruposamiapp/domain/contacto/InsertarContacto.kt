package com.gruposami.gruposamiapp.domain.contacto

import com.gruposami.gruposamiapp.data.repositories.ContactoRepository
import com.gruposami.gruposamiapp.domain.contacto.model.Contacto
import javax.inject.Inject


class InsertarContacto @Inject constructor(
    private val contactoRepository: ContactoRepository
) {
    suspend operator fun invoke(contacto: Contacto) {
        contactoRepository.insertarContacto(contacto)
    }
}
