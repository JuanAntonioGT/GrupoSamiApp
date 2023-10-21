package com.gruposami.gruposamiapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gruposami.gruposamiapp.domain.empleado.model.Empleado


@Entity(tableName = "empleado_table")
data class EmpleadoEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id_empleado") val id: Int?,
    @ColumnInfo(name = "username") val username: String?,
    @ColumnInfo(name = "first_name") var firstName: String?,
    @ColumnInfo(name = "last_name") var lastName: String?,
)

fun Empleado.toDatabase() = EmpleadoEntity(
    id = id,
    username = username,
    firstName = firstName,
    lastName = lastName,
)