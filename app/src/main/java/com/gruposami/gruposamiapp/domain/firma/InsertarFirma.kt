package com.gruposami.gruposamiapp.domain.firma

import com.gruposami.gruposamiapp.data.repositories.FirmaRepository
import com.gruposami.gruposamiapp.domain.firma.model.Firma

import javax.inject.Inject

class InsertarFirma @Inject constructor(
    private val firmaRepository: FirmaRepository
){
    suspend operator fun invoke(firma: Firma){
        firmaRepository.insertarFirma(firma)
    }
}