package com.gruposami.gruposamiapp.domain.formularioservicio


import com.gruposami.gruposamiapp.data.database.entities.toDatabase
import com.gruposami.gruposamiapp.data.network.formularioservicio.model.FormularioServicioRelacionesResponse
import com.gruposami.gruposamiapp.data.network.formularioservicio.model.FormularioServicioResponse
import com.gruposami.gruposamiapp.data.repositories.FormularioServicioRepository
import com.gruposami.gruposamiapp.domain.formularioservicio.model.FormularioServicio
import com.gruposami.gruposamiapp.domain.formularioservicio.model.FormularioServicioRelaciones
import com.gruposami.gruposamiapp.domain.formularioservicio.model.toDomain
import com.gruposami.gruposamiapp.ui.login.model.Comprobacion
import javax.inject.Inject

class ObtenerFormularioServicio @Inject constructor(
    private val formularioServicioRepository: FormularioServicioRepository,

    ) {
    suspend operator fun invoke(): Comprobacion {
        val formularioServicioManagement = formularioServicioRepository.remoteFormularioServicio()
        val comprobacion = Comprobacion(formularioServicioManagement.comprobacion, formularioServicioManagement.mensaje)

        if (formularioServicioManagement.comprobacion && formularioServicioManagement.response != null) {
            val listaFormularioServicioResponse: List<FormularioServicioResponse>? =
                formularioServicioManagement.response.body()
            if (listaFormularioServicioResponse != null) {
                val formulariosServicio = listaFormularioServicioResponse.map { it.toDomain() }
                formulariosServicio.map {
                    formularioServicioRepository.insertarFormularioServicio(
                        FormularioServicio(
                            it.idFormularioServicio, it.fase, it.titulo
                        ).toDatabase()
                    )
                }
            }
        }

        val formularioServicioRelacionesManagement = formularioServicioRepository.remoteFormularioServicioRelaciones()

        if (formularioServicioRelacionesManagement.comprobacion && formularioServicioRelacionesManagement.response != null) {
            val listaFormularioServicioRelacionesResponse: List<FormularioServicioRelacionesResponse>? =
                formularioServicioRelacionesManagement.response.body()
            if (listaFormularioServicioRelacionesResponse != null) {
                val formulariosServicioRelaciones = listaFormularioServicioRelacionesResponse.map { it.toDomain() }
                formulariosServicioRelaciones.map {
                    formularioServicioRepository.insertarFormularioServicioRelaciones(
                        FormularioServicioRelaciones(
                            it.idRelaciones, it.faseUno, it.faseDos, it.faseTres, it.faseCuatro, it.faseCinco
                        ).toDatabase()
                    )
                }
            }
        }
        return comprobacion
    }

}