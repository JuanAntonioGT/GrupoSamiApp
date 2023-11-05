package com.gruposami.gruposamiapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gruposami.gruposamiapp.domain.estado.model.Estado


@Entity(tableName = "estado_table")
data class EstadoEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id_estado") var id: Int,
    @ColumnInfo(name = "estado") var estado: String?,
    @ColumnInfo(name = "creado_por") var creadoPor: Int?,
    @ColumnInfo(name = "fecha_creacion") var fechaCreacion: String?,
    @ColumnInfo(name = "fecha_modificacion") var fechaModificacion: String?,
)


fun Estado.toDatabase() = EstadoEntity(
    id = id,
    estado = estado,
    creadoPor = creadoPor,
    fechaCreacion = fechaCreacion,
    fechaModificacion = fechaModificacion,
)
