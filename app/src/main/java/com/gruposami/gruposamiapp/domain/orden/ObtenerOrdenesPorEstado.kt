package com.gruposami.gruposamiapp.domain.orden

import com.gruposami.gruposamiapp.data.repositories.OrdenRepository
import com.gruposami.gruposamiapp.domain.orden.model.OrdenCompleta
import com.gruposami.gruposamiapp.domain.sesion.model.Sesion
import javax.inject.Inject

class ObtenerOrdenesPorEstado @Inject constructor(

    private val ordenRepository: OrdenRepository,
){

    suspend operator fun invoke(): List<OrdenCompleta> {
        // Juan 18-11-2023 He decidido filtrar así y no comerme el coco con una query.
        val listaRetorno = mutableListOf<OrdenCompleta>()
        val listadoCompleto = ordenRepository.obtenerOrdenesBD()

        for (ordenCompleta in listadoCompleto){
            for (servicioCompleto in ordenCompleta.servicioCompleto){
                if (servicioCompleto.estado.last()?.estado == Sesion.filtroEstado){
                    listaRetorno.add(ordenCompleta)
                }
            }
        }

        return listaRetorno
    }
}