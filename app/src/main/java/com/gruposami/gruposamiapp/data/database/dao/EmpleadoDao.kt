package com.gruposami.gruposamiapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gruposami.gruposamiapp.data.database.entities.EmpleadoEntity

@Dao
interface EmpleadoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarEmpleado(empleadoEntity: EmpleadoEntity)

    @Query("SELECT * FROM empleado_table e " +
            "join sesion_table s on s.usuario_id = e.id_empleado")
    suspend fun obtenerEmpleadoSesion(): EmpleadoEntity

}