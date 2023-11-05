package com.gruposami.gruposamiapp.data.network.orden.model

import com.google.gson.annotations.SerializedName
import javax.inject.Singleton

@Singleton
class DireccionResponse(
    @SerializedName("id") var id: Int,
    @SerializedName("direccion") var direccion: String?,
    @SerializedName("poblacion") var poblacion: String?,
    @SerializedName("codigo_postal") var codigoPostal: String?,
    @SerializedName("actual") var actual: Boolean?,
    @SerializedName("cliente") var clienteId: Int?,
    @SerializedName("fecha_creacion") var fechaCreacion: String?,
    @SerializedName("fecha_modificacion") var fechaModificacion: String?,
)