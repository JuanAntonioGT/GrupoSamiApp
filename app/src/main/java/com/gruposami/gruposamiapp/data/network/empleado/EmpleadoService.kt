package com.gruposami.gruposamiapp.data.network.empleado

import android.util.Log
import com.gruposami.gruposamiapp.data.network.empleado.model.EmpleadoResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import javax.inject.Inject

class EmpleadoService @Inject constructor(private val empleadoApiClient: EmpleadoApiClient) {

    suspend fun getEmpleadoService(): EmpleadoManagement {
        return withContext(Dispatchers.IO) {
            try {
                val response = empleadoApiClient.getEmpleadoResponse()
                EmpleadoManagement(true, "Correcto", response)
            } catch (e: ConnectException) {
                val mensaje = "Error de conexión de tu dispositivo."
                Log.e("EmpleadoService", "ConnectException: $e")
                EmpleadoManagement(false, mensaje, null)
            } catch (e: SocketTimeoutException) {
                val mensaje = "Parece que el servidor no se encuentra operativo."
                // En este punto la petición se puede volver a mandar a una cola de espera
                Log.e("EmpleadoService", "SocketTimeoutException: $e")
                EmpleadoManagement(false, mensaje, null)
            } catch (e: Exception) {
                val mensaje = "Error desconocido."
                Log.e("EmpleadoService", "Exception: $e")
                EmpleadoManagement(false, mensaje, null)
            }
        }
    }
}

data class EmpleadoManagement(
    var comprobacion: Boolean,
    var mensaje: String,
    val response: Response<List<EmpleadoResponse>>?
)
