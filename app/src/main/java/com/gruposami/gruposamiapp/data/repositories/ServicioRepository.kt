package com.gruposami.gruposamiapp.data.repositories

import com.gruposami.gruposamiapp.data.database.dao.ServicioDao
import com.gruposami.gruposamiapp.data.database.entities.ServicioEntity
import com.gruposami.gruposamiapp.data.database.entities.toDatabase
import com.gruposami.gruposamiapp.domain.orden.model.CambioId
import com.gruposami.gruposamiapp.domain.orden.model.OrdenEstado
import com.gruposami.gruposamiapp.domain.servicio.model.Servicio
import com.gruposami.gruposamiapp.domain.servicio.model.ServicioCompleto
import com.gruposami.gruposamiapp.domain.servicio.model.ServicioEstado

import com.gruposami.gruposamiapp.domain.servicio.model.ServicioMedido
import com.gruposami.gruposamiapp.domain.servicio.model.ServicioMontado
import com.gruposami.gruposamiapp.domain.servicio.model.ServicioPendiente
import com.gruposami.gruposamiapp.domain.servicio.model.toDomain
import javax.inject.Inject

class ServicioRepository @Inject constructor(
    private val servicioDao: ServicioDao
) {

    suspend fun insertarServicioBBDD(servicio: Servicio) {
        servicioDao.insertarServicio(servicio.toDatabase())
    }

    suspend fun insertarServicioEstado(servicioEstado: ServicioEstado) {
        servicioDao.insertarServicioEstado(servicioEstado.toDatabase())
    }

    suspend fun insertarServicioMedido(servicioMedido: ServicioMedido) {
        servicioDao.insertarServicioMedido(servicioMedido.toDatabase())
    }

    suspend fun insertarServicioMontado(servicioMontado: ServicioMontado) {
        servicioDao.insertarServicioMontado(servicioMontado.toDatabase())
    }

    suspend fun insertarServicioPendiente(servicioPendiente: ServicioPendiente) {
        servicioDao.insertarServicioPendiente(servicioPendiente.toDatabase())
    }

    suspend fun obtenerServicioPorOrden(orden_id: Int): List<ServicioCompleto> {
        val response = servicioDao.obtenerServiciosPorOrden(orden_id)
        return response.map { it.toDomain() }
    }

    suspend fun obtenerServicioPorId(idServicio: Int): Servicio {
        val response = servicioDao.obtenerServicio(idServicio)
        return response.toDomain()
    }

    suspend fun modificarServicio(servicio: ServicioEntity) {
        servicioDao.modificarServicio(servicio)
    }

    suspend fun modificarServicioId(servicio: CambioId?) {
        servicioDao.modificarServicioId(servicio?.anteriorId, servicio?.nuevaId)
    }

    suspend fun eliminarServicio(servicioId: Int) {
        servicioDao.eliminarServicio(servicioId)
    }

}