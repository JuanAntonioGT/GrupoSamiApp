package com.gruposami.gruposamiapp.data.network.empleado.model

import com.google.gson.annotations.SerializedName

data class EmpleadoResponse(
    @SerializedName("id") var id: Int?,
    @SerializedName("username") var username: String?,
    @SerializedName("first_name") var firstName: String?,
    @SerializedName("last_name") var lastName: String?,
    @SerializedName("avatar") var avatar: String?,
)
