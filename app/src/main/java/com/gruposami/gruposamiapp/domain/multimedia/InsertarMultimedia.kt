package com.gruposami.gruposamiapp.domain.multimedia


import com.gruposami.gruposamiapp.data.repositories.MultimediaRepository
import com.gruposami.gruposamiapp.domain.multimedia.model.Multimedia
import javax.inject.Inject

class InsertarMultimedia @Inject constructor(
    private val multimediaRepository: MultimediaRepository
) {
    suspend operator fun invoke(multimedia: Multimedia){
        multimediaRepository.insertarMultimedia(multimedia)
    }
}