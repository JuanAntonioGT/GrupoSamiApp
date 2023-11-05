package com.gruposami.gruposamiapp.data.repositories


import com.gruposami.gruposamiapp.data.database.dao.FormularioServicioDao
import com.gruposami.gruposamiapp.data.database.entities.FormularioServicioEntity
import com.gruposami.gruposamiapp.data.database.entities.FormularioServicioRelacionesEntity
import com.gruposami.gruposamiapp.data.database.entities.toDatabase
import com.gruposami.gruposamiapp.data.network.formularioservicio.FormularioServicioService
import com.gruposami.gruposamiapp.domain.formularioservicio.model.FormularioServicio
import com.gruposami.gruposamiapp.domain.formularioservicio.model.FormularioServicioRelaciones
import com.gruposami.gruposamiapp.domain.servicio.model.Servicio
import javax.inject.Inject


class FormularioServicioRepository @Inject constructor(
    private val formularioServicioService: FormularioServicioService,
    private val formularioServicioDao: FormularioServicioDao,
) {

    suspend fun remoteFormularioServicio() {
        val formulariosServicio = formularioServicioService.getFormularioServicioService()
        val formulariosServicioRelaciones = formularioServicioService.getFormularioServicioRelacionesService()

        formulariosServicio.map {
            insertarFormularioServicio(
                FormularioServicio(
                    it.idFormularioServicio, it.fase, it.titulo
                ).toDatabase()
            )
        }
        formulariosServicioRelaciones.map {
            insertarFormularioServicio(
                FormularioServicioRelaciones(
                    it.idRelaciones, it.faseUno, it.faseDos, it.faseTres, it.faseCuatro, it.faseCinco
                ).toDatabase()
            )
        }

    }

    suspend fun insertarFormularioServicio(formularioServicioEntity: FormularioServicioEntity) {
        formularioServicioDao.insertarFormularioServicio(formularioServicioEntity)
    }

    suspend fun insertarFormularioServicio(formularioServicioRelacionesEntity: FormularioServicioRelacionesEntity) {
        formularioServicioDao.insertarFormularioServicioRelacion(formularioServicioRelacionesEntity)
    }

    suspend fun obtenerFormularioServicioFaseUno(): List<FormularioServicioEntity> {
        return formularioServicioDao.obtenerFormularioServicioFaseUno()
    }

    suspend fun obtenerFormularioServicioFaseDos(servicio: Servicio): List<FormularioServicioEntity> {
        return formularioServicioDao.obtenerFormularioServicioFaseDos(servicio.descripcionUno)
    }

    suspend fun obtenerFormularioServicioFaseTres(servicio: Servicio): List<FormularioServicioEntity> {
        return formularioServicioDao.obtenerFormularioServicioFaseTres(
            servicio.descripcionUno,
            servicio.descripcionDos,
        )
    }

    suspend fun obtenerFormularioServicioFaseCuatro(servicio: Servicio): List<FormularioServicioEntity> {
        return formularioServicioDao.obtenerFormularioServicioFaseCuatro(
            servicio.descripcionUno,
            servicio.descripcionDos,
            servicio.descripcionTres,
        )
    }

    suspend fun obtenerFormularioServicioFaseCinco(servicio: Servicio): List<FormularioServicioEntity> {
        return formularioServicioDao.obtenerFormularioServicioFaseCinco(
            servicio.descripcionUno,
            servicio.descripcionDos,
            servicio.descripcionTres,
            servicio.descripcionCuatro,
        )
    }


}