package com.gruposami.gruposamiapp.data.network.login

import com.gruposami.gruposamiapp.data.network.login.model.LoginResponse
import com.gruposami.gruposamiapp.ui.login.model.LoginRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApiClient {
    @POST("auth/login")
    suspend fun getLoginResponse(@Body loginRequest: LoginRequest): Response<LoginResponse>
}