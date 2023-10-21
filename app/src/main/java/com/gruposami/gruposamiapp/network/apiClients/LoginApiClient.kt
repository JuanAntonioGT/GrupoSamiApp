package com.gruposami.gruposamiapp.network.apiClients

import com.gruposami.gruposamiapp.data.models.LoginResponse
import com.gruposami.gruposamiapp.network.model.LoginRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApiClient {
    @POST("auth/login")
    suspend fun getLoginResponse(@Body loginRequest: LoginRequest): Response<LoginResponse>
}