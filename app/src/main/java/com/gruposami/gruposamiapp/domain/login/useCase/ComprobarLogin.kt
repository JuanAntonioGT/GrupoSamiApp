package com.gruposami.gruposamiapp.domain.login.useCase

import com.gruposami.gruposamiapp.data.repositories.SesionRepository
import com.gruposami.gruposamiapp.domain.sesion.model.Sesion
import com.gruposami.gruposamiapp.domain.sesion.useCase.CrearSesion
import com.gruposami.gruposamiapp.ui.login.model.Comprobacion
import com.gruposami.gruposamiapp.ui.login.model.LoginRequest
import javax.inject.Inject


class ComprobarLogin @Inject constructor(
    private val repository: SesionRepository,
    private val crearSesion: CrearSesion,
) {
    /**
    Esta petición devolverá un:
    200 si está correcto,
    401 si esta incorrecto y
    403 si no tienes permiso -> Cerrar Sesión.
     */

    suspend operator fun invoke(loginRequest: LoginRequest): Comprobacion {
        // Esta clase sólo debería comprobar si tiene la comprobación correcta o no.
        // Si es correcta, que se guarde el usuario, sino, devolver el mensaje de error.

        val loginResponse = repository.loginRepository(loginRequest)
        val comprobacion = Comprobacion(loginResponse.comprobacion, loginResponse.mensaje)

        if (loginResponse.comprobacion && loginResponse.response != null) {
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
                ))
        }
        return comprobacion
    }
}
