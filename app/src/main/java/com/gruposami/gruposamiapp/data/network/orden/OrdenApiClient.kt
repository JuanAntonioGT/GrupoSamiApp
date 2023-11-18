package com.gruposami.gruposamiapp.data.network.orden

import com.gruposami.gruposamiapp.data.network.orden.model.OrdenResponse
import com.gruposami.gruposamiapp.domain.orden.model.OrdenCompleta
import com.gruposami.gruposamiapp.domain.orden.model.OrdenCompletaResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface OrdenApiClient {
    @GET("orden/lista")
    suspend fun getOrdenResponse(): Response<List<OrdenResponse>>

    @PUT("orden/modificar/{id}")
    suspend fun enviarOrden(@Path("id") id: Int, @Body ordenrequest: OrdenCompleta): Response<OrdenCompletaResponse>

}