package com.gruposami.gruposamiapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.gruposami.gruposamiapp.domain.servicio.model.Servicio
import com.gruposami.gruposamiapp.domain.servicio.model.ServicioEstado
import com.gruposami.gruposamiapp.domain.servicio.model.ServicioMedido
import com.gruposami.gruposamiapp.domain.servicio.model.ServicioMontado
import com.gruposami.gruposamiapp.domain.servicio.model.ServicioPendiente


@Entity(tableName = "servicio_table")
data class ServicioEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id_servicio") var id: Int,
    @ColumnInfo(name = "descripcion_previa") var descripcionPrevia: String?,
    @ColumnInfo(name = "medido", defaultValue = "null") var medido: Boolean?,
    @ColumnInfo(name = "comentario_medicion") var comentarioMedicion: String?,
    @ColumnInfo(name = "fecha_medicion") var fechaMedicion: String?,
    @ColumnInfo(name = "montado", defaultValue = "null") var montado: Boolean?,
    @ColumnInfo(name = "comentario_montaje") var comentarioMontaje: String?,
    @ColumnInfo(name = "fecha_montaje") var fechaMontaje: String?,
    @ColumnInfo(name = "pendiente", defaultValue = "null") var pendiente: Boolean?,
    @ColumnInfo(name = "tipo_pendiente") var tipoPendiente: String?,
    @ColumnInfo(name = "comentario_pendiente") var comentarioPendiente: String?,
    @ColumnInfo(name = "fecha_pendiente") var fechaPendiente: String?,
    @ColumnInfo(name = "descripcion_uno") var descripcionUno: String?,
    @ColumnInfo(name = "descripcion_dos") var descripcionDos: String?,
    @ColumnInfo(name = "descripcion_tres") var descripcionTres: String?,
    @ColumnInfo(name = "descripcion_cuatro") var descripcionCuatro: String?,
    @ColumnInfo(name = "descripcion_cinco") var descripcionCinco: String?,
    @ColumnInfo(name = "desperfectos") var desperfectos: String?,
    @ColumnInfo(name = "cantidad") var cantidad: Int?,
    @ColumnInfo(name = "modeloA") var modeloA: String?,
    @ColumnInfo(name = "camara") var camara: String?,
    @ColumnInfo(name = "palilleria") var palilleria: String?,
    @ColumnInfo(name = "modeloB") var modeloB: String?,
    @ColumnInfo(name = "luzAlto") var luzAlto: Double?,
    @ColumnInfo(name = "luzAncho") var luzAncho: Double?,
    @ColumnInfo(name = "espatulaAlto") var espatulaAlto: Double?,
    @ColumnInfo(name = "espatulaAncho") var espatulaAncho: Double?,
    @ColumnInfo(name = "ofertadaAlto") var ofertadaAlto: Double?,
    @ColumnInfo(name = "ofertadaAncho") var ofertadaAncho: Double?,
    @ColumnInfo(name = "formuladaAlto") var formuladaAlto: Double?,
    @ColumnInfo(name = "formuladaAncho") var formuladaAncho: Double?,
    @ColumnInfo(name = "modificadaAlto") var modificadaAlto: Double?,
    @ColumnInfo(name = "modificadaAncho") var modificadaAncho: Double?,
    @ColumnInfo(name = "sellado") var sellado: String?,
    @ColumnInfo(name = "manufacturaA") var manufacturaA: String?,
    @ColumnInfo(name = "manufacturaB") var manufacturaB: String?,
    @ColumnInfo(name = "observaciones") var observaciones: String?,
    @ColumnInfo(name = "restos") var restos: Boolean?,
    @ColumnInfo(name = "recogido") var recogido: Boolean?,
    @ColumnInfo(name = "fecha_creacion") var fechaCreacion: String?,
    @ColumnInfo(name = "fecha_modificacion") var fechaModificacion: String?,
    @ColumnInfo(name = "estado_id") var estadoId: Int?,
    @ColumnInfo(name = "orden_id") var ordenId: Int?,
    @ColumnInfo(name = "faseFormulario") var faseFormulario: Int?,
    )


fun Servicio.toDatabase() = ServicioEntity(
    id = id,
    descripcionPrevia = descripcionPrevia,
    medido = medido,
    comentarioMedicion = comentarioMedicion,
    fechaMedicion = fechaMedicion,
    montado = montado,
    comentarioMontaje = comentarioMontaje,
    fechaMontaje = fechaMontaje,
    pendiente = pendiente,
    tipoPendiente = tipoPendiente,
    comentarioPendiente = comentarioPendiente,
    fechaPendiente = fechaPendiente,
    descripcionUno = descripcionUno,
    descripcionDos = descripcionDos,
    descripcionTres = descripcionTres,
    descripcionCuatro = descripcionCuatro,
    descripcionCinco = descripcionCinco,
    desperfectos = desperfectos,
    cantidad = cantidad,
    modeloA = modeloA,
    camara = camara,
    palilleria = palilleria,
    modeloB = modeloB,
    luzAlto = luzAlto,
    luzAncho = luzAncho,
    espatulaAlto = espatulaAlto,
    espatulaAncho = espatulaAncho,
    ofertadaAlto = ofertadaAlto,
    ofertadaAncho = ofertadaAncho,
    formuladaAlto = formuladaAlto,
    formuladaAncho = formuladaAncho,
    modificadaAlto = modificadaAlto,
    modificadaAncho = modificadaAncho,
    sellado = sellado,
    manufacturaA = manufacturaA,
    manufacturaB = manufacturaB,
    observaciones = observaciones,
    restos = restos,
    recogido = recogido,
    fechaCreacion = fechaCreacion,
    fechaModificacion = fechaModificacion,
    estadoId = estadoId,
    ordenId = ordenId,
    faseFormulario = faseFormulario
)


@Entity(tableName = "servicio_estado_table", primaryKeys = ["servicio_id", "estado_id"], indices = [Index(value = ["estado_id"])])
data class ServicioEstadoEntity(
    @ColumnInfo(name = "servicio_id") val servicioId: Int,
    @ColumnInfo(name = "estado_id") val estadoId: Int
)

fun ServicioEstado.toDatabase() = ServicioEstadoEntity(
    servicioId = servicioId,
    estadoId = estadoId,
)

@Entity(tableName = "servicio_medido_table", primaryKeys = ["servicio_id", "user_id"], indices = [Index(value = ["user_id"])])
data class ServicioMedidoEntity(
    @ColumnInfo(name = "servicio_id") val servicioId: Int,
    @ColumnInfo(name = "user_id") val userId: Int,
)

fun ServicioMedido.toDatabase() = ServicioMedidoEntity(
    servicioId = servicioId,
    userId = userId
)


@Entity(tableName = "servicio_montado_table", primaryKeys = ["servicio_id", "user_id"], indices = [Index(value = ["user_id"])])
data class ServicioMontadoEntity(
    @ColumnInfo(name = "servicio_id") val servicioId: Int,
    @ColumnInfo(name = "user_id") val userId: Int,
)

fun ServicioMontado.toDatabase() = ServicioMontadoEntity(
    servicioId = servicioId,
    userId = userId
)

@Entity(tableName = "servicio_pendiente_table", primaryKeys = ["servicio_id", "user_id"], indices = [Index(value = ["user_id"])])
data class ServicioPendienteEntity(
    @ColumnInfo(name = "servicio_id") val servicioId: Int,
    @ColumnInfo(name = "user_id") val userId: Int,
)

fun ServicioPendiente.toDatabase() = ServicioPendienteEntity(
    servicioId = servicioId,
    userId = userId
)
