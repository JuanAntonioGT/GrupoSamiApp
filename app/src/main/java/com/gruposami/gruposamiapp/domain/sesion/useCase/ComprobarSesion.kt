package com.gruposami.gruposamiapp.domain.sesion.useCase

import com.gruposami.gruposamiapp.data.repositories.SesionRepository
import com.gruposami.gruposamiapp.domain.sesion.model.Sesion
import javax.inject.Inject

class ComprobarSesion @Inject constructor(
    private val sesionRepository: SesionRepository
) {

    suspend operator fun invoke(): Boolean {
        val comprobarSesion = sesionRepository.comprobarSesion()

        /* El objeto observable por toda la app se le carga con el usuario, usuario_id y token */
        if (comprobarSesion != null) {
            Sesion.usuario = comprobarSesion.usuario.toString()
            Sesion.usuarioId = comprobarSesion.usuarioId!!
            Sesion.token = comprobarSesion.token.toString()
            return comprobarSesion.logueado!!
        }
        return false

    }
}