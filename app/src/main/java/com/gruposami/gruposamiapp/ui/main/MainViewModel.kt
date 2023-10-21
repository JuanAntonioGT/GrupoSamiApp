package com.gruposami.gruposamiapp.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gruposami.gruposamiapp.domain.sesion.useCase.CerrarSesion
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.ConnectException
import java.net.SocketTimeoutException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val cerrarSesionUseCase: CerrarSesion,
//    private val obtenerEmpleadosUseCase: ObtenerEmpleados,
//    private val obtenerUsuarioUseCase: ObtenerUsuario,
//    private val enviarDireccionIPUseCase: EnviarDireccionIP,
//    private val sincronizarOrdenesUseCase: SincronizarOrdenes,
//    private val obtenerListaDeValoresUseCase: ObtenerListaDeValoresUseCase,
//    private val resumenOrdenesUseCase: ResumenOrdenes,

//    private val modificarEstadoUseCase: ModificarSesionEstado,
    // Traerse el formulario del servicio
//    private val obtenerFormularioServicioUseCase: ObtenerFormularioServicioUseCase,
) : ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val mensaje = MutableLiveData<String?>()
    val mensaje_flotante = MutableLiveData<String>()
    val numeroOrdenes = MutableLiveData<NumeroOrdenes>()

    fun onCreate() {
        /* Actualizará los siguientes datos por separado:
            - La lista de empleados y leerá al usuario de la sesión
            - Enviará tu dirección IP
            - Actualizará los datos de las órdenes de trabajo */
        viewModelScope.launch {
            withContext(Dispatchers.IO) {

                Thread.sleep(500)
//                listaTrabajadores()
//                Thread.sleep(500)
//                leerUsuario()
//                Thread.sleep(500)
//                direccionIP()
//                Thread.sleep(500)
//                listaDeValores()
//                Thread.sleep(500)
//                sincronizarOrdenes()
            }
        }
    }
//
//    fun listaTrabajadores() {
//        viewModelScope.launch {
//            try {
//                obtenerEmpleadosUseCase.invoke()
//            } catch (e: Exception) {
//                mensaje_flotante.postValue("Error al solicitar la lista de trabajadores.")
//            } catch (e: ConnectException) {
//                mensaje_flotante.postValue("Parece que no tienes internet.")
//            }
//        }
//    }
//
//    fun leerUsuario() {
//        viewModelScope.launch {
//            withContext(Dispatchers.IO) {
//                try {
//                    val empleado = obtenerUsuarioUseCase.invoke()
//                    mensaje.postValue(empleado?.first_name)
//                } catch (e: Exception) {
//                    mensaje_flotante.postValue("Error al leer los datos del usuario.")
//                } catch (e: ConnectException) {
//                    mensaje_flotante.postValue("Parece que no tienes internet.")
//                }
//            }
//        }
//    }
//
//    fun direccionIP() {
//        viewModelScope.launch {
//            try {
//                val direccion_ip = IpAddress().getLocalIpAddress()
//                enviarDireccionIPUseCase.invoke(direccion_ip.toString())
//            } catch (e: Exception) {
//                mensaje_flotante.postValue("Fallo de conexión")
//            } catch (e: ConnectException) {
//                mensaje_flotante.postValue("Parece que no tienes internet.")
//            }
//
//        }
//    }
//
//    fun listaDeValores() {
//        viewModelScope.launch {
//            try {
//                obtenerListaDeValoresUseCase.invoke()
//                obtenerFormularioServicioUseCase.invoke()
//            } catch (e: Exception) {
//                mensaje_flotante.postValue("Error al solicitar la lista de valores.")
//            } catch (e: ConnectException) {
//                mensaje_flotante.postValue("Parece que no tienes internet.")
//            }
//        }
//    }
//
    fun cerrarSesion() {
        viewModelScope.launch {
            cerrarSesionUseCase.invoke()
        }
    }
//
//    fun sincronizarOrdenes() {
//        viewModelScope.launch {
//            withContext(Dispatchers.IO) {
//                try {
//                    isLoading.postValue(true)
//                    sincronizarOrdenesUseCase.invoke()
//                } catch (e: NullPointerException) {
//                    mensaje_flotante.postValue("Error al leer las órdenes de trabajo. El error. $e")
//                } catch (e: SocketTimeoutException) {
//                    mensaje_flotante.postValue("Parece que el servidor no responde...")
//                } catch (e: ConnectException) {
//                    mensaje_flotante.postValue("Parece que no tienes internet.")
//                } finally {
//                    Thread.sleep(500)
//                    resumenOrdenes()
//                    isLoading.postValue(false)
//                }
//            }
//        }
//    }
//
//    fun resumenOrdenes() {
//        viewModelScope.launch {
//            withContext(Dispatchers.IO) {
//                val resultado = resumenOrdenesUseCase.invoke()
//                if (resultado != null) {
//                    comprobarNumeroOrdenes(resultado)
//                }
//            }
//        }
//    }
//
//    fun comprobarNumeroOrdenes(ordenesCompletas: List<OrdenCompleta>) {
//        var medirInt = 0
//        var medidoInt = 0
//        var motanrInt = 0
//        var montadoInt = 0
//
//        for (orden in ordenesCompletas) {
//            var flagMedir = false
//            var flagMedido = false
//            var flagMontar = false
//            var flagMontado = false
//
//            for (estado in orden.servicioCompleto){
//                if (estado?.estado?.estado.equals("Medir")) flagMedir = true
//                if (estado?.estado?.estado.equals("Medido")) flagMedido = true
//                if (estado?.estado?.estado.equals("Montar")) flagMontar = true
//                if (estado?.estado?.estado.equals("Montado")) flagMontado = true
//            }
//            if (flagMedir) medirInt += 1
//            if (flagMedido) medidoInt += 1
//            if (flagMontar) motanrInt += 1
//            if (flagMontado) montadoInt += 1
//        }
//
//        medir.postValue(medirInt)
//        medido.postValue(medidoInt)
//        montar.postValue(motanrInt)
//        montado.postValue(montadoInt)
//    }

}