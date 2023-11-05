package com.gruposami.gruposamiapp.data.network.orden.model

import com.google.gson.annotations.SerializedName
import javax.inject.Singleton

@Singleton
class EstadoResponse(
    @SerializedName("id") var id: Int,
    @SerializedName("estado") var estado: String,
    @SerializedName("creado_por") var creadoPor: Int, // Id del usuario (más facil)
    @SerializedName("fecha_creacion") var fechaCreacion: String,
    @SerializedName("fecha_modificacion") var fechaModificacion: String)