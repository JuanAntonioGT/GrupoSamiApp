package com.gruposami.gruposamiapp.data.network.orden.model

import com.google.gson.annotations.SerializedName
import javax.inject.Singleton

@Singleton
class OrdenResponse(
    @SerializedName("id") var id: Int,
    @SerializedName("cliente") var clienteResponse: ClienteResponse?,
//    @SerializedName("servicio_set") var servicio: List<ServicioResponse?>,
//    @SerializedName("estado") var estado: EstadoResponse?,
    @SerializedName("codigo_referencia") var codigoReferencia: String?,
    @SerializedName("numero_referencia") var numeroReferencia: String?,
    @SerializedName("comentario") var comentario: String?,
    @SerializedName("finalizado") var finalizado: Boolean?,
    @SerializedName("fecha_creacion") var fechaCreacion: String?,
    @SerializedName("fecha_modificacion") var fechaModificacion: String?,
    )