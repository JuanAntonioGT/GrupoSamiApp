package com.gruposami.gruposamiapp.data.repositories

import com.gruposami.gruposamiapp.data.database.dao.DireccionDao
import com.gruposami.gruposamiapp.data.database.entities.toDatabase
import com.gruposami.gruposamiapp.domain.direccion.model.Direccion
import javax.inject.Inject


class DireccionRepository @Inject constructor(
    private val direccionDao: DireccionDao
) {

    suspend fun insertarDireccion(direccion: Direccion) {
        direccionDao.insertarDireccion(direccion.toDatabase())
    }

//    suspend fun eliminarDireccion(id: Int) {
//        direccionDao.eliminarDireccion(id)
//    }

}