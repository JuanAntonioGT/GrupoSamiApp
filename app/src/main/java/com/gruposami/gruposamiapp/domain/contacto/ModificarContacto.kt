package com.gruposami.gruposamiapp.domain.contacto


import com.gruposami.gruposamiapp.data.repositories.ContactoRepository
import com.gruposami.gruposamiapp.domain.contacto.model.Contacto
import com.gruposami.gruposamiapp.utils.timestamp
import javax.inject.Inject

class ModificarContacto @Inject constructor(
    private val contactoRepository: ContactoRepository
) {
    suspend operator fun invoke(contacto: Contacto) {
        contacto.fechaModificacion = timestamp()
        contactoRepository.modificarContacto(contacto)
    }
}