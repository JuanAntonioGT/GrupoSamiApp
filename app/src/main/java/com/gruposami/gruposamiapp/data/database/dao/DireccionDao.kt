package com.gruposami.gruposamiapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gruposami.gruposamiapp.data.database.entities.ContactoEntity
import com.gruposami.gruposamiapp.data.database.entities.DireccionEntity

@Dao
interface DireccionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarDireccion(direccion: DireccionEntity)

    @Query("SELECT * FROM direccion_table where cliente_id =:clienteId ")
    suspend fun obtenerDireccionesPorCliente(clienteId: Int): List<DireccionEntity>

    @Query("UPDATE direccion_table SET id_direccion = :nuevaId where id_direccion = :anteriorId ")
    suspend fun modificarDireccionId(anteriorId: Int, nuevaId: Int)

    @Query("DELETE FROM direccion_table where id_direccion =:id ")
    suspend fun eliminarDireccion(id: Int)
}