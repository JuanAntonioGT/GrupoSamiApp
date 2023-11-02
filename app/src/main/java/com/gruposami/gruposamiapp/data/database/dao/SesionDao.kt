package com.gruposami.gruposamiapp.data.database.dao

import androidx.room.*
import com.gruposami.gruposamiapp.data.database.entities.SesionEntity

@Dao
interface SesionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarSesion(userEntity: SesionEntity)

    /* Esta query sirve para traernos las única entrada de la sesión */
    @Query("SELECT * FROM sesion_table where id_sesion = 0")
    suspend fun obtenerSesion(): SesionEntity?

    @Query("UPDATE sesion_table SET logueado = 0 where id_sesion = 0")
    suspend fun cerrarSesion()

    @Query("UPDATE sesion_table SET filtro_estado =:estado  where id_sesion = 0")
    suspend fun modificarEstado(estado: String)

    @Query("SELECT filtro_estado FROM sesion_table where id_sesion = 0")
    suspend fun obtenerEstado(): String

}
