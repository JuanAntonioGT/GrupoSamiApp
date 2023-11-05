package com.gruposami.gruposamiapp.domain.formularioservicio.model

import com.gruposami.gruposamiapp.data.database.entities.FormularioServicioEntity


data class FormularioServicio(
    var idFormularioServicio: Int,
    var fase: Int?,
    var titulo: String?,
)

fun FormularioServicioEntity.toDomain() = FormularioServicio(
    idFormularioServicio, fase, titulo
)

data class FormularioServicioRelaciones(
    val idFormularioServicio: Int,
    val faseUno: Int?,
    val faseDos: Int?,
    val faseTres: Int?,
    val faseCuatro: Int?,
    val faseCinco: Int?
)

