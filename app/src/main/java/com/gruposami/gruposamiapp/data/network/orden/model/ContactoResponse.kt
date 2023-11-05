package com.gruposami.gruposamiapp.data.network.orden.model

import com.google.gson.annotations.SerializedName
import javax.inject.Singleton

@Singleton
class ContactoResponse(
    @SerializedName("id") var id: Int,
    @SerializedName("contacto") var contacto: String?,
    @SerializedName("tipo_contacto") var tipoContacto: String?,
    @SerializedName("nombre") var nombre: String?,
    @SerializedName("principal") var principal: Boolean?,
    @SerializedName("activo") var activo: Boolean?,
    @SerializedName("cliente") var clienteId: Int?,
    @SerializedName("fecha_creacion") var fechaCreacion: String?,
    @SerializedName("fecha_modificacion") var fechaModificacion: String?,
)