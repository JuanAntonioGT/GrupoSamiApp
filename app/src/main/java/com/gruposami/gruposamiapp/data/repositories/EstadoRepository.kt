package com.gruposami.gruposamiapp.data.repositories


import com.gruposami.gruposamiapp.data.database.dao.EstadoDao
import com.gruposami.gruposamiapp.data.database.entities.toDatabase
import com.gruposami.gruposamiapp.domain.estado.model.Estado
import com.gruposami.gruposamiapp.domain.orden.model.CambioId
import javax.inject.Inject

class EstadoRepository @Inject constructor(
    private val estadoDao: EstadoDao
) {

    suspend fun insertarEstado(estado: Estado) {
        estadoDao.insertarEstado(estado.toDatabase())
    }

    suspend fun modificarEstadoId(servicio: CambioId) {
        estadoDao.modificarEstadoId(servicio.anteriorId, servicio.nuevaId)
    }

//
//    suspend fun eliminarEstado(id: Int) {
//        estadoDao.eliminarEstado(id)
//    }

}
