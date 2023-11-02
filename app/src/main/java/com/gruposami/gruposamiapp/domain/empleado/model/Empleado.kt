package com.gruposami.gruposamiapp.domain.empleado.model

import com.gruposami.gruposamiapp.data.database.entities.EmpleadoEntity
import com.gruposami.gruposamiapp.data.network.empleado.model.EmpleadoResponse

data class Empleado(
    var id: Int?,
    var username: String?,
    var firstName: String?,
    var lastName: String?,
    var avatar: String?,
)

fun EmpleadoResponse.toDomain() = Empleado(
    id = id,
    username = username,
    firstName = firstName,
    lastName = lastName,
    avatar = avatar,
)

fun EmpleadoEntity.toDomain() = Empleado(
    id = id,
    username = username,
    firstName = firstName,
    lastName = lastName,
    avatar = avatar
)
