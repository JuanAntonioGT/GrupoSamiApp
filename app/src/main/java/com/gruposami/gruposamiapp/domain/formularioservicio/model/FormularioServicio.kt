package com.gruposami.gruposamiapp.domain.formularioservicio.model

import com.gruposami.gruposamiapp.data.database.entities.FormularioServicioEntity
import com.gruposami.gruposamiapp.data.database.entities.FormularioServicioRelacionesEntity
import com.gruposami.gruposamiapp.data.network.formularioservicio.model.FormularioServicioRelacionesResponse
import com.gruposami.gruposamiapp.data.network.formularioservicio.model.FormularioServicioResponse


data class FormularioServicio(
    var idFormularioServicio: Int,
    var fase: Int?,
    var titulo: String?,
)

fun FormularioServicioEntity.toDomain() = FormularioServicio(
    idFormularioServicio, fase, titulo
)

fun FormularioServicioResponse.toDomain() = FormularioServicio(
    idFormularioServicio, fase, titulo
)

data class FormularioServicioRelaciones(
    val idRelaciones: Int,
    val faseUno: Int?,
    val faseDos: Int?,
    val faseTres: Int?,
    val faseCuatro: Int?,
    val faseCinco: Int?
)

fun FormularioServicioRelacionesEntity.toDomain() = FormularioServicioRelaciones(
    idRelaciones, faseUno, faseDos, faseTres, faseCuatro, faseCinco,
)

fun FormularioServicioRelacionesResponse.toDomain() = FormularioServicioRelaciones(
    idRelaciones, faseUno, faseDos, faseTres, faseCuatro, faseCinco,
)
