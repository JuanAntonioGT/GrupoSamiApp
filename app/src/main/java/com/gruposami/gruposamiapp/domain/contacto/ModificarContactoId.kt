package com.gruposami.gruposamiapp.domain.contacto


import com.gruposami.gruposamiapp.data.repositories.ContactoRepository
import com.gruposami.gruposamiapp.domain.orden.model.CambioId
import javax.inject.Inject

class ModificarContactoId @Inject constructor(
    private val contactoRepository: ContactoRepository
) {
    suspend operator fun invoke(contactos: List<CambioId>) {
        contactos.map { contactoRepository.modificarContactoId(it) }
    }
}