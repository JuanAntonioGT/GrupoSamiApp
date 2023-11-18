package com.gruposami.gruposamiapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.gruposami.gruposamiapp.data.database.entities.OrdenCompletaEntity
import com.gruposami.gruposamiapp.data.database.entities.OrdenEntity
import com.gruposami.gruposamiapp.data.database.entities.OrdenEstadoEntity

@Dao
interface OrdenDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarOrden(ordenes: OrdenEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarOrdenEstado(ordenEstadoEntity: OrdenEstadoEntity)

    @Transaction
    @Query("SELECT * FROM orden_table")
    suspend fun obtenerOrdenesCompletas(): List<OrdenCompletaEntity>

    @Query("SELECT * FROM orden_table where id_orden=:ordenId ")
    suspend fun obtenerOrdenPorId(ordenId: Int): OrdenEntity

    // todo Revisar esta query para que te devuelva el último estado agregado.
    @Transaction
    @Query(
        "SELECT * FROM orden_table o " +
                "join orden_estado_table oe on o.id_orden = oe.orden_id " +
                "join estado_table e on e.id_estado = oe.estado_id " +
                "where e.estado=:estado"
    )
    suspend fun obtenerOrdenPorEstado(estado: String): List<OrdenCompletaEntity>

    @Query("UPDATE orden_table SET fecha_modificacion = :timestamp WHERE id_orden = :ordenId")
    suspend fun modificarFechaOrden(ordenId: Int?, timestamp: String?)

    @Query("DELETE FROM orden_table where id_orden =:id ")
    suspend fun eliminarOrden(id: Int)

    @Query("DELETE FROM orden_table")
    suspend fun eliminarOrdenes()

}