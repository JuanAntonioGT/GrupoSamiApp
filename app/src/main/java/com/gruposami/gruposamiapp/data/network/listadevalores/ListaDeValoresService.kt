package com.gruposami.gruposamiapp.data.network.listadevalores

import com.gruposami.gruposamiapp.data.network.listadevalores.model.ListaDeValoresResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ListaDeValoresService @Inject constructor(private val listaDeValoresApiClient: ListaDeValoresApiClient) {

    suspend fun getListaDeValoresService(): List<ListaDeValoresResponse> {
        return withContext(Dispatchers.IO) {
            val response = listaDeValoresApiClient.getSelectorResponse()
            response.body() ?: emptyList()
        }
    }
}