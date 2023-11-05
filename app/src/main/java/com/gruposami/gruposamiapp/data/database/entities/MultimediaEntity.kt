package com.gruposami.gruposamiapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gruposami.gruposamiapp.domain.multimedia.model.Multimedia

@Entity(tableName = "multimedia_table")
data class MultimediaEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_multimedia") var id: Int?,
    @ColumnInfo(name = "ruta") var ruta: String?,
    @ColumnInfo(name = "tipo_archivo") var tipoArchivo: String?,
    @ColumnInfo(name = "categoria_archivo") var categoriaArchivo: String?,
    @ColumnInfo(name = "etiqueta_archivo") var etiquetaArchivo: String?,
    @ColumnInfo(name = "servicio_id") var servicioId: Int?,
    @ColumnInfo(name = "fecha_creacion") var fechaCreacion: String?,
    @ColumnInfo(name = "fecha_modificacion") var fechaModificacion: String?,
)

fun Multimedia.toDatabase() = MultimediaEntity(
    id = id,
    ruta = ruta,
    tipoArchivo = tipoArchivo,
    categoriaArchivo = categoriaArchivo,
    etiquetaArchivo = etiquetaArchivo,
    servicioId = servicioId,
    fechaCreacion = fechaCreacion,
    fechaModificacion = fechaModificacion,
)