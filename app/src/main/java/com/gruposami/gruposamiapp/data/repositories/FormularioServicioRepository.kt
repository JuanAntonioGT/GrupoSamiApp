package com.gruposami.gruposamiapp.data.repositories


import com.gruposami.gruposamiapp.data.database.dao.FormularioServicioDao
import com.gruposami.gruposamiapp.data.database.entities.FormularioServicioEntity
import com.gruposami.gruposamiapp.data.database.entities.FormularioServicioRelacionesEntity
import com.gruposami.gruposamiapp.data.network.formularioservicio.FormularioServicioManagement
import com.gruposami.gruposamiapp.data.network.formularioservicio.FormularioServicioRelacionesManagement
import com.gruposami.gruposamiapp.data.network.formularioservicio.FormularioServicioService
import com.gruposami.gruposamiapp.domain.login.useCase.RefrescarToken
import com.gruposami.gruposamiapp.domain.servicio.model.Servicio
import com.gruposami.gruposamiapp.ui.login.model.Comprobacion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class FormularioServicioRepository @Inject constructor(
    private val formularioServicioService: FormularioServicioService,
    private val formularioServicioDao: FormularioServicioDao,
    private val refrescarTokenUseCase: RefrescarToken,
) {

    suspend fun remoteFormularioServicio(): FormularioServicioManagement {
        return withContext(Dispatchers.IO) {
            var formulariosServicioManagement = formularioServicioService.getFormularioServicioService()
            if (formulariosServicioManagement.response != null) {
                if (formulariosServicioManagement.response!!.code() == 401) {
                    // Parece que el token de sesión a caducado.
                    // Comprobar de nuevo el token de sesión.
                    val refrescarToken: Comprobacion = refrescarTokenUseCase.invoke()
                    formulariosServicioManagement.comprobacion = refrescarToken.booleano
                    formulariosServicioManagement.mensaje = refrescarToken.mensaje!!
                    if (refrescarToken.booleano) {
                        formulariosServicioManagement = formularioServicioService.getFormularioServicioService()
                    }
                }
            }
            formulariosServicioManagement
        }

    }

    suspend fun remoteFormularioServicioRelaciones(): FormularioServicioRelacionesManagement {
        return withContext(Dispatchers.IO) {
            var formulariosServicioRelacionesManagement = formularioServicioService.getFormularioServicioRelacionesService()
            if (formulariosServicioRelacionesManagement.response != null) {
                if (formulariosServicioRelacionesManagement.response!!.code() == 401) {
                    // Parece que el token de sesión a caducado.
                    // Comprobar de nuevo el token de sesión.
                    val refrescarToken: Comprobacion = refrescarTokenUseCase.invoke()
                    formulariosServicioRelacionesManagement.comprobacion = refrescarToken.booleano
                    formulariosServicioRelacionesManagement.mensaje = refrescarToken.mensaje!!
                    if (refrescarToken.booleano) {
                        formulariosServicioRelacionesManagement = formularioServicioService.getFormularioServicioRelacionesService()
                    }
                }
            }
            formulariosServicioRelacionesManagement
        }

    }

    suspend fun insertarFormularioServicio(formularioServicioEntity: FormularioServicioEntity) {
        formularioServicioDao.insertarFormularioServicio(formularioServicioEntity)
    }

    suspend fun insertarFormularioServicioRelaciones(formularioServicioRelacionesEntity: FormularioServicioRelacionesEntity) {
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