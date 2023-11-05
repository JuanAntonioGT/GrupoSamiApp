package com.gruposami.gruposamiapp.domain.servicio.model

import com.gruposami.gruposamiapp.data.database.entities.ServicioCompletoEntity
import com.gruposami.gruposamiapp.domain.empleado.model.Empleado
import com.gruposami.gruposamiapp.domain.empleado.model.toDomain
import com.gruposami.gruposamiapp.domain.estado.model.Estado
import com.gruposami.gruposamiapp.domain.estado.model.toDomain
import com.gruposami.gruposamiapp.domain.firma.model.Firma
import com.gruposami.gruposamiapp.domain.firma.model.toDomain
import com.gruposami.gruposamiapp.domain.multimedia.model.Multimedia
import com.gruposami.gruposamiapp.domain.multimedia.model.toDomain

class ServicioCompleto(
    val servicio: Servicio,
    val estado: List<Estado?>,
    val firma: List<Firma?>,
    val multimedia: List<Multimedia?>,
    val empleadoMedido: List<Empleado?>,
    val empleadoMontado: List<Empleado?>,
    val empleadoPendiente: List<Empleado?>,
)

fun ServicioCompletoEntity.toDomain() = ServicioCompleto(
    servicio = servicioEntity.toDomain(),
    estado = estadoEntity.map { it?.toDomain() },
    firma = firmaEntity.map { it?.toDomain() },
    multimedia = multimediaEntity.map { it?.toDomain() },
    empleadoMedido = empleadoMedido.map { it?.toDomain() },
    empleadoMontado = empleadoMontado.map { it?.toDomain() },
    empleadoPendiente = empleadoPendiente.map { it?.toDomain() },
)



