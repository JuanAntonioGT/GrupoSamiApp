package com.gruposami.gruposamiapp.domain.firma.model

import com.gruposami.gruposamiapp.data.database.entities.FirmaEntity
import com.gruposami.gruposamiapp.data.network.orden.model.FirmaResponse


data class Firma(
    var id: Int?,
    var tipoFirma: String?,
    var conformidad: Boolean?,
    var estetica: Boolean?,
    var indemnizacion: Boolean?,
    var comentario: String?,
    var multimedia: Int?,
    var fechaCreacion: String?,
    var fechaModificacion: String?,
    var servicioId: Int?
)

fun FirmaEntity.toDomain() = Firma(
    id = id,
    tipoFirma = tipoFirma,
    conformidad = conformidad,
    estetica = estetica,
    indemnizacion = indemnizacion,
    comentario = comentario,
    multimedia = multimedia,
    fechaCreacion = fechaCreacion,
    fechaModificacion = fechaModificacion,
    servicioId = servicioId
)

fun FirmaResponse.toDomain(servicioId: Int) = Firma(
    id = id,
    tipoFirma = tipoFirma,
    conformidad = conformidad,
    estetica = estetica,
    indemnizacion = indemnizacion,
    comentario = comentario,
    multimedia = multimedia,
    fechaCreacion = fechaCreacion,
    fechaModificacion = fechaModificacion,
    servicioId = servicioId,
)