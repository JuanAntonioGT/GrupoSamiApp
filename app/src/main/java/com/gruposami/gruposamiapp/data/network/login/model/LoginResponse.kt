package com.gruposami.gruposamiapp.data.network.login.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("token") var token: String?, // Este puede o no estar
    @SerializedName("user_id") var userId: Int?, // Este puede o no estar
    @SerializedName("succes") var succes: Boolean,
    @SerializedName("message") var message: String,
)