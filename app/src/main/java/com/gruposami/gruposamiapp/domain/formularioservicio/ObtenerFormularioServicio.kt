package com.gruposami.gruposamiapp.domain.formularioservicio


import com.gruposami.gruposamiapp.data.repositories.FormularioServicioRepository
import javax.inject.Inject

class ObtenerFormularioServicio @Inject constructor(
    private val formularioServicioRepository: FormularioServicioRepository,
) {
    suspend operator fun invoke() {
        formularioServicioRepository.remoteFormularioServicio()
    }

}