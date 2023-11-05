package com.gruposami.gruposamiapp.data.network.listadevalores.model

import com.google.gson.annotations.SerializedName

data class ListaDeValoresResponse(
    @SerializedName("id") var id: Int,
    @SerializedName("entidad") var entidad: String?,
    @SerializedName("categoria") var categoria: String?,
    @SerializedName("descripcion") var descripcion: String?,
    @SerializedName("numeracion") var numeracion: Int?,
    @SerializedName("activo") var activo: Boolean?,
    @SerializedName("fecha_creacion") var fechaCreacion: String?,
    @SerializedName("fecha_modificacion") var fechaModificacion: String?,
)