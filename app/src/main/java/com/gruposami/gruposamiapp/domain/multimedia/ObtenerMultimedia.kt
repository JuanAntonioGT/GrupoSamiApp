package com.gruposami.gruposamiapp.domain.multimedia


import com.gruposami.gruposamiapp.data.repositories.MultimediaRepository
import com.gruposami.gruposamiapp.domain.multimedia.model.Multimedia
import com.gruposami.gruposamiapp.domain.multimedia.model.toDomain
import javax.inject.Inject

class ObtenerMultimedia @Inject constructor(
    private val multimediaRepository: MultimediaRepository
) {
    suspend operator fun invoke(multimedia: Multimedia): Multimedia {
        val response = multimediaRepository.obtenerMultimedia(multimedia)
        return if (response != null) {
            response.toDomain()
        } else {
            Multimedia(0, null,null, null,null,null,null,null)
        }
    }
}