package com.gruposami.gruposamiapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gruposami.gruposamiapp.data.database.entities.ListaDeValoresEntity

@Dao
interface ListaDeValoresDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarListaDeValores(listaDeValoresEntity: List<ListaDeValoresEntity>)

    @Query("SELECT * FROM lista_de_valores_table where entidad = 'producto' and categoria = 'activos' and activo = 1 ")
    suspend fun obtenerListaVidrios(): List<ListaDeValoresEntity>

    @Query("SELECT * FROM lista_de_valores_table where entidad = 'producto' and categoria = 'descatalogados' and activo = 1")
    suspend fun obtenerListaVidriosDescatalogados(): List<ListaDeValoresEntity>

    @Query("DELETE FROM lista_de_valores_table")
    suspend fun eliminarListaDeValores()

}
