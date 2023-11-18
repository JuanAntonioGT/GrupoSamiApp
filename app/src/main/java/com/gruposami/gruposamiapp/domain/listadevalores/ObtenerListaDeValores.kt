package com.gruposami.gruposamiapp.domain.listadevalores

import com.gruposami.gruposamiapp.data.database.entities.toDatabase
import com.gruposami.gruposamiapp.data.network.listadevalores.model.ListaDeValoresResponse
import com.gruposami.gruposamiapp.data.repositories.ListaDeValoresRepository
import com.gruposami.gruposamiapp.domain.listadevalores.model.toDomain
import com.gruposami.gruposamiapp.ui.login.model.Comprobacion
import javax.inject.Inject

class ObtenerListaDeValores @Inject constructor(
    private val listaDeValoresRepository: ListaDeValoresRepository,
) {
    suspend operator fun invoke(): Comprobacion {
        val listaDeValoresManagement = listaDeValoresRepository.listaDeValoresAPI()
        val comprobacion = Comprobacion(listaDeValoresManagement.comprobacion, listaDeValoresManagement.mensaje)

        if (listaDeValoresManagement.comprobacion && listaDeValoresManagement.response != null) {
            val listaDeValores: List<ListaDeValoresResponse>? = listaDeValoresManagement.response.body()
            if (listaDeValores != null && listaDeValores.isNotEmpty()) {
                listaDeValoresRepository.eliminarListaDeValores()
                listaDeValoresRepository.insertarListaDeValores(listaDeValores.map { it.toDomain().toDatabase() })
            }
        }
        return comprobacion

    }

}