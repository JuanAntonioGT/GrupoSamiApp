package com.gruposami.gruposamiapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gruposami.gruposamiapp.domain.direccion.model.Direccion

@Entity(tableName = "direccion_table")
data class DireccionEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id_direccion") var id: Int,
    @ColumnInfo(name = "direccion") var direccion: String?,
    @ColumnInfo(name = "poblacion") var poblacion: String?,
    @ColumnInfo(name = "codigo_postal") var codigoPostal: String?,
    @ColumnInfo(name = "actual") var actual: Boolean?,
    @ColumnInfo(name = "cliente_id") var clienteId: Int?,
    @ColumnInfo(name = "fecha_creacion") var fechaCreacion: String?,
    @ColumnInfo(name = "fecha_modificacion") var fechaModificacion: String?,
    )

fun Direccion.toDatabase() = DireccionEntity(
    id = id,
    direccion = direccion,
    poblacion = poblacion,
    codigoPostal = codigoPostal,
    actual = actual,
    clienteId= clienteId,
    fechaCreacion = fechaCreacion,
    fechaModificacion = fechaModificacion,
)