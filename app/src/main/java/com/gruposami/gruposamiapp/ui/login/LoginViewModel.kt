package com.gruposami.gruposamiapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gruposami.gruposamiapp.domain.empleado.useCase.ObtenerListaEmpleados
import com.gruposami.gruposamiapp.domain.formularioservicio.ObtenerFormularioServicio
import com.gruposami.gruposamiapp.domain.listadevalores.ObtenerListaDeValores
import com.gruposami.gruposamiapp.domain.login.useCase.ComprobarLogin
import com.gruposami.gruposamiapp.domain.sesion.model.Sesion
import com.gruposami.gruposamiapp.domain.sesion.useCase.ComprobarSesion
import com.gruposami.gruposamiapp.ui.login.model.LoginRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val comprobarSesion: ComprobarSesion,
    private val comprobarLogin: ComprobarLogin,
    private val obtenerListaEmpleados: ObtenerListaEmpleados,
    private val obtenerListaDeValores: ObtenerListaDeValores,
    private val obtenerFormularioServicio: ObtenerFormularioServicio,
) : ViewModel() {

    private val _cargando = MutableLiveData<Boolean>()
    private val _mostrarTarjeta = MutableLiveData<Boolean>()
    private val _logueado = MutableLiveData<Boolean>()
    private val _mensaje = MutableLiveData<String>()
    private val _error = MutableLiveData<String>()
    private val _usuario = MutableLiveData<String>()

    val cargando: LiveData<Boolean> = _cargando
    val mostrarTarjeta: LiveData<Boolean> = _mostrarTarjeta
    val logueado: LiveData<Boolean> = _logueado
    val mensaje: LiveData<String> = _mensaje
    val error: LiveData<String> = _error
    val usuario: LiveData<String> = _usuario

    /**
     * Al entrar en la aplicacion se comprobará si tienes registrada la sesión -> True.
     * Si no lo tienes, no puedes ir al Menu Principal,
     * Si lo tienes, entras al menu principal.
     */
    fun init() {
        viewModelScope.launch {
            _cargando.postValue(true)
            _mostrarTarjeta.postValue(false)

            withContext(Dispatchers.IO) {
                val comprobarUsuario = comprobarSesion.invoke()
                Thread.sleep(500)
                if (comprobarUsuario) {
                    _logueado.postValue(true)
                } else {
                    _logueado.postValue(false)
                    _mostrarTarjeta.postValue(true)
                    _usuario.postValue(Sesion.usuario)
                }
                _cargando.postValue(false)
            }
        }
    }

    /**
     * Función para comprobar que lo que has escrito en la ventana del login está correcto
     * Si es valida lo que has escrito en el formulario del login, enviará una petición con tus
     * credenciales a la API, si no, te notificará que no es correcto.
     * @param usuarioRequest
     */
    fun comprobacionLogin(usuarioRequest: LoginRequest) {
        val comprobacion = usuarioRequest.esValido()
        if (comprobacion.booleano) {
            _cargando.postValue(true)
            _mostrarTarjeta.postValue(false)
            enviarLoginRequest(usuarioRequest)
        } else {
            _error.postValue(comprobacion.mensaje.toString())
        }
    }

    /**
     * Funcion que envia la peticion de login al servidor, esperando una respuesta.
     * @param loginRequest
     * @return sesion
     */
    private fun enviarLoginRequest(loginRequest: LoginRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            val comprobarUsuario = comprobarLogin(loginRequest)
            _cargando.postValue(false)
            _mostrarTarjeta.postValue(true)
            if (comprobarUsuario.booleano) {
                // Que te traigas las listas de valores y todos los valores estáticos de la app.
                parametrosBasicos()
                _logueado.postValue(true)
            } else {
                _error.postValue(comprobarUsuario.mensaje.toString())
            }
        }
    }

    private fun parametrosBasicos() {
        listaDeValores()
        listaTrabajadores()
        listaFormularioServicios()
    }

    private fun listaDeValores() {
        viewModelScope.launch {
            val comprobar = obtenerListaEmpleados.invoke()
            if (!comprobar.booleano) {
                _mensaje.postValue(comprobar.mensaje.toString())
            }
        }
    }

    private fun listaTrabajadores() {
        viewModelScope.launch {
            val comprobar = obtenerListaDeValores.invoke()
            if (!comprobar.booleano) {
                _mensaje.postValue(comprobar.mensaje.toString())
            }
        }
    }

    private fun listaFormularioServicios() {
        viewModelScope.launch {
            val comprobar = obtenerFormularioServicio.invoke()
            if (!comprobar.booleano) {
                _mensaje.postValue(comprobar.mensaje.toString())
            }
        }
    }

}