package com.gruposami.gruposamiapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gruposami.gruposamiapp.data.database.entities.MultimediaEntity

@Dao
interface MultimediaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarMultimedia(multimedia: MultimediaEntity)

    @Query("SELECT * FROM multimedia_table " +
            "where servicio_id=:servicioId and tipo_archivo=:tipo and categoria_archivo =:categoria " +
            "and etiqueta_archivo=:etiqueta order by id_multimedia asc limit 1")
    suspend fun obtenerMultimediaFiltrado(
        servicioId: Int?,
        tipo: String?,
        categoria: String?,
        etiqueta: String?
    ): MultimediaEntity

    @Query("UPDATE multimedia_table SET id_multimedia = :nuevaId where id_multimedia = :anteriorId ")
    suspend fun modificarMultimediaId(anteriorId: Int?, nuevaId: Int?)

    @Query("SELECT * FROM multimedia_table where id_multimedia=:idMultimedia")
    suspend fun obtenerMultimediaPorId(idMultimedia: Int): MultimediaEntity

    @Query("DELETE FROM multimedia_table where id_multimedia =:id ")
    suspend fun eliminarMultimedia(id: Int)

}