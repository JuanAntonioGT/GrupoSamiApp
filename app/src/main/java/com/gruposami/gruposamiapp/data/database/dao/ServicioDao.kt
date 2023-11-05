package com.gruposami.gruposamiapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gruposami.gruposamiapp.data.database.entities.ServicioCompletoEntity
import com.gruposami.gruposamiapp.data.database.entities.ServicioEntity
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

    @Query("SELECT * FROM servicio_table where orden_id =:orden_id ")
    suspend fun obtenerServiciosPorOrden(orden_id: Int): List<ServicioCompletoEntity>

    @Query("SELECT * FROM servicio_table where id_servicio =:id ")
    suspend fun obtenerServicio(id: Int): ServicioEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun modificarServicio(servicio: ServicioEntity)

    @Query("UPDATE servicio_table SET id_servicio = :nuevaId where id_servicio = :anteriorId ")
    suspend fun modificarServicioId(anteriorId: Int?, nuevaId: Int?)

//    @Query("DELETE FROM servicio_table where id_servicio =:id")
//    suspend fun eliminarServicio(id: Int?)
//
//    @Query("DELETE FROM servicio_table")
//    suspend fun eliminarServicios()

}