package com.gruposami.gruposamiapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.gruposami.gruposamiapp.data.database.entities.ServicioCompletoEntity
import com.gruposami.gruposamiapp.data.database.entities.ServicioEntity
import com.gruposami.gruposamiapp.data.database.entities.ServicioEstadoEntity
import com.gruposami.gruposamiapp.data.database.entities.ServicioMedidoEntity
import com.gruposami.gruposamiapp.data.database.entities.ServicioMontadoEntity
import com.gruposami.gruposamiapp.data.database.entities.ServicioPendienteEntity

@Dao
interface ServicioDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarServicio(servicio: ServicioEntity)

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertarServicioEstado(servicioEstado: ServicioEstadoEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarServicioMedido(servicioMedido: ServicioMedidoEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarServicioMontado(servicioMontado: ServicioMontadoEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarServicioPendiente(servicioPendiente: ServicioPendienteEntity)

    @Transaction
    @Query("SELECT * FROM servicio_table where orden_id =:ordenId ")
    suspend fun obtenerServiciosPorOrden(ordenId: Int): List<ServicioCompletoEntity>

    @Query("SELECT * FROM servicio_table where id_servicio =:id ")
    suspend fun obtenerServicio(id: Int): ServicioEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun modificarServicio(servicio: ServicioEntity)

    @Query("UPDATE servicio_table SET id_servicio = :nuevaId where id_servicio = :anteriorId ")
    suspend fun modificarServicioId(anteriorId: Int?, nuevaId: Int?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarServicioEstado(servicioEstadoEntity: ServicioEstadoEntity)

    @Query("DELETE FROM servicio_table where id_servicio =:id")
    suspend fun eliminarServicio(id: Int?)

}