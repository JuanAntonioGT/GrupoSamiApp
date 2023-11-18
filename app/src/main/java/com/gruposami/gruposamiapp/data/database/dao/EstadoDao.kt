package com.gruposami.gruposamiapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gruposami.gruposamiapp.data.database.entities.EstadoEntity

@Dao
interface EstadoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarEstado(estados: EstadoEntity)

    @Query("UPDATE estado_table SET id_estado = :nuevaId where id_estado = :anteriorId ")
    suspend fun modificarEstadoId(anteriorId: Int?, nuevaId: Int?)

    @Query("DELETE FROM estado_table where id_estado =:id ")
    suspend fun eliminarEstado(id: Int)

    @Query("DELETE FROM orden_estado_table where estado_id =:id ")
    suspend fun eliminarEstadoOrden(id: Int)

    @Query("DELETE FROM servicio_estado_table where estado_id =:id ")
    suspend fun eliminarEstadoServicio(id: Int)

}