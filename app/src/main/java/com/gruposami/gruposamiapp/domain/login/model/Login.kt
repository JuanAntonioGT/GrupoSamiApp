package com.gruposami.gruposamiapp.domain.login.model

import com.gruposami.gruposamiapp.data.models.LoginResponse

data class Login(
    var token: String?,
    var userId: Int?,
    var message: String,
    var succes: Boolean,
    )

fun LoginResponse.toDomain() = Login(
    token = token,
    userId = userId,
    message = message,
    succes = succes
)