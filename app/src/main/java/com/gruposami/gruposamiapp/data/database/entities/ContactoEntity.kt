package com.gruposami.gruposamiapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gruposami.gruposamiapp.domain.contacto.model.Contacto

@Entity(tableName = "contacto_table")
data class ContactoEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id_contacto") var id: Int,
    @ColumnInfo(name = "contacto") var contacto: String?,
    @ColumnInfo(name = "tipo_contacto") var tipoContacto: String?,
    @ColumnInfo(name = "nombre") var nombre: String?,
    @ColumnInfo(name = "principal") var principal: Boolean?,
    @ColumnInfo(name = "activo") var activo: Boolean?,
    @ColumnInfo(name = "cliente_id") var clienteId: Int?,
    @ColumnInfo(name = "fecha_creacion") var fechaCreacion: String?,
    @ColumnInfo(name = "fecha_modificacion") var fechaModificacion: String?,
)

fun Contacto.toDatabase() = ContactoEntity(
    id = id,
    contacto = contacto,
    tipoContacto = tipoContacto,
    nombre = nombre,
    principal = principal,
    activo = activo,
    clienteId = clienteId,
    fechaCreacion = fechaCreacion,
    fechaModificacion = fechaModificacion,
)