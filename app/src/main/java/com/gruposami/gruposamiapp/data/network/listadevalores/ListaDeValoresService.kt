package com.gruposami.gruposamiapp.data.network.listadevalores

import android.util.Log
import com.gruposami.gruposamiapp.data.network.listadevalores.model.ListaDeValoresResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import javax.inject.Inject

class ListaDeValoresService @Inject constructor(private val listaDeValoresApiClient: ListaDeValoresApiClient) {

    suspend fun getListaDeValoresService(): ListaDeValoresManagement {
        return withContext(Dispatchers.IO) {
            try {
                val response = listaDeValoresApiClient.getSelectorResponse()
                ListaDeValoresManagement(true, "Correcto", response)
            } catch (e: ConnectException) {
                val mensaje = "Error de conexión de tu dispositivo."
                Log.e("ListaDeValoresService", "ConnectException: $e")
                ListaDeValoresManagement(false, mensaje, null)
            } catch (e: SocketTimeoutException) {
                val mensaje = "Parece que el servidor no se encuentra operativo."
                // En este punto la petición se puede volver a mandar a una cola de espera
                Log.e("ListaDeValoresService", "SocketTimeoutException: $e")
                ListaDeValoresManagement(false, mensaje, null)
            } catch (e: Exception) {
                val mensaje = "Error desconocido."
                Log.e("ListaDeValoresService", "Exception: $e")
                ListaDeValoresManagement(false, mensaje, null)
            }
        }
    }
}


data class ListaDeValoresManagement(
    var comprobacion: Boolean,
    var mensaje: String,
    val response: Response<List<ListaDeValoresResponse>>?
)
