package com.gruposami.gruposamiapp.data.network.listadevalores

import com.gruposami.gruposamiapp.data.network.listadevalores.model.ListaDeValoresResponse
import retrofit2.Response
import retrofit2.http.GET

interface ListaDeValoresApiClient {
    @GET("valores/lista")
    suspend fun getSelectorResponse(): Response<List<ListaDeValoresResponse>>
}