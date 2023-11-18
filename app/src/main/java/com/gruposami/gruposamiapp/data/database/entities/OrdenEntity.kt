package com.gruposami.gruposamiapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.gruposami.gruposamiapp.domain.orden.model.Orden
import com.gruposami.gruposamiapp.domain.orden.model.OrdenEstado

@Entity(tableName = "orden_table")
data class OrdenEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id_orden") var id: Int?,
    @ColumnInfo(name = "cliente_id") var clienteId: Int?,
    @ColumnInfo(name = "codigo_referencia") var codigoReferencia: String?,
    @ColumnInfo(name = "numero_referencia") var numeroReferencia: String?,
    @ColumnInfo(name = "comentario") var comentario: String?,
    @ColumnInfo(name = "finalizado") var finalizado: Boolean?,
    @ColumnInfo(name = "fecha_creacion") var fechaCreacion: String,
    @ColumnInfo(name = "fecha_modificacion") var fechaModificacion: String,
)

fun Orden.toDatabase() = OrdenEntity(
    id = id,
    clienteId = clienteId,
    codigoReferencia = codigoReferencia,
    numeroReferencia = numeroReferencia,
    comentario = comentario,
    finalizado = finalizado,
    fechaCreacion = fechaCreacion,
    fechaModificacion = fechaModificacion,
)

@Entity(tableName = "orden_estado_table", primaryKeys = ["orden_id", "estado_id"], indices = [Index(value = ["estado_id"])])
data class OrdenEstadoEntity(
    @ColumnInfo(name = "orden_id") val ordenId: Int,
    @ColumnInfo(name = "estado_id") val estadoId: Int,
)

fun OrdenEstado.toDatabase() = OrdenEstadoEntity(
    ordenId = ordenId,
    estadoId = estadoId,
)
