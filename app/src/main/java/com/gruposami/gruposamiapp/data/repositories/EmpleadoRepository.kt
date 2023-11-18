package com.gruposami.gruposamiapp.data.repositories

import com.gruposami.gruposamiapp.data.database.dao.EmpleadoDao
import com.gruposami.gruposamiapp.data.database.entities.EmpleadoEntity
import com.gruposami.gruposamiapp.data.network.empleado.EmpleadoManagement
import com.gruposami.gruposamiapp.data.network.empleado.EmpleadoService
import com.gruposami.gruposamiapp.domain.login.useCase.RefrescarToken
import com.gruposami.gruposamiapp.ui.login.model.Comprobacion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EmpleadoRepository @Inject constructor(
    private val empleadoService: EmpleadoService,
    private val empleadoDao: EmpleadoDao,
    private val refrescarTokenUseCase: RefrescarToken,
) {
    /* Llamadas a la API */
    suspend fun listaEmpleadosRepository(): EmpleadoManagement {
        return withContext(Dispatchers.IO) {
            var obtenerEmpleados = empleadoService.getEmpleadoService()
            if (obtenerEmpleados.response != null) {
                if (obtenerEmpleados.response!!.code() == 401) {
                    // Parece que el token de sesión a caducado.
                    // Comprobar de nuevo el token de sesión.
                    val refrescarToken: Comprobacion = refrescarTokenUseCase.invoke()
                    obtenerEmpleados.comprobacion = refrescarToken.booleano
                    obtenerEmpleados.mensaje = refrescarToken.mensaje!!
                    if (refrescarToken.booleano) {
                        obtenerEmpleados = empleadoService.getEmpleadoService()
                    }
                }
            }
            obtenerEmpleados
        }
    }


    /* Llamadas a la BBDD */
    suspend fun insertarEmpleado(empleado: EmpleadoEntity) {
        empleadoDao.insertarEmpleado(empleado)
    }

    suspend fun obtenerEmpleadoSesion(): EmpleadoEntity {
        return empleadoDao.obtenerEmpleadoSesion()
    }

}