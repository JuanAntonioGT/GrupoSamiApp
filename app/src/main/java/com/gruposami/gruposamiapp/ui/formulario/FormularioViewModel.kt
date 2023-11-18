package com.gruposami.gruposamiapp.ui.formulario

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


//@HiltViewModel
//class FormularioViewModel @Inject constructor(
//    private val listaFormularioServicioUseCase: ListaFormularioServicioUseCase,
//    private val obtenerServicioUseCase: ObtenerServicio,
//    private val obtenerListaServiciosUseCase: ObtenerListaServicioCompleto,
//    private val modificarServicioUseCase: ModificarServicio,
//    private val insertarMultimediaUseCase: InsertarMultimedia,
//    private val obtenerMultimediaUseCase: ObtenerMultimedia,
//    private val obtenerListaVidriosUseCase: ObtenerListaVidrios,
//    private val insertarEstado: InsertarEstado,
//) : ViewModel() {
//    var formularioServicio = MutableLiveData<List<FormularioServicio>?>()
//    val servicio = MutableLiveData<Servicio>()
//    val listaServicios = MutableLiveData<List<ServicioCompleto>>()
//    val multimediaLejosMutable = MutableLiveData<Multimedia?>()
//    val multimediaIntermediaMutable = MutableLiveData<Multimedia?>()
//    val multimediaCercaMutable = MutableLiveData<Multimedia?>()
//    val multimediaDibujoMutable = MutableLiveData<Multimedia?>()
//    val listaVidrios = MutableLiveData<List<ListaDeValores?>>()
//    val listaVidriosDescatalogados = MutableLiveData<List<ListaDeValores?>>()
//
//    fun onCreate(servicio_id_intent: Int) {
//        obtenerServicio(servicio_id_intent)
//    }
//
//    fun obtenerListaFormulario(servicio: Servicio) {
//        viewModelScope.launch {
//            // IMPORTANTE -> Tienes que vacíar la variable CADA vez que llamas
//            formularioServicio.value = null
//            val lista = listaFormularioServicioUseCase.invoke(servicio)
//            formularioServicio.postValue(lista)
//        }
//    }
//
//    fun obtenerServicio(servicio_id_intent: Int) {
//        viewModelScope.launch {
//            val servicioFiltrado = obtenerServicioUseCase.invoke(servicio_id_intent)
//            servicio.postValue(servicioFiltrado)
//        }
//    }
//
//    fun obtenerListaServicios(ordenId: Int){
//        viewModelScope.launch {
//            val lista = obtenerListaServiciosUseCase.invoke(ordenId)
//            listaServicios.postValue(lista)
//        }
//    }
//
//    fun modificarServicio(servicio: Servicio) {
//        viewModelScope.launch {
//            modificarServicioUseCase.invoke(servicio)
//        }
//    }
//
//    fun insertarImagen(multimedia: Multimedia) {
//        viewModelScope.launch {
//            insertarMultimediaUseCase.invoke(multimedia)
//        }
//    }
//
//    fun insertarEstado(estado: Estado) {
//        viewModelScope.launch {
//            insertarEstado.invoke(estado)
//        }
//    }
//
//    fun obtenerMultimedia(multimedia: Multimedia) {
//        viewModelScope.launch {
//            val response = obtenerMultimediaUseCase.invoke(multimedia)
//            if (response != null){
//                if (response.etiqueta_archivo.equals("Lejos")){
//                    multimediaLejosMutable.postValue(response)
//                }
//                if (response.etiqueta_archivo.equals("Intermedia")){
//                    multimediaIntermediaMutable.postValue(response)
//                }
//                if (response.etiqueta_archivo.equals("Cerca")){
//                    multimediaCercaMutable.postValue(response)
//                }
//                if (response.etiqueta_archivo.equals("Dibujo")) {
//                    multimediaDibujoMutable.postValue(response)
//                }
//            }
//        }
//    }
//
//    fun obtenerListaVidrios() {
//        viewModelScope.launch {
//            val lista = obtenerListaVidriosUseCase.invoke(false)
//            if (lista != null){
//                listaVidrios.postValue(lista)
//            }
//        }
//    }
//
//    fun obtenerListaVidriosDescatalogados() {
//        viewModelScope.launch {
//            val lista = obtenerListaVidriosUseCase.invoke(true)
//            if (lista != null){
//                listaVidriosDescatalogados.postValue(lista)
//            }
//        }
//    }
//
//}