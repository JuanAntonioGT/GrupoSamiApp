package com.gruposami.gruposamiapp.domain.multimedia

import com.gruposami.gruposamiapp.data.repositories.MultimediaRepository
import com.gruposami.gruposamiapp.domain.orden.model.CambioId
import javax.inject.Inject

class ModificarMultimediaId @Inject constructor(private val multimediaRepository: MultimediaRepository) {
    suspend operator fun invoke(cambio: List<CambioId?>){
        cambio.map {
            if (it != null) {
                multimediaRepository.modificarMultimediaId(it)
            }
        }
    }
}