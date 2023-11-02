package com.gruposami.gruposamiapp.domain.listadevalores

import com.gruposami.gruposamiapp.data.database.entities.toDatabase
import com.gruposami.gruposamiapp.data.repositories.ListaDeValoresRepository
import javax.inject.Inject

class ObtenerListaDeValores @Inject constructor(
    private val listaDeValoresRepository: ListaDeValoresRepository,
) {
    suspend operator fun invoke() {
        val listaDeValores = listaDeValoresRepository.listaDeValoresAPI()
        if (listaDeValores.isNotEmpty()) {
            listaDeValoresRepository.eliminarListaDeValores()
            listaDeValoresRepository.insertarListaDeValores(listaDeValores.map { it.toDatabase() })
        }
    }

}