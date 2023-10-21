package com.gruposami.gruposamiapp.domain.sesion.model

import com.gruposami.gruposamiapp.data.database.entities.SesionEntity

data class Sesion(
    var usuario: String?,
    val contrasena: String?,
    val usuarioId: Int?,
    var logueado: Boolean?,
    var token: String?,
    val filtroEstado: String?,
    val filtroFormulario: String?,
) {
    companion object {
        var usuario = ""
        var usuarioId = 0
        var token = ""
        var filtroEstado = ""
        var filtroFormulario = ""
    }
}

fun SesionEntity.toDomain() = Sesion(
    usuario = usuario,
    contrasena = contrasena,
    usuarioId = usuarioId,
    logueado = logueado,
    token = token,
    filtroEstado = filtroEstado,
    filtroFormulario = filtroFormulario,
)



