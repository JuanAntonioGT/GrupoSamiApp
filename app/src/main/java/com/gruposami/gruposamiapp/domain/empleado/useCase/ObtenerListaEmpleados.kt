package com.gruposami.gruposamiapp.domain.empleado.useCase

import com.gruposami.gruposamiapp.data.database.entities.toDatabase
import com.gruposami.gruposamiapp.data.network.empleado.model.EmpleadoResponse
import com.gruposami.gruposamiapp.data.repositories.EmpleadoRepository
import com.gruposami.gruposamiapp.domain.empleado.model.toDomain
import com.gruposami.gruposamiapp.ui.login.model.Comprobacion
import javax.inject.Inject

class ObtenerListaEmpleados @Inject constructor(
    private val repository: EmpleadoRepository
) {

    /* Caso de uso de traer la lista de empleado para luego reutilizarla en formularios.
    * Esta función llamará a la api para traer la lista de empleados y guardarlos */
    suspend operator fun invoke(): Comprobacion {
        val empleadoManagement = repository.listaEmpleadosRepository()
        val comprobacion = Comprobacion(empleadoManagement.comprobacion, empleadoManagement.mensaje)

        if (empleadoManagement.comprobacion && empleadoManagement.response != null) {
            val listadoEmpleados: List<EmpleadoResponse>? = empleadoManagement.response.body()
            if (listadoEmpleados != null) {
                for (empleado in listadoEmpleados) {
                    repository.insertarEmpleado(empleado.toDomain().toDatabase())
                }
            }
        }
        return comprobacion

    }
}