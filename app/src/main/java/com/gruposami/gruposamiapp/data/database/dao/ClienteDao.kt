package com.gruposami.gruposamiapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gruposami.gruposamiapp.data.database.entities.ClienteEntity

@Dao
interface ClienteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarCliente(cliente: ClienteEntity)

    @Query("DELETE FROM cliente_table where id_cliente =:id ")
    suspend fun eliminarCliente(id: Int)


}