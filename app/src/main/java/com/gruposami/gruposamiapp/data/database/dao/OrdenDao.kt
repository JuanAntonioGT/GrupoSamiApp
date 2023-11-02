package com.gruposami.gruposamiapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gruposami.gruposamiapp.data.database.entities.OrdenEntity

@Dao
interface OrdenDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarOrden(ordenes: OrdenEntity)

    @Query("DELETE FROM orden_table")
    suspend fun eliminarOrdenes()

}