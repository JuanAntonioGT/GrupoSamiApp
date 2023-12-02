package com.gruposami.gruposamiapp.domain.orden

import com.gruposami.gruposamiapp.data.repositories.OrdenRepository
import com.gruposami.gruposamiapp.domain.orden.model.Orden
import com.gruposami.gruposamiapp.domain.orden.model.OrdenCompleta
import com.gruposami.gruposamiapp.domain.sesion.model.Sesion
import javax.inject.Inject

class ObtenerOrdenPorId @Inject constructor(
    private val ordenRepository: OrdenRepository,
){
    suspend operator fun invoke(ordenId: Int): Orden {
        return ordenRepository.obtenerOrdenPorId(ordenId)
        // Juan 18-11-2023 He decidido filtrar así y no comerme el coco con una query.

    }
}