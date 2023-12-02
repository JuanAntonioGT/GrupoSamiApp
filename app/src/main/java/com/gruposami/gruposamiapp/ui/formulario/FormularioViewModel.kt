package com.gruposami.gruposamiapp.ui.formulario

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gruposami.gruposamiapp.domain.estado.InsertarEstado
import com.gruposami.gruposamiapp.domain.estado.model.Estado
import com.gruposami.gruposamiapp.domain.firma.InsertarFirma
import com.gruposami.gruposamiapp.domain.firma.model.Firma
import com.gruposami.gruposamiapp.domain.formularioservicio.ListaFormularioServicios
import com.gruposami.gruposamiapp.domain.formularioservicio.model.FormularioServicio
import com.gruposami.gruposamiapp.domain.listadevalores.ObtenerListaVidrios
import com.gruposami.gruposamiapp.domain.listadevalores.model.ListaDeValores
import com.gruposami.gruposamiapp.domain.multimedia.InsertarMultimedia
import com.gruposami.gruposamiapp.domain.multimedia.ObtenerMultimedia
import com.gruposami.gruposamiapp.domain.multimedia.model.Multimedia
import com.gruposami.gruposamiapp.domain.servicio.InsertarEstadoServicio
import com.gruposami.gruposamiapp.domain.servicio.ModificarServicio
import com.gruposami.gruposamiapp.domain.servicio.ObtenerListaServicioCompleto
import com.gruposami.gruposamiapp.domain.servicio.ObtenerServicio
import com.gruposami.gruposamiapp.domain.servicio.model.Servicio
import com.gruposami.gruposamiapp.domain.servicio.model.ServicioCompleto
import com.gruposami.gruposamiapp.domain.servicio.model.ServicioEstado
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FormularioViewModel @Inject constructor(
    private val obtenerServicioUseCase: ObtenerServicio,
    private val modificarServicioUseCase: ModificarServicio,
    private val listaFormularioServicioUseCase: ListaFormularioServicios,
    private val obtenerListaServiciosUseCase: ObtenerListaServicioCompleto,
    private val insertarMultimediaUseCase: InsertarMultimedia,
    private val obtenerMultimediaUseCase: ObtenerMultimedia,
    private val obtenerListaVidriosUseCase: ObtenerListaVidrios,
    private val insertarEstado: InsertarEstado,
    private val insertarServicioEstadoUseCase: InsertarEstadoServicio,
    private val insertarFirmaUseCase: InsertarFirma
) : ViewModel() {
    val servicio = MutableLiveData<Servicio>()
    var formularioServicio = MutableLiveData<List<FormularioServicio>?>()
    val listaServicios = MutableLiveData<List<ServicioCompleto>>()
    val multimediaLejosMutable = MutableLiveData<Multimedia?>()
    val multimediaIntermediaMutable = MutableLiveData<Multimedia?>()
    val multimediaCercaMutable = MutableLiveData<Multimedia?>()
    val multimediaDibujoMutable = MutableLiveData<Multimedia?>()
    val listaVidrios = MutableLiveData<List<ListaDeValores?>>()
    val listaVidriosDescatalogados = MutableLiveData<List<ListaDeValores?>>()

    fun onCreate(servicioId: Int) {
        viewModelScope.launch {
            val servicioFiltrado = obtenerServicioUseCase.invoke(servicioId)
            servicio.postValue(servicioFiltrado)
        }
    }

    fun obtenerListaFormulario(servicio: Servicio) {
        viewModelScope.launch {
            // IMPORTANTE -> Tienes que vacíar la variable CADA vez que llamas
            formularioServicio.value = null
            val lista = listaFormularioServicioUseCase.invoke(servicio)
            formularioServicio.postValue(lista)
        }
    }

    fun obtenerListaServicios(ordenId: Int) {
        viewModelScope.launch {
            val lista = obtenerListaServiciosUseCase.invoke(ordenId)
            listaServicios.postValue(lista)
        }
    }

    fun modificarServicio(servicio: Servicio) {
        viewModelScope.launch {
            modificarServicioUseCase.invoke(servicio)
        }
    }

    fun insertarImagen(multimedia: Multimedia) {
        viewModelScope.launch {
            insertarMultimediaUseCase.invoke(multimedia)
        }
    }

    fun insertarEstado(estado: Estado) {
        viewModelScope.launch {
            insertarEstado.invoke(estado)
        }
    }

    fun obtenerMultimedia(multimedia: Multimedia) {
        viewModelScope.launch {
            val response = obtenerMultimediaUseCase.invoke(multimedia)
            if (response != null) {
                if (response.etiquetaArchivo.equals("Lejos")) {
                    multimediaLejosMutable.postValue(response)
                }
                if (response.etiquetaArchivo.equals("Intermedia")) {
                    multimediaIntermediaMutable.postValue(response)
                }
                if (response.etiquetaArchivo.equals("Cerca")) {
                    multimediaCercaMutable.postValue(response)
                }
                if (response.etiquetaArchivo.equals("Dibujo")) {
                    multimediaDibujoMutable.postValue(response)
                }
            }
        }
    }

    fun obtenerListaVidrios() {
        viewModelScope.launch {
            val lista = obtenerListaVidriosUseCase.invoke(false)
            if (lista != null) {
                listaVidrios.postValue(lista)
            }
        }
    }

    fun obtenerListaVidriosDescatalogados() {
        viewModelScope.launch {
            val lista = obtenerListaVidriosUseCase.invoke(true)
            if (lista != null) {
                listaVidriosDescatalogados.postValue(lista)
            }
        }
    }

    fun insertarEstadoSer(estado: Estado, servicioId: Int) {
        viewModelScope.launch {
            insertarEstado.invoke(estado)
            insertarServicioEstadoUseCase.invoke(ServicioEstado(servicioId, estado.id))
        }
    }

    fun insertarFirma(firma: Firma) {
        viewModelScope.launch {
            insertarFirmaUseCase.invoke(firma)
        }
    }

}