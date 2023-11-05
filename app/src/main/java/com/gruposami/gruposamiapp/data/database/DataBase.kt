package com.gruposami.gruposamiapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gruposami.gruposamiapp.data.database.dao.ClienteDao
import com.gruposami.gruposamiapp.data.database.dao.ContactoDao
import com.gruposami.gruposamiapp.data.database.dao.DireccionDao
import com.gruposami.gruposamiapp.data.database.dao.EmpleadoDao
import com.gruposami.gruposamiapp.data.database.dao.EstadoDao
import com.gruposami.gruposamiapp.data.database.dao.FirmaDao
import com.gruposami.gruposamiapp.data.database.dao.FormularioServicioDao
import com.gruposami.gruposamiapp.data.database.dao.ListaDeValoresDao
import com.gruposami.gruposamiapp.data.database.dao.MultimediaDao
import com.gruposami.gruposamiapp.data.database.dao.OrdenDao
import com.gruposami.gruposamiapp.data.database.dao.ServicioDao
import com.gruposami.gruposamiapp.data.database.dao.SesionDao
import com.gruposami.gruposamiapp.data.database.entities.ClienteEntity
import com.gruposami.gruposamiapp.data.database.entities.ContactoEntity
import com.gruposami.gruposamiapp.data.database.entities.DireccionEntity
import com.gruposami.gruposamiapp.data.database.entities.EmpleadoEntity
import com.gruposami.gruposamiapp.data.database.entities.EstadoEntity
import com.gruposami.gruposamiapp.data.database.entities.FirmaEntity
import com.gruposami.gruposamiapp.data.database.entities.FormularioServicioEntity
import com.gruposami.gruposamiapp.data.database.entities.FormularioServicioRelacionesEntity
import com.gruposami.gruposamiapp.data.database.entities.ListaDeValoresEntity
import com.gruposami.gruposamiapp.data.database.entities.MultimediaEntity
import com.gruposami.gruposamiapp.data.database.entities.OrdenEntity
import com.gruposami.gruposamiapp.data.database.entities.OrdenEstadoEntity
import com.gruposami.gruposamiapp.data.database.entities.ServicioEntity
import com.gruposami.gruposamiapp.data.database.entities.ServicioEstadoEntity
import com.gruposami.gruposamiapp.data.database.entities.ServicioMedidoEntity
import com.gruposami.gruposamiapp.data.database.entities.ServicioMontadoEntity
import com.gruposami.gruposamiapp.data.database.entities.ServicioPendienteEntity
import com.gruposami.gruposamiapp.data.database.entities.SesionEntity


@Database(
    entities = [
        SesionEntity::class,
        EmpleadoEntity::class,
        ListaDeValoresEntity::class,
        FormularioServicioEntity::class,
        FormularioServicioRelacionesEntity::class,
        OrdenEntity::class,
        ClienteEntity::class,
        DireccionEntity::class,
        ContactoEntity::class,
        EstadoEntity::class,
        OrdenEstadoEntity::class,
        ServicioEntity::class,
        MultimediaEntity::class,
        FirmaEntity::class,
        ServicioEstadoEntity::class,
        ServicioMedidoEntity::class,
        ServicioMontadoEntity::class,
        ServicioPendienteEntity::class,
    ], version = 1,
    exportSchema = false
)
abstract class DataBase : RoomDatabase() {
    abstract fun getSesionDao(): SesionDao
    abstract fun getEmpleadoDao(): EmpleadoDao
    abstract fun getListaDeValoresDao(): ListaDeValoresDao
    abstract fun getOrdenDao(): OrdenDao
    abstract fun getClienteDao(): ClienteDao
    abstract fun getDireccionDao(): DireccionDao
    abstract fun getContactoDao(): ContactoDao
    abstract fun getServicioDao(): ServicioDao
    abstract fun getEstadoDao(): EstadoDao
    abstract fun getMultimediaDao(): MultimediaDao
    abstract fun getFirmaDao(): FirmaDao
    abstract fun getFormularioServicioDao(): FormularioServicioDao
}
