package com.gruposami.gruposamiapp.data.repositories

import com.gruposami.gruposamiapp.data.database.dao.OrdenDao
import com.gruposami.gruposamiapp.data.database.entities.toDatabase
import com.gruposami.gruposamiapp.data.network.orden.OrdenManagement
import com.gruposami.gruposamiapp.data.network.orden.OrdenService
import com.gruposami.gruposamiapp.domain.login.useCase.RefrescarToken
import com.gruposami.gruposamiapp.domain.orden.model.Orden
import com.gruposami.gruposamiapp.ui.login.model.Comprobacion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class OrdenRepository @Inject constructor(
    private val ordenService: OrdenService,
    private val ordenDao: OrdenDao,
    private val refrescarTokenUseCase: RefrescarToken,
) {

    /* Llamadas a la API */
    suspend fun obtenerOrdenesApi(): OrdenManagement {
        return withContext(Dispatchers.IO) {
            var obtenerOrdenes = ordenService.getOrdenService()
            if (obtenerOrdenes.response != null){
                if (obtenerOrdenes.response!!.code() == 401) {
                    // Parece que el token de sesión a caducado.
                    // Comprobar de nuevo el token de sesión.
                    val refrescarToken: Comprobacion = refrescarTokenUseCase.invoke()
                    obtenerOrdenes.comprobacion = refrescarToken.booleano
                    obtenerOrdenes.mensaje = refrescarToken.mensaje!!
                    if (refrescarToken.booleano) {
                        obtenerOrdenes = ordenService.getOrdenService()
                    }
                }
            }
            obtenerOrdenes
        }
    }

    /* Llamadas a la BBDD */
    suspend fun insertarOrden(orden: Orden) {
        ordenDao.insertarOrden(orden.toDatabase())
    }

    suspend fun eliminarOrdenes() {
        ordenDao.eliminarOrdenes()
    }
}