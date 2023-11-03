package com.gruposami.gruposamiapp.data.network.orden

import android.util.Log
import com.gruposami.gruposamiapp.data.network.orden.model.OrdenResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import javax.inject.Inject


class OrdenService @Inject constructor(private val ordenApiClient: OrdenApiClient) {

    suspend fun getOrdenService(): OrdenManagement {
        return withContext(Dispatchers.IO) {
            try {
                val response = ordenApiClient.getOrdenResponse()
                OrdenManagement(true, "Correcto", response)
            } catch (e: ConnectException) {
                val mensaje = "Error de conexión de tu dispositivo."
                Log.e("OrdenService", "ConnectException: $e")
                OrdenManagement(false, mensaje, null)
            } catch (e: SocketTimeoutException) {
                val mensaje = "Parece que el servidor no se encuentra operativo."
                // En este punto la petición se puede volver a mandar a una cola de espera
                Log.e("OrdenService", "SocketTimeoutException: $e")
                OrdenManagement(false, mensaje, null)
            } catch (e: Exception) {
                val mensaje = "Error desconocido."
                Log.e("OrdenService", "Exception: $e")
                OrdenManagement(false, mensaje, null)
            }
        }
    }
}

data class OrdenManagement(
    var comprobacion: Boolean,
    var mensaje: String,
    val response: Response<List<OrdenResponse>>?
)
