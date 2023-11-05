package com.gruposami.gruposamiapp.data.network.multimedia.model

import com.google.gson.annotations.SerializedName

data class MultimediaResponse(
    @SerializedName("id") var id: Int,
    @SerializedName("fecha_creacion") var fechaCreacion: String?,
    @SerializedName("fecha_modificacion") var fechaModificacion: String?,
    @SerializedName("tipo_archivo") var tipoArchivo: String?,
    @SerializedName("categoria_archivo") var categoriaArchivo: String?,
    @SerializedName("etiqueta_archivo") var etiquetaArchivo: String?,
    @SerializedName("ruta") var ruta: String?,
    @SerializedName("servicio") var servicioId: Int?,
)
