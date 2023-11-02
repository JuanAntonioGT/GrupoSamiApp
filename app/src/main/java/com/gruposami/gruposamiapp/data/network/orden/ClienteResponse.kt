package com.gruposami.gruposamiapp.data.network.orden

import com.google.gson.annotations.SerializedName

data class ClienteResponse(
    @SerializedName("id") var id: Int,
    @SerializedName("nombre") var nombre: String?,
    @SerializedName("apellidos") var apellidos: String?,
    @SerializedName("razon_social") var razonSocial: String?,
    @SerializedName("numero_identificacion") var numeroIdentificacion: String?,
//    @SerializedName("direccion_set") var direccion_set: List<DireccionResponse?>,
//    @SerializedName("contacto_set") var contacto_set: List<ContactoResponse?>,
    @SerializedName("fecha_creacion") var fechaCreacion: String?,
    @SerializedName("fecha_modificacion") var fechaModificacion: String?,
)
