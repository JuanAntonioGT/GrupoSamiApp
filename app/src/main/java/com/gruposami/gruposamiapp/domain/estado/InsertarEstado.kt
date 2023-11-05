package com.gruposami.gruposamiapp.domain.estado


import com.gruposami.gruposamiapp.data.repositories.EstadoRepository
import com.gruposami.gruposamiapp.domain.estado.model.Estado
import javax.inject.Inject

class InsertarEstado @Inject constructor(
    private val estadoRepository: EstadoRepository,
) {
    suspend operator fun invoke(estado: Estado) {
        estadoRepository.insertarEstado(estado)
    }

}