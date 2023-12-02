package com.gruposami.gruposamiapp.data.repositories


import com.gruposami.gruposamiapp.data.database.dao.FirmaDao
import com.gruposami.gruposamiapp.data.database.entities.toDatabase
import com.gruposami.gruposamiapp.domain.firma.model.Firma
import com.gruposami.gruposamiapp.domain.orden.model.CambioId
import javax.inject.Inject

class FirmaRepository @Inject constructor(
    private val firmaDao: FirmaDao
) {

    suspend fun insertarFirma(firma: Firma) {
        firmaDao.insertarFirma(firma.toDatabase())
    }

    suspend fun modificarFirmaId(cambio: CambioId) {
        firmaDao.modificarDireccionId(cambio.anteriorId, cambio.nuevaId)
    }

    suspend fun eliminarFirma(firma: Firma) {
        firmaDao.eliminarFirma(firma.id!!)
    }

//    suspend fun obtenerFirma(id_firma: Int): Firma {
//        val response: FirmaEntity = firmaDao.obtenerFirmaFiltrado(id_firma)
//        return response.toDomain()
//    }

}