package com.gruposami.gruposamiapp.data.network.empleado

import com.gruposami.gruposamiapp.data.network.empleado.model.EmpleadoResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EmpleadoService @Inject constructor(private val empleadoApiClient: EmpleadoApiClient) {

    suspend fun getEmpleadoService(): List<EmpleadoResponse> {
        return withContext(Dispatchers.IO) {
            val response = empleadoApiClient.getEmpleadoResponse()
            response.body() ?: emptyList()
        }
    }
}