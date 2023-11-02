package com.gruposami.gruposamiapp.domain.login.useCase

import com.gruposami.gruposamiapp.data.network.login.LoginManagement
import com.gruposami.gruposamiapp.data.repositories.SesionRepository
import com.gruposami.gruposamiapp.domain.sesion.model.Sesion
import com.gruposami.gruposamiapp.domain.sesion.useCase.CrearSesion
import com.gruposami.gruposamiapp.ui.login.model.Comprobacion
import com.gruposami.gruposamiapp.ui.login.model.LoginRequest
import javax.inject.Inject

class RefrescarToken @Inject constructor(
    private val repository: SesionRepository,
    private val crearSesion: CrearSesion,
) {
    /**
    Esta petición devolverá un:
    200 si está correcto,
    401 si esta incorrecto
     */

    suspend operator fun invoke(): Comprobacion {
        val usuarioctual = repository.comprobarSesion()
        if (usuarioctual != null) {
            val loginRequest = LoginRequest(usuarioctual.usuario!!, usuarioctual.contrasena!!)
            val loginResponse: LoginManagement = repository.loginRepository(loginRequest)
            if (loginResponse.response != null) {
                if (loginResponse.response.code() == 200) {
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
                    return Comprobacion(loginResponse.comprobacion, loginResponse.mensaje)
                }
                return Comprobacion(false, " No tienes permiso para acceder a la aplicación.")
            }
            return Comprobacion(false, " No tienes permiso para acceder a la aplicación.")
        }
        return Comprobacion(false, "Datos de sesión incorrectos. Es necesario que entres en la app de nuevo.")
    }
}
