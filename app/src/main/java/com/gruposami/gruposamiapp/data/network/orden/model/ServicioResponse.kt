package com.gruposami.gruposamiapp.data.network.orden.model

import com.google.gson.annotations.SerializedName
import com.gruposami.gruposamiapp.data.network.empleado.model.EmpleadoResponse
import javax.inject.Singleton

@Singleton
class ServicioResponse(
    @SerializedName("id") var id: Int,
    @SerializedName("descripcion_previa") var descripcionPrevia: String,
    @SerializedName("medido") var medido: Boolean?,
    @SerializedName("comentario_medicion") var comentarioMedicion: String,
    @SerializedName("fecha_medicion") var fechaMedicion: String,
    @SerializedName("medido_por") var medidoPor: List<EmpleadoResponse?>,
    @SerializedName("montado") var montado: Boolean?,
    @SerializedName("comentario_montaje") var comentarioMontaje: String,
    @SerializedName("fecha_montaje") var fechaMontaje: String,
    @SerializedName("montado_por") var montadoPor: List<EmpleadoResponse?>,
    @SerializedName("pendiente") var pendiente: Boolean?,
    @SerializedName("tipo_pendiente") var tipoPendiente: String,
    @SerializedName("comentario_pendiente") var comentarioPendiente: String,
    @SerializedName("fecha_pendiente") var fechaPendiente: String,
    @SerializedName("pendiente_por") var pendientePor: List<EmpleadoResponse?>,
    @SerializedName("descripcion_uno") var descripcionUno: String,
    @SerializedName("descripcion_dos") var descripcionDos: String,
    @SerializedName("descripcion_tres") var descripcionTres: String,
    @SerializedName("descripcion_cuatro") var descripcionCuatro: String,
    @SerializedName("descripcion_cinco") var descripcionCinco: String,
    @SerializedName("desperfectos") var desperfectos: String,
    @SerializedName("cantidad") var cantidad: Int,
    @SerializedName("modeloA") var modeloA: String,
    @SerializedName("camara") var camara: String,
    @SerializedName("palilleria") var palilleria: String,
    @SerializedName("modeloB") var modeloB: String,
    @SerializedName("luzAlto") var luzAlto: Double,
    @SerializedName("luzAncho") var luzAncho: Double,
    @SerializedName("espatulaAlto") var espatulaAlto: Double,
    @SerializedName("espatulaAncho") var espatulaAncho: Double,
    @SerializedName("ofertadaAlto") var ofertadaAlto: Double,
    @SerializedName("ofertadaAncho") var ofertadaAncho: Double,
    @SerializedName("formuladaAlto") var formuladaAlto: Double,
    @SerializedName("formuladaAncho") var formuladaAncho: Double,
    @SerializedName("modificadaAlto") var modificadaAlto: Double,
    @SerializedName("modificadaAncho") var modificadaAncho: Double,
    @SerializedName("sellado") var sellado: String,
    @SerializedName("manufacturaA") var manufacturaA: String,
    @SerializedName("manufacturaB") var manufacturaB: String,
    @SerializedName("observaciones") var observaciones: String,
    @SerializedName("restos") var restos: Boolean,
    @SerializedName("recogido") var recogido: Boolean,
    @SerializedName("fecha_creacion") var fechaCreacion: String,
    @SerializedName("fecha_modificacion") var fechaModificacion: String,
    @SerializedName("multimedia_set") var multimedia: List<MultimediaResponse?>,
    @SerializedName("firma_set") var firma: List<FirmaResponse?>,
    @SerializedName("estado") var estado: EstadoResponse?,
    @SerializedName("orden") var ordenId: Int?,
)