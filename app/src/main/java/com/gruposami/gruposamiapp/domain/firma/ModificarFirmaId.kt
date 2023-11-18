package com.gruposami.gruposamiapp.domain.firma

import com.gruposami.gruposamiapp.data.repositories.FirmaRepository
import com.gruposami.gruposamiapp.domain.orden.model.CambioId
import javax.inject.Inject

class ModificarFirmaId @Inject constructor(
    private val firmaRepository: FirmaRepository,
) {
    suspend operator fun invoke(firmas: List<CambioId?>) {
        firmas.map {
            if (it != null) {
                firmaRepository.modificarFirmaId(it)
            }
        }
    }
}