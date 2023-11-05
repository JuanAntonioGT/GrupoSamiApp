package com.gruposami.gruposamiapp.data.database.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.gruposami.gruposamiapp.domain.orden.model.OrdenCompleta


data class OrdenCompletaEntity(
    @Embedded val ordenEntity: OrdenEntity?,

    @Relation(
        parentColumn = "cliente_id",
        entityColumn = "id_cliente"
    ) val clienteEntity: ClienteEntity?,

    @Relation(
        parentColumn = "cliente_id",
        entityColumn = "cliente_id",
        entity = DireccionEntity::class,
    ) val direccionEntity: List<DireccionEntity?>,

    @Relation(
        parentColumn = "cliente_id",
        entityColumn = "cliente_id",
    ) val contactoEntity: List<ContactoEntity?>,

    // Estado Entity
    @Relation(
        parentColumn = "id_orden",
        entity = EstadoEntity::class,
        entityColumn = "id_estado",
        associateBy = Junction(
            value = OrdenEstadoEntity::class, parentColumn = "orden_id", entityColumn = "estado_id"
        )
    ) val estadoEntity: List<EstadoEntity?>,

    // Servicio Entity
    @Relation(
        entity = ServicioEntity::class,
        parentColumn = "id_orden",
        entityColumn = "orden_id",
    ) val servicioCompletoEntity: List<ServicioCompletoEntity?>,
)

fun OrdenCompleta.toDatabase() = OrdenCompletaEntity(
    ordenEntity = orden?.toDatabase(),
    clienteEntity = cliente?.toDatabase(),
    direccionEntity = direccion?.map { it?.toDatabase() } ?: emptyList(),
    contactoEntity = contacto?.map { it?.toDatabase() } ?: emptyList(),
    estadoEntity =  estado?.map { it?.toDatabase() } ?: emptyList(),
    servicioCompletoEntity = servicioCompleto.map { it?.toDatabase() }
)