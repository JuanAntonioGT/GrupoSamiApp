package com.gruposami.gruposamiapp.ui.firma

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gruposami.gruposamiapp.domain.firma.InsertarFirma
import com.gruposami.gruposamiapp.domain.firma.model.Firma
import com.gruposami.gruposamiapp.domain.multimedia.InsertarMultimedia
import com.gruposami.gruposamiapp.domain.multimedia.model.Multimedia
import com.gruposami.gruposamiapp.domain.orden.ModificarOrden
import com.gruposami.gruposamiapp.domain.orden.ObtenerOrdenPorId
import com.gruposami.gruposamiapp.domain.orden.model.Orden
import com.gruposami.gruposamiapp.domain.servicio.ObtenerListaServicioCompleto
import com.gruposami.gruposamiapp.domain.servicio.model.Servicio
import com.gruposami.gruposamiapp.domain.servicio.model.ServicioCompleto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FirmaViewModel @Inject constructor(
    private val insertarMultimediaUseCase: InsertarMultimedia,
    private val obtenerOrdenPorIdUseCase: ObtenerOrdenPorId,
    private val obtenerServicioPorOrdenUseCase: ObtenerListaServicioCompleto,
    private val insertarFirmaUseCase: InsertarFirma,
    private val modificarOrdenUseCase: ModificarOrden,
) : ViewModel() {
    val servicio = MutableLiveData<Servicio>()
    val multimediaLejosMutable = MutableLiveData<Multimedia?>()
    val multimediaIntermediaMutable = MutableLiveData<Multimedia?>()
    val multimediaCercaMutable = MutableLiveData<Multimedia?>()
    val multimediaDibujoMutable = MutableLiveData<Multimedia?>()
    val listado_servicios = MutableLiveData<List<ServicioCompleto>>()
    val orden = MutableLiveData<Orden>()

    fun obtenerServicios(orden_id: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val response = obtenerServicioPorOrdenUseCase.invoke(orden_id)
                    listado_servicios.postValue(response)
                } catch (e: NullPointerException) {
                    println("No tienes órdenes de trabajo asignadas para medir")
                }
            }
        }
    }

    fun obtenerMultimedia(multimedia: Multimedia) {
        viewModelScope.launch {
//            val response = obtenerMultimediaUseCase.invoke(multimedia)
//            if (response != null) {
//                if (response.etiqueta_archivo.equals("Lejos")) {
//                    multimediaLejosMutable.postValue(response.toDomain())
//                }
//                if (response.etiqueta_archivo.equals("Intermedia")) {
//                    multimediaIntermediaMutable.postValue(response.toDomain())
//                }
//                if (response.etiqueta_archivo.equals("Cerca")) {
//                    multimediaCercaMutable.postValue(response.toDomain())
//                }
//                if (response.etiqueta_archivo.equals("Dibujo")) {
//                    multimediaDibujoMutable.postValue(response.toDomain())
//                }
//            }
        }
    }

    fun insertarImagen(multimedia: Multimedia) {
        viewModelScope.launch {
            insertarMultimediaUseCase.invoke(multimedia)
        }
    }

    fun insertarFirma(firma: Firma) {
        viewModelScope.launch {
            insertarFirmaUseCase.invoke(firma)
        }
    }

    fun modificarOrden(orden: Orden) {
        viewModelScope.launch {
            modificarOrdenUseCase.invoke(orden)
        }
    }

    fun obtenerOrden(ordenId: Int) {
        viewModelScope.launch {
            val response = obtenerOrdenPorIdUseCase.invoke(ordenId)
            orden.postValue(response)
        }
    }
}