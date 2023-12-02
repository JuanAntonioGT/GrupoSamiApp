package com.gruposami.gruposamiapp.data.repositories


import com.gruposami.gruposamiapp.data.database.dao.MultimediaDao
import com.gruposami.gruposamiapp.data.database.entities.MultimediaEntity
import com.gruposami.gruposamiapp.data.database.entities.toDatabase
import com.gruposami.gruposamiapp.data.network.multimedia.MultimediaService
import com.gruposami.gruposamiapp.domain.multimedia.model.Multimedia
import com.gruposami.gruposamiapp.domain.multimedia.model.toDomain
import com.gruposami.gruposamiapp.domain.orden.model.CambioId
import javax.inject.Inject

class MultimediaRepository @Inject constructor(
    private val multimediaDao: MultimediaDao,
    private val multimediaService: MultimediaService
) {
    // Remote
    suspend fun enviarMultimedia(multimedia: Multimedia, nuevaId: Int) {
        val response = multimediaService.enviarMultimedia(multimedia, nuevaId)
        println(response)
    }

    // Local
    suspend fun insertarMultimedia(multimedia: Multimedia) {
        multimediaDao.insertarMultimedia(multimedia.toDatabase())
    }

    suspend fun obtenerMultimedia(multimedia: Multimedia): MultimediaEntity {
        return multimediaDao.obtenerMultimediaFiltrado(
            multimedia.servicioId,
            multimedia.tipoArchivo,
            multimedia.categoriaArchivo,
            multimedia.etiquetaArchivo
        )
    }

    suspend fun obtenerMultimediaId(id_multimedia: Int): Multimedia {
        return multimediaDao.obtenerMultimediaPorId(id_multimedia).toDomain()
    }

    suspend fun modificarMultimediaId(cambio: CambioId) {
        // Cargar un modelo
        val multimedia = obtenerMultimediaId(cambio.anteriorId)
        enviarMultimedia(multimedia, cambio.nuevaId)

        multimediaDao.modificarMultimediaId(cambio.anteriorId, cambio.nuevaId)
    }

    suspend fun eliminarMultimedia(multimedia: Multimedia) {
        multimediaDao.eliminarMultimedia(multimedia.id!!)
    }

}