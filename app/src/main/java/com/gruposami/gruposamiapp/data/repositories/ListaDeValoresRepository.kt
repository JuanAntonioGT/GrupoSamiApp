package com.gruposami.gruposamiapp.data.repositories

import com.gruposami.gruposamiapp.data.database.dao.ListaDeValoresDao
import com.gruposami.gruposamiapp.data.database.entities.ListaDeValoresEntity
import com.gruposami.gruposamiapp.data.network.listadevalores.ListaDeValoresManagement
import com.gruposami.gruposamiapp.data.network.listadevalores.ListaDeValoresService
import com.gruposami.gruposamiapp.domain.listadevalores.model.ListaDeValores
import com.gruposami.gruposamiapp.domain.listadevalores.model.toDomain
import com.gruposami.gruposamiapp.domain.login.useCase.RefrescarToken
import com.gruposami.gruposamiapp.ui.login.model.Comprobacion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ListaDeValoresRepository @Inject constructor(
    private val listaDeValoresService: ListaDeValoresService,
    private val listaDeValoresDao: ListaDeValoresDao,
    private val refrescarTokenUseCase: RefrescarToken,
) {

    /** Función que llama a la API para traerse los datos del Selector */
    suspend fun listaDeValoresAPI(): ListaDeValoresManagement {
        return withContext(Dispatchers.IO) {
            var obtenerListaDeValores = listaDeValoresService.getListaDeValoresService()
            if (obtenerListaDeValores.response != null) {
                if (obtenerListaDeValores.response!!.code() == 401) {
                    // Parece que el token de sesión a caducado.
                    // Comprobar de nuevo el token de sesión.
                    val refrescarToken: Comprobacion = refrescarTokenUseCase.invoke()
                    obtenerListaDeValores.comprobacion = refrescarToken.booleano
                    obtenerListaDeValores.mensaje = refrescarToken.mensaje!!
                    if (refrescarToken.booleano) {
                        obtenerListaDeValores = listaDeValoresService.getListaDeValoresService()
                    }
                }
            }
            obtenerListaDeValores
        }
    }

    /** Función para guardar la lista del selector en la bbdd */
    suspend fun insertarListaDeValores(listadevaloresEntity: List<ListaDeValoresEntity>) {
        listaDeValoresDao.insertarListaDeValores(listadevaloresEntity)
    }

//    /** Función para guardar un selector en la bbdd */
//    suspend fun insertarSelector(selector: SelectorEntity) {
//        selectorDao.insertarSelector(selector)
//    }

    /** Función para eliminar todos los datos del selector guardados */
    suspend fun eliminarListaDeValores() {
        listaDeValoresDao.eliminarListaDeValores()
    }

    suspend fun obtenerListaVidrios(): List<ListaDeValores> {
        return listaDeValoresDao.obtenerListaVidrios().map { it.toDomain() }
    }

    suspend fun obtenerListaVidriosDescatalogados(): List<ListaDeValores> {
        return listaDeValoresDao.obtenerListaVidriosDescatalogados().map { it.toDomain() }
    }

}