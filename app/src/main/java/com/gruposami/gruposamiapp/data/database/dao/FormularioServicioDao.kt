package com.gruposami.gruposamiapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gruposami.gruposamiapp.data.database.entities.FormularioServicioEntity
import com.gruposami.gruposamiapp.data.database.entities.FormularioServicioRelacionesEntity

@Dao
interface FormularioServicioDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarFormularioServicio(formularioServicioEntity: FormularioServicioEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarFormularioServicioRelacion(formularioServicioRelacionesEntity: FormularioServicioRelacionesEntity)

    @Query("SELECT * FROM formulario_servicio_table")
    suspend fun obtenerFormularioServicio(): List<FormularioServicioEntity>

    @Query("SELECT * FROM formulario_servicio_table where  fase = 1")
    suspend fun obtenerFormularioServicioFaseUno(): List<FormularioServicioEntity>

    @Query("SELECT fs.* FROM formulario_servicio_table fs " +
            "inner join formulario_servicio_relaciones_table fsr on fs.idFormularioServicio = fsr.faseDos " +
            "inner join formulario_servicio_table fs2 on fsr.faseUno = fs2.idFormularioServicio " +
            "where fs2.titulo = :descripcionUno")
    suspend fun obtenerFormularioServicioFaseDos(
        descripcionUno: String?
    ): List<FormularioServicioEntity>

    @Query("SELECT fs.* FROM formulario_servicio_table fs " +
            "inner join formulario_servicio_relaciones_table fsr on fs.idFormularioServicio = fsr.faseTres " +
            "inner join formulario_servicio_table fs2 on fsr.faseUno = fs2.idFormularioServicio " +
            "inner join formulario_servicio_table fs3 on fsr.faseDos = fs3.idFormularioServicio " +
            "where fs2.titulo = :descripcionUno and fs3.titulo = :descripcionDos")
    suspend fun obtenerFormularioServicioFaseTres(
        descripcionUno: String?,
        descripcionDos: String?):
            List<FormularioServicioEntity>

    @Query("SELECT fs.* FROM formulario_servicio_table fs " +
            "inner join formulario_servicio_relaciones_table fsr on fs.idFormularioServicio = fsr.faseCuatro " +
            "inner join formulario_servicio_table fs2 on fsr.faseUno = fs2.idFormularioServicio " +
            "inner join formulario_servicio_table fs3 on fsr.faseDos = fs3.idFormularioServicio " +
            "inner join formulario_servicio_table fs4 on fsr.faseTres = fs4.idFormularioServicio " +
            "where fs2.titulo = :descripcionUno and fs3.titulo = :descripcionDos and fs4.titulo = :descripcionTres")
    suspend fun obtenerFormularioServicioFaseCuatro(
        descripcionUno: String?,
        descripcionDos: String?,
        descripcionTres: String?
    ): List<FormularioServicioEntity>

    @Query("SELECT fs.* FROM formulario_servicio_table fs " +
            "inner join formulario_servicio_relaciones_table fsr on fs.idFormularioServicio = fsr.faseCinco " +
            "inner join formulario_servicio_table fs2 on fsr.faseUno = fs2.idFormularioServicio " +
            "inner join formulario_servicio_table fs3 on fsr.faseDos = fs3.idFormularioServicio " +
            "inner join formulario_servicio_table fs4 on fsr.faseTres = fs4.idFormularioServicio " +
            "inner join formulario_servicio_table fs5 on fsr.faseCuatro = fs5.idFormularioServicio " +
            "where fs2.titulo = :descripcionUno and fs3.titulo = :descripcionDos and fs4.titulo = :descripcionTres and fs5.titulo = :descripcionCuatro")
    suspend fun obtenerFormularioServicioFaseCinco(
        descripcionUno: String?,
        descripcionDos: String?,
        descripcionTres: String?,
        descripcionCuatro: String?
    ): List<FormularioServicioEntity>
}