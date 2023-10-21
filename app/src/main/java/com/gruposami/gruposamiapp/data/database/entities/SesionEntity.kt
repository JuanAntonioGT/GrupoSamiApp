package com.gruposami.gruposamiapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gruposami.gruposamiapp.domain.sesion.model.Sesion


@Entity(tableName = "sesion_table")
data class SesionEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id_sesion") val id: Int = 0,
    @ColumnInfo(name = "usuario") var usuario: String?,
    @ColumnInfo(name = "contrasena") var contrasena: String?,
    @ColumnInfo(name = "usuario_id") var usuarioId: Int?,
    @ColumnInfo(name = "logueado") val logueado: Boolean?,
    @ColumnInfo(name = "token") var token: String?,
    @ColumnInfo(name = "filtro_estado") var filtroEstado: String?,
    @ColumnInfo(name = "filtro_formulario") var filtroFormulario: String?,
)

fun Sesion.toDatabase() = SesionEntity(
    usuario = usuario,
    contrasena = contrasena,
    usuarioId = usuarioId,
    logueado = logueado,
    token = token,
    filtroEstado = filtroEstado,
    filtroFormulario = filtroFormulario,
)
