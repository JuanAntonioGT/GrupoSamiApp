package com.gruposami.gruposamiapp.data.repositories

import com.gruposami.gruposamiapp.data.database.dao.SesionDao
import com.gruposami.gruposamiapp.data.database.entities.SesionEntity
import com.gruposami.gruposamiapp.data.database.entities.toDatabase
import com.gruposami.gruposamiapp.data.network.login.LoginManagement
import com.gruposami.gruposamiapp.data.network.login.LoginService
import com.gruposami.gruposamiapp.domain.sesion.model.Sesion
import com.gruposami.gruposamiapp.ui.login.model.LoginRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class SesionRepository @Inject constructor(
    private val sesionDao: SesionDao,
    private val loginService: LoginService,
) {

    /* Llamadas a la API */
    suspend fun loginRepository(loginRequest: LoginRequest): LoginManagement {
        // Creo que la responsabilidad de comprobar los códigos, la tendría que tener aquí.
        return withContext(Dispatchers.IO) {
            val comprobacionLogin = loginService.getLoginService(loginRequest)
            if (comprobacionLogin.response != null){
                if (comprobacionLogin.response.code() == 401) {
                    comprobacionLogin.comprobacion = false
                    comprobacionLogin.mensaje = "Usuario o contraseña incorrectos."
                }
                if (comprobacionLogin.response.code() == 403) {
                    comprobacionLogin.comprobacion = false
                    comprobacionLogin.mensaje = "No tienes permiso para acceder a la aplicación."
                }
            }
            comprobacionLogin
        }
    }

    /* Llamadas a la BBDD */
    suspend fun crearSesion(sesion: Sesion) {
        sesionDao.insertarSesion(sesion.toDatabase())
    }

    /* Esto comprobará si la sesíón está registrada */
    suspend fun comprobarSesion(): SesionEntity? {
        return sesionDao.obtenerSesion()
    }

    suspend fun cerrarSesion() {
        sesionDao.cerrarSesion()
    }

}