package com.gruposami.gruposamiapp.data.network.orden

import retrofit2.Response
import retrofit2.http.GET

interface OrdenApiClient {
    @GET("orden/lista")
    suspend fun getOrdenResponse(): Response<List<OrdenResponse>>
}