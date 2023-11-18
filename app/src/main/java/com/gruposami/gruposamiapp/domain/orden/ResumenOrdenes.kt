package com.gruposami.gruposamiapp.domain.orden

import com.gruposami.gruposamiapp.data.repositories.OrdenRepository
import com.gruposami.gruposamiapp.domain.orden.model.OrdenCompleta
import javax.inject.Inject

class ResumenOrdenes @Inject constructor(
    private val ordenRepository: OrdenRepository,
) {

    suspend operator fun invoke(): List<OrdenCompleta> {
        return ordenRepository.obtenerOrdenesBD()
    }
}