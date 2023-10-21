package com.gruposami.gruposamiapp.data.repositories

import com.gruposami.gruposamiapp.data.database.dao.SesionDao
import com.gruposami.gruposamiapp.data.database.entities.SesionEntity
import com.gruposami.gruposamiapp.data.database.entities.toDatabase
import com.gruposami.gruposamiapp.data.models.LoginResponse
import com.gruposami.gruposamiapp.domain.sesion.model.Sesion
import com.gruposami.gruposamiapp.network.model.LoginManagement
import com.gruposami.gruposamiapp.network.model.LoginRequest
import com.gruposami.gruposamiapp.network.services.LoginService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject


class SesionRepository @Inject constructor(
    private val sesionDao: SesionDao,
    private val loginService: LoginService,
) {

    /* Llamadas a la API */
    suspend fun loginRepository(loginRequest: LoginRequest): LoginManagement {
        return withContext(Dispatchers.IO) {
            loginService.getUserService(loginRequest)
        }
    }

    /* Llamadas a la BBDD */

    suspend fun crearSesion(sesion: Sesion) {
        sesionDao.crearSesion(sesion.toDatabase())
    }

    /* Esto comprobará si la sesíón está registrada */
    suspend fun comprobarSesion(): SesionEntity? {
        return sesionDao.obtenerSesion()
    }

    suspend fun cerrarSesion() {
        sesionDao.cerrarSesion()
    }

}