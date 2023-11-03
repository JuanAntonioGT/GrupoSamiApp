package com.gruposami.gruposamiapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gruposami.gruposamiapp.data.database.entities.ContactoEntity

@Dao
interface ContactoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarContacto(contacto: ContactoEntity)

    @Query("DELETE FROM contacto_table where id_contacto =:id ")
    suspend fun eliminarContacto(id: Int?)

    @Query("UPDATE contacto_table SET id_contacto = :nuevaId where id_contacto = :anteriorId ")
    suspend fun modificarContactoId(anteriorId: Int?, nuevaId: Int?)

    @Query("SELECT * FROM contacto_table where cliente_id =:clienteId ")
    suspend fun obtenerListaContactos(clienteId: Int): List<ContactoEntity>

}