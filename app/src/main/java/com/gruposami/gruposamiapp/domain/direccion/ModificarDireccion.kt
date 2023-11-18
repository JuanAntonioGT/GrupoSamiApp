package com.gruposami.gruposamiapp.domain.direccion

import com.gruposami.gruposamiapp.data.repositories.DireccionRepository
import com.gruposami.gruposamiapp.domain.direccion.model.Direccion
import com.gruposami.gruposamiapp.utils.timestamp
import javax.inject.Inject

class ModificarDireccion @Inject constructor(
    private val direccionRepository: DireccionRepository
) {
    suspend operator fun invoke(direccion: Direccion) {
        direccion.fechaModificacion = timestamp()
        direccionRepository.modificarDireccion(direccion)
    }

}