package com.gruposami.gruposamiapp.domain.listadevalores

import com.gruposami.gruposamiapp.data.repositories.ListaDeValoresRepository
import com.gruposami.gruposamiapp.domain.listadevalores.model.ListaDeValores
import javax.inject.Inject

class ObtenerListaVidrios @Inject constructor(
    private val listaDeValoresRepository: ListaDeValoresRepository,
) {
    suspend operator fun invoke(descatalogado: Boolean): List<ListaDeValores> {
        val listaDeValores: List<ListaDeValores>

        if (descatalogado){
            listaDeValores = listaDeValoresRepository.obtenerListaVidriosDescatalogados()
        } else {
            listaDeValores = listaDeValoresRepository.obtenerListaVidrios()
        }
        return listaDeValores
    }

}