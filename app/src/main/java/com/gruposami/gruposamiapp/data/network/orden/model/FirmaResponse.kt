package com.gruposami.gruposamiapp.data.network.orden.model

import com.google.gson.annotations.SerializedName
import javax.inject.Singleton

@Singleton
class FirmaResponse (
    @SerializedName("id") var id: Int,
    @SerializedName("fecha_creacion") var fechaCreacion: String,
    @SerializedName("fecha_modificacion") var fechaModificacion: String,
    @SerializedName("tipo_firma") var tipoFirma: String,
    @SerializedName("conformidad") var conformidad: Boolean,
    @SerializedName("estetica") var estetica: Boolean,
    @SerializedName("indemnizacion") var indemnizacion: Boolean,
    @SerializedName("comentario") var comentario: String,
    @SerializedName("multimedia") var multimedia: Int,
    @SerializedName("servicio") var servicioId: Int?,
)