package com.gruposami.gruposamiapp.data.repositories


import com.gruposami.gruposamiapp.data.database.dao.FirmaDao
import com.gruposami.gruposamiapp.data.database.entities.toDatabase
import com.gruposami.gruposamiapp.domain.firma.model.Firma
import javax.inject.Inject

class FirmaRepository @Inject constructor(
    private val firmaDao: FirmaDao
) {

    suspend fun insertarFirma(firma: Firma) {
        firmaDao.insertarFirma(firma.toDatabase())
    }
//
//    suspend fun obtenerFirma(id_firma: Int): Firma {
//        val response: FirmaEntity = firmaDao.obtenerFirmaFiltrado(id_firma)
//        return response.toDomain()
//    }

}