package com.gruposami.gruposamiapp.domain.empleado.useCase

import com.gruposami.gruposamiapp.data.repositories.EmpleadoRepository
import com.gruposami.gruposamiapp.domain.empleado.model.Empleado
import com.gruposami.gruposamiapp.domain.empleado.model.toDomain
import javax.inject.Inject

class ObtenerEmpleado @Inject constructor(
    private val repository: EmpleadoRepository
) {

    /* Caso de uso de llamar a la bbdd para traer los detalles del empleado en la sesion */
    suspend operator fun invoke(): Empleado? {
        val empleado = repository.obtenerEmpleadoSesion()
        return if (empleado != null) {
            empleado.toDomain()
        } else {
            null
        }
    }
}