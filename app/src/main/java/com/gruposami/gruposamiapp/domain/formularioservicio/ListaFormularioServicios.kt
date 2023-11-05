package com.gruposami.gruposamiapp.domain.formularioservicio


import com.gruposami.gruposamiapp.data.repositories.FormularioServicioRepository
import com.gruposami.gruposamiapp.domain.formularioservicio.model.FormularioServicio
import com.gruposami.gruposamiapp.domain.formularioservicio.model.toDomain
import com.gruposami.gruposamiapp.domain.servicio.model.Servicio
import javax.inject.Inject

class ListaFormularioServicios @Inject constructor(
    private val formularioServicioRepository: FormularioServicioRepository,
) {
    suspend operator fun invoke(servicio: Servicio): List<FormularioServicio> {
        var lista = emptyList<FormularioServicio>()

        when (servicio.faseFormulario) {
            1 -> {
                lista = formularioServicioRepository.obtenerFormularioServicioFaseUno().map { it.toDomain() }
            }
            2 -> {
                lista = formularioServicioRepository.obtenerFormularioServicioFaseDos(servicio).map { it.toDomain() }
            }
            3 -> {
                lista =  formularioServicioRepository.obtenerFormularioServicioFaseTres(servicio).map { it.toDomain() }
            }
            4 -> {
                lista =  formularioServicioRepository.obtenerFormularioServicioFaseCuatro(servicio).map { it.toDomain() }
            }
            5 -> {
                lista =  formularioServicioRepository.obtenerFormularioServicioFaseCinco(servicio).map { it.toDomain() }
            }
        }

        lista = lista.distinct()
        return lista
    }

}