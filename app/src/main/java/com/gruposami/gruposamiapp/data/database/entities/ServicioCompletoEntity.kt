package com.gruposami.gruposamiapp.data.database.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.gruposami.gruposamiapp.domain.servicio.model.ServicioCompleto


// Clase para traer todos los datos del servicio con el conjunto de otras entidades
data class ServicioCompletoEntity(
    @Embedded val servicioEntity: ServicioEntity,

    // Estado
    @Relation(
        parentColumn = "id_servicio",
        entity = EstadoEntity::class,
        entityColumn = "id_estado",
        associateBy = Junction(
            value = ServicioEstadoEntity::class,
            parentColumn = "servicio_id",
            entityColumn = "estado_id"
        )
    ) val estadoEntity: List<EstadoEntity?>,

    // Firma
    @Relation(
        parentColumn = "id_servicio",
        entityColumn = "servicio_id",
    ) val firmaEntity: List<FirmaEntity?>,

    // Multimedia
    @Relation(
        parentColumn = "id_servicio",
        entityColumn = "servicio_id",
    ) val multimediaEntity: List<MultimediaEntity?>,

    // MedidoPor
    @Relation(
        parentColumn = "id_servicio",
        entity = EmpleadoEntity::class,
        entityColumn = "id_empleado",
        associateBy = Junction(
            value = ServicioMedidoEntity::class,
            parentColumn = "servicio_id",
            entityColumn = "user_id"
        )
    ) val empleadoMedido: List<EmpleadoEntity?>,

    // MontadoPor
    @Relation(
        parentColumn = "id_servicio",
        entity = EmpleadoEntity::class,
        entityColumn = "id_empleado",
        associateBy = Junction(
            value = ServicioMontadoEntity::class,
            parentColumn = "servicio_id",
            entityColumn = "user_id"
        )
    ) val empleadoMontado: List<EmpleadoEntity?>,

    // PendientePor
    @Relation(
        parentColumn = "id_servicio",
        entity = EmpleadoEntity::class,
        entityColumn = "id_empleado",
        associateBy = Junction(
            value = ServicioPendienteEntity::class,
            parentColumn = "servicio_id",
            entityColumn = "user_id"
        )
    ) val empleadoPendiente: List<EmpleadoEntity?>,
)

fun ServicioCompleto.toDatabase() = ServicioCompletoEntity(
    servicioEntity = servicio.toDatabase(),
    estadoEntity = estado.map { it?.toDatabase() },
    firmaEntity = firma.map { it?.toDatabase() },
    multimediaEntity = multimedia.map { it?.toDatabase() },
    empleadoMedido = empleadoMedido.map { it?.toDatabase() },
    empleadoMontado = empleadoMontado.map { it?.toDatabase() },
    empleadoPendiente = empleadoPendiente.map { it?.toDatabase() },
)