package com.gruposami.gruposamiapp.data.network.multimedia

import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface MultimediaApiClient {

    @Multipart
    @POST("multimedia")
    suspend fun enviarMultimedia(
        @Part file: MultipartBody.Part,
        @Part("id_nuevo") id: Int,
        @Part("tipo_archivo") tipoArchivo: String,
        @Part("categoria_archivo") categoriaArchivo: String,
        @Part("etiqueta_archivo") etiquetaArchivo: String,
        @Part("servicio_id") servicioId: Int,
        @Part("fecha_creacion") fechaCreacion: String,
        @Part("fecha_modificacion") fechaModificacion: String,
    )
}
