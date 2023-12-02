package com.gruposami.gruposamiapp.ui.main


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gruposami.gruposamiapp.domain.empleado.useCase.ObtenerEmpleado
import com.gruposami.gruposamiapp.domain.orden.ResumenOrdenes
import com.gruposami.gruposamiapp.domain.orden.model.OrdenCompleta
import com.gruposami.gruposamiapp.domain.sesion.useCase.CerrarSesion
import com.gruposami.gruposamiapp.domain.sincronizacion.SincronizacionDatos
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

    private val obtenerEmpleado: ObtenerEmpleado,
    private val sincronizacionDatosUseCase: SincronizacionDatos,
    private val resumenOrdenesUseCase: ResumenOrdenes,

    ) : ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val mensaje = MutableLiveData<String?>()
    val mensajeFlotante = MutableLiveData<String>()
    val numeroOrdenes = MutableLiveData<NumeroOrdenes>()
    val cerrarSesionView = MutableLiveData<Boolean>()

    fun init() {
        /* Actualizará los siguientes datos por separado:
            - La lista de empleados y leerá al usuario de la sesión
            # Enviará tu dirección IP - POR AHORA NOOOOO
            - Actualizará los datos de las órdenes de trabajo */
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                leerUsuario()
                compararHora() // Pendiente de decidir
                Thread.sleep(1000)
                sincronizarOrdenes()
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
                    mensajeFlotante.postValue("Error al leer los datos del usuario.")
                } catch (e: ConnectException) {
                    mensajeFlotante.postValue("Parece que no tienes internet.")
                }
            }
        }
    }

    fun compararHora() {
        // ¿Comparar la hora para mandar a reajustarla?
    }
    fun sincronizarOrdenes() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    isLoading.postValue(true)
                    sincronizacionDatosUseCase.invoke()
                } finally {
                    resumenOrdenes()
                    isLoading.postValue(false)
                }
            }
        }
    }

    fun resumenOrdenes() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val resultado = resumenOrdenesUseCase.invoke()
                if (resultado != null) {
                    comprobarNumeroOrdenes(resultado)
                }
            }
        }
    }

    fun comprobarNumeroOrdenes(ordenesCompletas: List<OrdenCompleta>) {
        val numeroOrdenesTemp = NumeroOrdenes()

        for (orden in ordenesCompletas) {
            var flagMedir = false
            var flagMedido = false
            var flagMontar = false
            var flagMontado = false

            if (orden.estado.last().estado.equals("Medir")) flagMedir = true
            if (orden.estado.last().estado.equals("Medido")) flagMedido = true
            if (orden.estado.last().estado.equals("Montar")) flagMontar = true
            if (orden.estado.last().estado.equals("Montado")) flagMontado = true

            for (servicioCompleto in orden.servicioCompleto) {

//                if (!flagMedir || !flagMedido || !flagMontar || !flagMontado) {
                    // Si no he encontrado nada en los servicios, buscar en el último estado de la orden
                if (servicioCompleto.estado.isNotEmpty()){
                    if (servicioCompleto.estado.last()?.estado.equals("Medir")) flagMedir = true
                    if (servicioCompleto.estado.last()?.estado.equals("Medido")) flagMedido = true
                    if (servicioCompleto.estado.last()?.estado.equals("Montar")) flagMontar = true
                    if (servicioCompleto.estado.last()?.estado.equals("Montado")) flagMontado = true
                }

//                }

            }
            if (flagMedir) numeroOrdenesTemp.medir += 1
            if (flagMedido) numeroOrdenesTemp.medido += 1
            if (flagMontar) numeroOrdenesTemp.montar += 1
            if (flagMontado) numeroOrdenesTemp.montado += 1
        }
        numeroOrdenes.postValue(numeroOrdenesTemp)

    }

    fun cerrarSesion() {
        viewModelScope.launch {
            cerrarSesionUseCase.invoke()
            cerrarSesionView.postValue(true)
        }
    }

}