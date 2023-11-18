package com.gruposami.gruposamiapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gruposami.gruposamiapp.data.database.entities.FirmaEntity

@Dao
interface FirmaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarFirma(firma: FirmaEntity)

    @Query("UPDATE firma_table SET id_firma = :nuevaId where id_firma = :anteriorId ")
    suspend fun modificarDireccionId(anteriorId: Int, nuevaId: Int)

//    @Query("SELECT * FROM firma_table where id_firma = :id_firma ")
//    suspend fun obtenerFirmaFiltrado(id_firma: Int): FirmaEntity

}