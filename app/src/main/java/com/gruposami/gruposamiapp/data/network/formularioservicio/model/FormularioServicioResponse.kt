package com.gruposami.gruposamiapp.data.network.formularioservicio.model

import com.google.gson.annotations.SerializedName

data class FormularioServicioResponse(
    @SerializedName("id") var idFormularioServicio: Int,
    @SerializedName("fase") var fase: Int?,
    @SerializedName("titulo") var titulo: String?,

)

data class FormularioServicioRelacionesResponse(
    @SerializedName("id") var idRelaciones: Int,
    @SerializedName("fase_uno") var faseUno: Int?,
    @SerializedName("fase_dos") var faseDos: Int?,
    @SerializedName("fase_tres") var faseTres: Int?,
    @SerializedName("fase_cuatro") var faseCuatro: Int?,
    @SerializedName("fase_cinco") var faseCinco: Int?,
)
