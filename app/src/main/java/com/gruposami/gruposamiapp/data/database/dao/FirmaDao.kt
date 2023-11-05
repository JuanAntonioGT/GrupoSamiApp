package com.gruposami.gruposamiapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.gruposami.gruposamiapp.data.database.entities.FirmaEntity

@Dao
interface FirmaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarFirma(firma: FirmaEntity)

//    @Query("SELECT * FROM firma_table where id_firma = :id_firma ")
//    suspend fun obtenerFirmaFiltrado(id_firma: Int): FirmaEntity

}