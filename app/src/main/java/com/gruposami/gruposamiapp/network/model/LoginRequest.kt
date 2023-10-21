package com.gruposami.gruposamiapp.network.model


data class LoginRequest(
    val username: String,
    val password: String
) {
    fun esValido(): Comprobacion {
        if (username.isEmpty()) {
            return Comprobacion(false, "Debes escribir un usuario")
        } else if (password.isEmpty()) {
            return Comprobacion(false, "Debes escribir una contraseña")
        } else if (username.length > 20 || password.length > 20) {
            return Comprobacion(false, "Usuario o Contraseña es demasiado largo")
        }
        return Comprobacion(true, "Correcto")
    }
}

data class Comprobacion(
    var booleano: Boolean,
    var mensaje: String?,
)