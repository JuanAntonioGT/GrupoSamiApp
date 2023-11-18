package com.gruposami.gruposamiapp.data.network.formularioservicio

import android.util.Log
import com.gruposami.gruposamiapp.data.network.formularioservicio.model.FormularioServicioRelacionesResponse
import com.gruposami.gruposamiapp.data.network.formularioservicio.model.FormularioServicioResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import javax.inject.Inject

class FormularioServicioService @Inject constructor(
    private val formularioServicioApiClient: FormularioServicioApiClient
) {

    suspend fun getFormularioServicioService(): FormularioServicioManagement {
        return withContext(Dispatchers.IO) {
            try {
                val response = formularioServicioApiClient.getFormularioServicioResponse()
                FormularioServicioManagement(true, "Correcto", response)
            } catch (e: ConnectException) {
                val mensaje = "Error de conexión de tu dispositivo."
                Log.e("FormularioService", "ConnectException: $e")
                FormularioServicioManagement(false, mensaje, null)
            } catch (e: SocketTimeoutException) {
                val mensaje = "Parece que el servidor no se encuentra operativo."
                // En este punto la petición se puede volver a mandar a una cola de espera
                Log.e("FormularioService", "SocketTimeoutException: $e")
                FormularioServicioManagement(false, mensaje, null)
            } catch (e: Exception) {
                val mensaje = "Error desconocido."
                Log.e("FormularioService", "Exception: $e")
                FormularioServicioManagement(false, mensaje, null)
            }
        }
    }

    suspend fun getFormularioServicioRelacionesService(): FormularioServicioRelacionesManagement {
        return withContext(Dispatchers.IO) {
            try {
                val response = formularioServicioApiClient.getFormularioServicioRelacionesResponse()
                FormularioServicioRelacionesManagement(true, "Correcto", response)
            } catch (e: ConnectException) {
                val mensaje = "Error de conexión de tu dispositivo."
                Log.e("FormularioRelacionesService", "ConnectException: $e")
                FormularioServicioRelacionesManagement(false, mensaje, null)
            } catch (e: SocketTimeoutException) {
                val mensaje = "Parece que el servidor no se encuentra operativo."
                // En este punto la petición se puede volver a mandar a una cola de espera
                Log.e("FormularioRelacionesService", "SocketTimeoutException: $e")
                FormularioServicioRelacionesManagement(false, mensaje, null)
            } catch (e: Exception) {
                val mensaje = "Error desconocido."
                Log.e("FormularioRelacionesService", "Exception: $e")
                FormularioServicioRelacionesManagement(false, mensaje, null)
            }
        }
    }
}

data class FormularioServicioManagement(
    var comprobacion: Boolean,
    var mensaje: String,
    val response: Response<List<FormularioServicioResponse>>?
)

data class FormularioServicioRelacionesManagement(
    var comprobacion: Boolean,
    var mensaje: String,
    val response: Response<List<FormularioServicioRelacionesResponse>>?
)
