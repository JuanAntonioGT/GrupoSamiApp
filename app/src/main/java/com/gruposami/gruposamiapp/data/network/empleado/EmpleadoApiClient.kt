package com.gruposami.gruposamiapp.data.network.empleado

import com.gruposami.gruposamiapp.data.network.empleado.model.EmpleadoResponse
import retrofit2.Response
import retrofit2.http.GET

interface EmpleadoApiClient {

    @GET("empleado/lista")
    suspend fun getEmpleadoResponse(): Response<List<EmpleadoResponse>>

}