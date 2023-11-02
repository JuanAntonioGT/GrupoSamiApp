package com.gruposami.gruposamiapp.domain.empleado.useCase

import com.gruposami.gruposamiapp.data.database.entities.toDatabase
import com.gruposami.gruposamiapp.data.repositories.EmpleadoRepository
import com.gruposami.gruposamiapp.domain.empleado.model.toDomain
import javax.inject.Inject

class ObtenerListaEmpleados @Inject constructor(
    private val repository: EmpleadoRepository
) {

    /* Caso de uso de traer la lista de empleado para luego reutilizarla en formularios.
    * Esta función llamará a la api para traer la lista de empleados y guardarlos */
    suspend operator fun invoke() {
        val listaEmpleado = repository.listaEmpleadosRepository()
        if (listaEmpleado.isNotEmpty()){
            for (empleado in listaEmpleado) {
                repository.insertarEmpleado(empleado.toDomain().toDatabase())
            }
        }
    }
}