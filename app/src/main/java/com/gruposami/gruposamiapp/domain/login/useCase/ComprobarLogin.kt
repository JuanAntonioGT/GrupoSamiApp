package com.gruposami.gruposamiapp.domain.login.useCase

import com.gruposami.gruposamiapp.data.repositories.SesionRepository
import com.gruposami.gruposamiapp.domain.sesion.model.Sesion
import com.gruposami.gruposamiapp.domain.sesion.useCase.CrearSesion
import com.gruposami.gruposamiapp.network.model.Comprobacion
import com.gruposami.gruposamiapp.network.model.LoginRequest
import javax.inject.Inject


class ComprobarLogin @Inject constructor(
    private val repository: SesionRepository,
    private val crearSesion: CrearSesion,
) {
    /**
    Esta petición devolverá un:
    200 si está correcto,
    401 si esta incorrecto y
    403 si no tienes permiso
    */

    suspend operator fun invoke(loginRequest: LoginRequest): Comprobacion {
        val comprobacion = Comprobacion(false, null)

        val loginResponse = repository.loginRepository(loginRequest)
        comprobacion.booleano = false
        comprobacion.mensaje = loginResponse.mensaje
        if (loginResponse.response != null) {
            if (loginResponse.response.code() == 200) {
                comprobacion.booleano = true
                comprobacion.mensaje = loginResponse.response.body()?.message
                crearSesion.invoke(
                        Sesion(
                            loginRequest.username,
                            loginRequest.password,
                            loginResponse.response.body()?.userId,
                            true,
                            loginResponse.response.body()?.token.toString(),
                            "",
                            "",
                        )
                    )
                    Sesion.usuario = loginRequest.username
                    Sesion.usuarioId = loginResponse.response.body()?.userId!!
                    Sesion.token = loginResponse.response.body()?.token.toString()
            } else if (loginResponse.response.code() == 401) {
                comprobacion.mensaje = "Usuario o contraseña incorrectos."
            } else if (loginResponse.response.code() == 403) {
                comprobacion.mensaje = "No tienes permiso para acceder a la aplicación."
            }
        }
        return comprobacion
    }
}
