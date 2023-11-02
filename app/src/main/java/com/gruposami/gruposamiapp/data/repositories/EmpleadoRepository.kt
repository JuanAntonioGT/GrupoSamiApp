package com.gruposami.gruposamiapp.data.repositories

import com.gruposami.gruposamiapp.data.database.dao.EmpleadoDao
import com.gruposami.gruposamiapp.data.database.entities.EmpleadoEntity
import com.gruposami.gruposamiapp.data.network.empleado.model.EmpleadoResponse
import com.gruposami.gruposamiapp.data.network.empleado.EmpleadoService
import javax.inject.Inject

class EmpleadoRepository @Inject constructor(
    private val empleadoService: EmpleadoService,
    private val empleadoDao: EmpleadoDao,
) {
    /* Llamadas a la API */
    suspend fun listaEmpleadosRepository(): List<EmpleadoResponse> {
        return empleadoService.getEmpleadoService()
    }

    /* Llamadas a la BBDD */
    suspend fun insertarEmpleado(empleado: EmpleadoEntity) {
        empleadoDao.insertarEmpleado(empleado)
    }

    suspend fun obtenerEmpleadoSesion(): EmpleadoEntity {
        return empleadoDao.obtenerEmpleadoSesion()
    }

}