package com.gruposami.gruposamiapp.data.network.orden

import android.util.Log
import com.gruposami.gruposamiapp.data.network.orden.model.OrdenResponse
import com.gruposami.gruposamiapp.domain.orden.model.OrdenCompleta
import com.gruposami.gruposamiapp.domain.orden.model.OrdenCompletaResponse
import com.gruposami.gruposamiapp.utils.timestamp
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

    suspend fun enviarOrdenApi(orden: OrdenCompleta): OrdenManagementEnviar {
        return withContext(Dispatchers.IO) {
            try {
                // Añadir a la OrdenCompleta la hora del movil actualmente para que se hagan cálculo.
                orden.horaMovil = timestamp()
                val response = ordenApiClient.enviarOrden(orden.orden.id!!, orden)

                OrdenManagementEnviar(true, "Correcto", response)
            } catch (e: ConnectException) {
                val mensaje = "Error de conexión de tu dispositivo."
                Log.e("OrdenService", "ConnectException: $e")
                OrdenManagementEnviar(false, mensaje, null)
            } catch (e: SocketTimeoutException) {
                val mensaje = "Parece que el servidor no se encuentra operativo."
                // En este punto la petición se puede volver a mandar a una cola de espera
                Log.e("OrdenService", "SocketTimeoutException: $e")
                OrdenManagementEnviar(false, mensaje, null)
            } catch (e: Exception) {
                val mensaje = "Error desconocido."
                Log.e("OrdenService", "Exception: $e")
                OrdenManagementEnviar(false, mensaje, null)
            }
        }
    }

}

data class OrdenManagement(
    var comprobacion: Boolean,
    var mensaje: String,
    val response: Response<List<OrdenResponse>>?
)

data class OrdenManagementEnviar(
    var comprobacion: Boolean,
    var mensaje: String,
    val response: Response<OrdenCompletaResponse>?
)

