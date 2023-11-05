package com.gruposami.gruposamiapp.domain.listadevalores.model

import com.gruposami.gruposamiapp.data.database.entities.ListaDeValoresEntity
import com.gruposami.gruposamiapp.data.network.listadevalores.model.ListaDeValoresResponse

data class ListaDeValores(
    var id: Int,
    var entidad: String?,
    var categoria: String?,
    var descripcion: String?,
    var numeracion: Int?,
    var activo: Boolean?,
    var fechaCreacion: String?,
    var fechaModificacion: String?,
)

fun ListaDeValoresResponse.toDomain() = ListaDeValores(
    id,
    entidad,
    categoria,
    descripcion,
    numeracion,
    activo,
    fechaCreacion,
    fechaModificacion
)

fun ListaDeValoresEntity.toDomain() = ListaDeValores(
    id,
    entidad,
    categoria,
    descripcion,
    numeracion,
    activo,
    fechaCreacion,
    fechaModificacion
)