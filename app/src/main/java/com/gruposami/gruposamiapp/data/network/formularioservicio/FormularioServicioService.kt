package com.gruposami.gruposamiapp.data.network.formularioservicio

import com.gruposami.gruposamiapp.data.network.formularioservicio.model.FormularioServicioRelacionesResponse
import com.gruposami.gruposamiapp.data.network.formularioservicio.model.FormularioServicioResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FormularioServicioService @Inject constructor(
    private val formularioServicioApiClient: FormularioServicioApiClient
) {

    suspend fun getFormularioServicioService(): List<FormularioServicioResponse> {
        return withContext(Dispatchers.IO) {
            val response = formularioServicioApiClient.getFormularioServicioResponse()
            response.body() ?: emptyList()
        }
    }

    suspend fun getFormularioServicioRelacionesService(): List<FormularioServicioRelacionesResponse> {
        return withContext(Dispatchers.IO) {
            val response = formularioServicioApiClient.getFormularioServicioRelacionesResponse()
            response.body() ?: emptyList()
        }
    }
}