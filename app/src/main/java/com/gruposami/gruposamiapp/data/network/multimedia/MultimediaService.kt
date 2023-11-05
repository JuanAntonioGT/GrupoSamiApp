package com.gruposami.gruposamiapp.data.network.multimedia

import com.gruposami.gruposamiapp.domain.multimedia.model.Multimedia
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.http.Part
import java.io.File
import java.lang.Exception
import javax.inject.Inject


class MultimediaService @Inject constructor(private val multimediaApiClient: MultimediaApiClient) {

    suspend fun enviarMultimedia(multimedia: Multimedia, nuevaId: Int): Boolean {
        var retorno: Boolean
        withContext(Dispatchers.IO) {
            println("ENVIANDO IMAGENES")
            retorno = try {
                val file = File(multimedia.ruta!!)
                val requestBody: RequestBody = file.asRequestBody("image/*".toMediaTypeOrNull())
                val body = MultipartBody.Part.createFormData("file", file.name, requestBody);

                multimediaApiClient.enviarMultimedia(
                    body,
                    nuevaId,
                    multimedia.tipoArchivo!!,
                    multimedia.categoriaArchivo!!,
                    multimedia.etiquetaArchivo!!,
                    multimedia.servicioId!!,
                    multimedia.fechaCreacion!!,
                    multimedia.fechaModificacion!!,
                )

                true
            } catch (e: Exception){
                println(e.message)
                println("NO TIENES PERMISOS DE LECTURA POR LA PUTA CARA")
                false
            }

        }
        return retorno
    }

}
