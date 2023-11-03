package com.gruposami.gruposamiapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.gruposami.gruposamiapp.data.database.entities.DireccionEntity

@Dao
interface DireccionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarDireccion(direccion: DireccionEntity)

//    @Query

}