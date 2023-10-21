package com.gruposami.gruposamiapp.network.model

import com.gruposami.gruposamiapp.data.models.LoginResponse
import retrofit2.Response

data class LoginManagement(
    val comprobacion: Boolean,
    val mensaje: String,
    val response: Response<LoginResponse>?
)