package com.gruposami.gruposamiapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gruposami.gruposamiapp.domain.listadevalores.model.ListaDeValores

@Entity(tableName = "lista_de_valores_table")
data class ListaDeValoresEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id_ldv") val id: Int,
    @ColumnInfo(name = "entidad") var entidad: String?,
    @ColumnInfo(name = "categoria") var categoria: String?,
    @ColumnInfo(name = "descripcion") var descripcion: String?,
    @ColumnInfo(name = "numeracion") var numeracion: Int?,
    @ColumnInfo(name = "activo") var activo: Boolean?,
    @ColumnInfo(name = "fecha_creacion") var fechaCreacion: String?,
    @ColumnInfo(name = "fecha_modificacion") var fechaModificacion: String?,
)

fun ListaDeValores.toDatabase() = ListaDeValoresEntity(
    id = id,
    entidad = entidad,
    categoria = categoria,
    descripcion = descripcion,
    numeracion = numeracion,
    activo = activo,
    fechaCreacion = fechaCreacion,
    fechaModificacion = fechaModificacion,
)