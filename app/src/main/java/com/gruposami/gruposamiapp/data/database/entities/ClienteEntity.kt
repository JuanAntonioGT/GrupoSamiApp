package com.gruposami.gruposamiapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gruposami.gruposamiapp.domain.cliente.model.Cliente

@Entity(tableName = "cliente_table")
data class ClienteEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id_cliente") var id: Int,
    @ColumnInfo(name = "nombre") var nombre: String?,
    @ColumnInfo(name = "apellidos") var apellidos: String?,
    @ColumnInfo(name = "razon_social") var razonSocial: String?,
    @ColumnInfo(name = "numero_identificacion") var numeroIdentificacion: String?,
    @ColumnInfo(name = "fecha_creacion") var fechaCreacion: String?,
    @ColumnInfo(name = "fecha_modificacion") var fechaModificacion: String?,
)

fun Cliente.toDatabase() = ClienteEntity(
    id = id,
    nombre = nombre,
    apellidos = apellidos,
    razonSocial = razonSocial,
    numeroIdentificacion = numeroIdentificacion,
    fechaCreacion = fechaCreacion,
    fechaModificacion = fechaModificacion,
)
