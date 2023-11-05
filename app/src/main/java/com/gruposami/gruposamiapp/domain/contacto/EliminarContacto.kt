package com.gruposami.gruposamiapp.domain.contacto

import com.gruposami.gruposamiapp.data.repositories.ContactoRepository
import javax.inject.Inject

class EliminarContacto @Inject constructor(
    private val contactoRepository: ContactoRepository
) {
    suspend operator fun invoke(idContacto: Int) {
        contactoRepository.eliminarContacto(idContacto)
    }
}