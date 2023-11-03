package com.gruposami.gruposamiapp.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun timestamp(): String? {
    // Diferencia el API LEVEL y luego devolver el formato que sea.
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    return LocalDateTime.now().format(formatter)

}

