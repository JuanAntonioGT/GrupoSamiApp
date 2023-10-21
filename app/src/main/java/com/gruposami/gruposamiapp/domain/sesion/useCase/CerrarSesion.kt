package com.gruposami.gruposamiapp.domain.sesion.useCase

import com.gruposami.gruposamiapp.data.repositories.SesionRepository
import javax.inject.Inject

class CerrarSesion @Inject constructor(
    private val sesionRepository: SesionRepository
) {
    suspend operator fun invoke() {
        sesionRepository.cerrarSesion()
    }
}