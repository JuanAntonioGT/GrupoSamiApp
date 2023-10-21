package com.gruposami.gruposamiapp.domain.sesion.useCase

import com.gruposami.gruposamiapp.data.repositories.SesionRepository
import com.gruposami.gruposamiapp.domain.sesion.model.Sesion
import javax.inject.Inject

class CrearSesion@Inject constructor(
    private val sesionRepository: SesionRepository
) {
    suspend operator fun invoke(sesion: Sesion) {
        sesionRepository.crearSesion(sesion)
    }
}