package com.gruposami.gruposamiapp.network.services

import android.util.Log
import com.gruposami.gruposamiapp.network.apiClients.LoginApiClient
import com.gruposami.gruposamiapp.network.model.LoginManagement
import com.gruposami.gruposamiapp.network.model.LoginRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.ConnectException
import java.net.SocketTimeoutException
import javax.inject.Inject

class LoginService @Inject constructor(
    private val loginApiClient: LoginApiClient,
) {
    suspend fun getUserService(loginRequest: LoginRequest): LoginManagement {
        return withContext(Dispatchers.IO) {
            try {
                val response = loginApiClient.getLoginResponse(loginRequest)
                LoginManagement(true, "Correcto", response)
            } catch (e: ConnectException) {
                val mensaje = "Error de conexión de tu dispositivo."
                Log.e("LoginService", "ConnectException: $e")
                LoginManagement(false, mensaje, null)
            } catch (e: SocketTimeoutException) {
                val mensaje = "Parece que el servidor no se encuentra operativo."
                // En este punto la petición se puede volver a mandar a una cola de espera
                Log.e("LoginService", "SocketTimeoutException: $e")
                LoginManagement(false, mensaje, null)
            } catch (e: Exception) {
                val mensaje = "Error desconocido."
                Log.e("LoginService", "Exception: $e")
                LoginManagement(false, mensaje, null)
            }
        }

    }
}
