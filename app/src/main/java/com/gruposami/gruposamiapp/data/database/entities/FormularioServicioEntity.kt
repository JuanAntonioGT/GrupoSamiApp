package com.gruposami.gruposamiapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gruposami.gruposamiapp.domain.formularioservicio.model.FormularioServicio
import com.gruposami.gruposamiapp.domain.formularioservicio.model.FormularioServicioRelaciones

@Entity(tableName = "formulario_servicio_table")
data class FormularioServicioEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "idFormularioServicio") val idFormularioServicio: Int,
    @ColumnInfo(name = "fase") var fase: Int?,
    @ColumnInfo(name = "titulo") var titulo: String?,
)

fun FormularioServicio.toDatabase() = FormularioServicioEntity(
    idFormularioServicio = idFormularioServicio,
    fase = fase,
    titulo = titulo,
)

@Entity(tableName = "formulario_servicio_relaciones_table")
data class FormularioServicioRelacionesEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "idRelaciones") val idFormularioServicio: Int,
    @ColumnInfo(name = "faseUno") var faseUno: Int?,
    @ColumnInfo(name = "faseDos") var faseDos: Int?,
    @ColumnInfo(name = "faseTres") var faseTres: Int?,
    @ColumnInfo(name = "faseCuatro") var faseCuatro: Int?,
    @ColumnInfo(name = "faseCinco") var faseCinco: Int?,
)

fun FormularioServicioRelaciones.toDatabase() = FormularioServicioRelacionesEntity(
    idFormularioServicio = idFormularioServicio,
    faseUno = faseUno,
    faseDos = faseDos,
    faseTres = faseTres,
    faseCuatro = faseCuatro,
    faseCinco = faseCinco
)

