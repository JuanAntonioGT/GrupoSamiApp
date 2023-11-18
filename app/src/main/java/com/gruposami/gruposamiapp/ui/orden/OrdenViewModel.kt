package com.gruposami.gruposamiapp.ui.orden

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gruposami.gruposamiapp.domain.contacto.EliminarContacto
import com.gruposami.gruposamiapp.domain.contacto.InsertarContacto
import com.gruposami.gruposamiapp.domain.contacto.ModificarContacto
import com.gruposami.gruposamiapp.domain.contacto.model.Contacto
import com.gruposami.gruposamiapp.domain.direccion.InsertarDireccion
import com.gruposami.gruposamiapp.domain.direccion.ModificarDireccion
import com.gruposami.gruposamiapp.domain.direccion.model.Direccion
import com.gruposami.gruposamiapp.domain.estado.InsertarEstado
import com.gruposami.gruposamiapp.domain.estado.model.Estado
import com.gruposami.gruposamiapp.domain.multimedia.InsertarMultimedia
import com.gruposami.gruposamiapp.domain.multimedia.model.Multimedia
import com.gruposami.gruposamiapp.domain.orden.ModificarOrden
import com.gruposami.gruposamiapp.domain.orden.ObtenerOrdenesPorEstado
import com.gruposami.gruposamiapp.domain.orden.model.Orden
import com.gruposami.gruposamiapp.domain.orden.model.OrdenCompleta
import com.gruposami.gruposamiapp.domain.servicio.InsertarServicio
import com.gruposami.gruposamiapp.domain.servicio.InsertarServicioCompleto
import com.gruposami.gruposamiapp.domain.servicio.model.Servicio
import com.gruposami.gruposamiapp.domain.servicio.model.ServicioCompleto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class OrdenViewModel @Inject constructor(
    private val obtenerOrdenesPorEstadoUseCase: ObtenerOrdenesPorEstado,
    private val modificarOrdenUseCase: ModificarOrden,
    // Direccion
    private val insertarDireccionUseCase: InsertarDireccion,
    private val modificarDireccionUseCase: ModificarDireccion,
    // Contacto
    private val insertarContactoUseCase: InsertarContacto,
    private val modificarContactoUseCase: ModificarContacto,
    private val eliminarContactoUseCase: EliminarContacto,
    // Servicio
    private val insertarServicioUseCase: InsertarServicioCompleto,
    private val insertarMultimedia: InsertarMultimedia,
    // Estado
    private val insertarEstadoUseCase: InsertarEstado,
//    private val
) : ViewModel() {

    val mensajeFlotante = MutableLiveData<String>()
    val listadoOrdenes = MutableLiveData<List<OrdenCompleta>>()

    fun onCreate() {
        obtenerOrdenes()
    }

    fun modificarOrden(orden: Orden) {
        viewModelScope.launch {
            modificarOrdenUseCase.invoke(orden)
        }
    }

    fun nuevoContacto(contacto: Contacto) {
        viewModelScope.launch {
            insertarContactoUseCase.invoke(contacto)
        }
    }

    fun modificarContacto(contacto: Contacto) {
        viewModelScope.launch {
            modificarContactoUseCase.invoke(contacto)
        }
    }

    fun eliminarContacto(contacto: Contacto) {
        viewModelScope.launch {
            eliminarContactoUseCase.invoke(contacto.id)
        }
    }

    fun obtenerOrdenes() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val response = obtenerOrdenesPorEstadoUseCase.invoke()
                    listadoOrdenes.postValue(response)
                } catch (e: NullPointerException) {
                    mensajeFlotante.postValue("No tienes órdenes de trabajo asignadas")
                }
            }
        }
    }

    fun nuevaDireccion(nuevaDireccion: Direccion) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                insertarDireccionUseCase.invoke(nuevaDireccion)
            }
        }
    }

    fun modificarDireccion(direccion: Direccion) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                modificarDireccionUseCase.invoke(direccion)
            }
        }
    }

    fun nuevoServicio(nuevoServicio: ServicioCompleto) {
        viewModelScope.launch {
            insertarServicioUseCase.invoke(nuevoServicio)
        }
    }

    fun insertarImagen(multimedia: Multimedia) {
        viewModelScope.launch {
            insertarMultimedia.invoke(multimedia)
        }
    }

    fun nuevoEstadoOrden(estado: Estado) {
        viewModelScope.launch {
            insertarEstadoUseCase.invoke(estado)
        }
    }

}
