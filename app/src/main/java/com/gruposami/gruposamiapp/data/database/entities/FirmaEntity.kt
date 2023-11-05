package com.gruposami.gruposamiapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gruposami.gruposamiapp.domain.firma.model.Firma

@Entity(tableName = "firma_table")
data class FirmaEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id_firma") var id: Int?,
    @ColumnInfo(name = "tipo_firma") var tipoFirma: String?,
    @ColumnInfo(name = "conformidad") var conformidad: Boolean?,
    @ColumnInfo(name = "estetica") var estetica: Boolean?,
    @ColumnInfo(name = "indemnizacion") var indemnizacion: Boolean?,
    @ColumnInfo(name = "comentario") var comentario: String?,
    @ColumnInfo(name = "multimedia") var multimedia: Int?,
    @ColumnInfo(name = "fecha_creacion") var fechaCreacion: String?,
    @ColumnInfo(name = "fecha_modificacion") var fechaModificacion: String?,
    @ColumnInfo(name = "servicio_id") var servicioId: Int?,
)

fun Firma.toDatabase() = FirmaEntity(
    id = id,
    tipoFirma = tipoFirma,
    conformidad = conformidad,
    estetica = estetica,
    indemnizacion = indemnizacion,
    comentario = comentario,
    multimedia = multimedia,
    fechaCreacion = fechaCreacion,
    fechaModificacion = fechaModificacion,
    servicioId = servicioId,
)
