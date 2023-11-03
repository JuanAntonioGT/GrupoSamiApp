package com.gruposami.gruposamiapp.domain.direccion

import com.gruposami.gruposamiapp.data.network.orden.model.DireccionResponse
import com.gruposami.gruposamiapp.data.repositories.DireccionRepository
import com.gruposami.gruposamiapp.domain.direccion.model.Direccion
import com.gruposami.gruposamiapp.domain.direccion.model.toDomain
import javax.inject.Inject


class InsertarDireccion @Inject constructor(
    private val direccionRepository: DireccionRepository
) {
    suspend operator fun invoke(direccion: Direccion) {
        direccionRepository.insertarDireccion(direccion)
    }

}