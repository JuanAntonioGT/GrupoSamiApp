package com.gruposami.gruposamiapp.ui.main


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gruposami.gruposamiapp.domain.empleado.useCase.ObtenerEmpleado
import com.gruposami.gruposamiapp.domain.empleado.useCase.ObtenerListaEmpleados
import com.gruposami.gruposamiapp.domain.listadevalores.ObtenerListaDeValores
import com.gruposami.gruposamiapp.domain.orden.EliminarOrdenes
import com.gruposami.gruposamiapp.domain.orden.ObtenerOrdenes
import com.gruposami.gruposamiapp.domain.sesion.useCase.CerrarSesion
import com.gruposami.gruposamiapp.ui.main.model.NumeroOrdenes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.ConnectException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val cerrarSesionUseCase: CerrarSesion,
    private val obtenerListaEmpleados: ObtenerListaEmpleados,
    private val obtenerListaDeValores: ObtenerListaDeValores,
    private val obtenerEmpleado: ObtenerEmpleado,
    private val obtenerOrdenesUseCase: ObtenerOrdenes,
    private val eliminarOrdenes: EliminarOrdenes,
) : ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val mensaje = MutableLiveData<String?>()
    val mensaje_flotante = MutableLiveData<String>()
    val numeroOrdenes = MutableLiveData<NumeroOrdenes>()
    val cerrarSesionView = MutableLiveData<Boolean>()

    fun init() {
        /* Actualizará los siguientes datos por separado:
            - La lista de empleados y leerá al usuario de la sesión
            # Enviará tu dirección IP - POR AHORA NOOOOO
            - Actualizará los datos de las órdenes de trabajo */
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                Thread.sleep(500)
                parametrosBasicos()
                Thread.sleep(500)
                leerUsuario()
                Thread.sleep(500)
                obtenerOrdenes()
            }
        }
    }

    private fun obtenerOrdenes() {
        viewModelScope.launch {
            val comprobar = obtenerOrdenesUseCase.invoke()

            if (!comprobar.booleano) {
                mensaje_flotante.postValue(comprobar.mensaje.toString())
                cerrarSesion()
            }
        }
    }

    private fun parametrosBasicos() {
        viewModelScope.launch {
            listaDeValores()
            listaTrabajadores()
        }

    }

    fun listaDeValores() {
        viewModelScope.launch {
            try {
                obtenerListaDeValores.invoke()
//                obtenerFormularioServicioUseCase.invoke()
            } catch (e: Exception) {
                mensaje_flotante.postValue("Error al solicitar la lista de valores.")
            } catch (e: ConnectException) {
                mensaje_flotante.postValue("Parece que no tienes internet.")
            }
        }
    }

    fun listaTrabajadores() {
        viewModelScope.launch {
            try {
                obtenerListaEmpleados.invoke()
            } catch (e: Exception) {
                mensaje_flotante.postValue("Error al solicitar la lista de trabajadores.")
            } catch (e: ConnectException) {
                mensaje_flotante.postValue("Parece que no tienes internet.")
            }
        }
    }

    fun leerUsuario() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val usuario = obtenerEmpleado.invoke()
                    mensaje.postValue(usuario?.firstName)
                } catch (e: Exception) {
                    mensaje_flotante.postValue("Error al leer los datos del usuario.")
                } catch (e: ConnectException) {
                    mensaje_flotante.postValue("Parece que no tienes internet.")
                }
            }
        }
    }

    fun sincronizarOrdenes() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    isLoading.postValue(true)
                    obtenerOrdenes()
                } finally {
//                    resumenOrdenes()
                    isLoading.postValue(false)
                }
            }
        }
    }

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
//            for (estado in orden.servicioCompleto) {
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

    fun cerrarSesion() {
        viewModelScope.launch {
            cerrarSesionUseCase.invoke()
            cerrarSesionView.postValue(true)
        }
    }

}