package com.gruposami.gruposamiapp.data.network.formularioservicio


import com.gruposami.gruposamiapp.data.network.formularioservicio.model.FormularioServicioRelacionesResponse
import com.gruposami.gruposamiapp.data.network.formularioservicio.model.FormularioServicioResponse
import retrofit2.Response
import retrofit2.http.GET

interface FormularioServicioApiClient {
    @GET("valores/formulario")
    suspend fun getFormularioServicioResponse(): Response<List<FormularioServicioResponse>>

    @GET("valores/formulario_relaciones")
    suspend fun getFormularioServicioRelacionesResponse(): Response<List<FormularioServicioRelacionesResponse>>
}