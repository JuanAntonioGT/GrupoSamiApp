package com.gruposami.gruposamiapp.data.repositories

import com.gruposami.gruposamiapp.data.database.dao.ListaDeValoresDao
import com.gruposami.gruposamiapp.data.database.entities.ListaDeValoresEntity
import com.gruposami.gruposamiapp.data.network.listadevalores.ListaDeValoresResponse
import com.gruposami.gruposamiapp.data.network.listadevalores.ListaDeValoresService
import com.gruposami.gruposamiapp.domain.listadevalores.model.ListaDeValores
import com.gruposami.gruposamiapp.domain.listadevalores.model.toDomain
import javax.inject.Inject

class ListaDeValoresRepository @Inject constructor(
    private val listaDeValoresService: ListaDeValoresService,
    private val listaDeValoresDao: ListaDeValoresDao,
) {

    /** Función que llama a la API para traerse los datos del Selector */
    suspend fun listaDeValoresAPI(): List<ListaDeValores> {
        val response: List<ListaDeValoresResponse> = listaDeValoresService.getListaDeValoresService()


        return response.map { it.toDomain() }
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
        return listaDeValoresDao.obtenerListaVidriosDescatalogados().map{ it.toDomain() }
    }

}