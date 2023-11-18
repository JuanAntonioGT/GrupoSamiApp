package com.gruposami.gruposamiapp.data.repositories

import com.gruposami.gruposamiapp.data.database.dao.OrdenDao
import com.gruposami.gruposamiapp.data.database.entities.toDatabase
import com.gruposami.gruposamiapp.data.network.orden.OrdenManagement
import com.gruposami.gruposamiapp.data.network.orden.OrdenManagementEnviar
import com.gruposami.gruposamiapp.data.network.orden.OrdenService
import com.gruposami.gruposamiapp.domain.login.useCase.RefrescarToken
import com.gruposami.gruposamiapp.domain.orden.model.Orden
import com.gruposami.gruposamiapp.domain.orden.model.OrdenCompleta
import com.gruposami.gruposamiapp.domain.orden.model.OrdenCompletaResponse
import com.gruposami.gruposamiapp.domain.orden.model.OrdenEstado
import com.gruposami.gruposamiapp.domain.orden.model.toDomain
import com.gruposami.gruposamiapp.ui.login.model.Comprobacion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
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

//    suspend fun enviarOrden(ordenCompleta: OrdenCompleta): Response<OrdenCompletaResponse> {
    suspend fun enviarOrdenApi(ordenCompleta: OrdenCompleta): OrdenManagementEnviar {
        return withContext(Dispatchers.IO) {
            var enviarOrdenApi = ordenService.enviarOrdenApi(ordenCompleta)
            if (enviarOrdenApi.response != null){
                if (enviarOrdenApi.response!!.code() == 401) {
                    // Parece que el token de sesión a caducado.
                    // Comprobar de nuevo el token de sesión.
                    val refrescarToken: Comprobacion = refrescarTokenUseCase.invoke()
                    enviarOrdenApi.comprobacion = refrescarToken.booleano
                    enviarOrdenApi.mensaje = refrescarToken.mensaje!!
                    if (refrescarToken.booleano) {
                        enviarOrdenApi = ordenService.enviarOrdenApi(ordenCompleta)
                    }
                }
            }
            enviarOrdenApi
        }
    }

    /* Llamadas a la BBDD */
    suspend fun insertarOrden(orden: Orden) {
        ordenDao.insertarOrden(orden.toDatabase())
    }

    suspend fun insertarOrdenEstado(ordenEstado: OrdenEstado) {
        ordenDao.insertarOrdenEstado(ordenEstado.toDatabase())
    }

    suspend fun obtenerOrdenesBD(): List<OrdenCompleta> {
        val response = ordenDao.obtenerOrdenesCompletas()
        return response.map { it.toDomain() }
    }

    suspend fun obtenerOrdenesPorEstado(estado: String): List<OrdenCompleta> {
        val response = ordenDao.obtenerOrdenPorEstado(estado)
        return if (response.isNotEmpty()){
            response.map { it.toDomain() }
        } else {
            emptyList()
        }

    }

    suspend fun eliminarOrden(orden: Orden) {
        ordenDao.eliminarOrden(orden.id!!)
    }

    suspend fun eliminarOrdenes() {
        ordenDao.eliminarOrdenes()
    }
}