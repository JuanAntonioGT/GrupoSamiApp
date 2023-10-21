package com.gruposami.gruposamiapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gruposami.gruposamiapp.data.database.dao.EmpleadoDao
import com.gruposami.gruposamiapp.data.database.dao.SesionDao
import com.gruposami.gruposamiapp.data.database.entities.EmpleadoEntity
import com.gruposami.gruposamiapp.data.database.entities.SesionEntity

@Database(entities = [
        SesionEntity::class,
        EmpleadoEntity::class,
//        ListaDeValoresEntity::class,
//        OrdenEntity::class,
//        ClienteEntity::class,
//        DireccionEntity::class,
//        ContactoEntity::class,
//        ServicioEntity::class,
//        EstadoEntity::class,
//        MultimediaEntity::class,
//        FirmaEntity::class,
//        OrdenEstadoEntity::class,
////        ServicioEstadoEntity::class,
//        ServicioMedidoEntity::class,
//        ServicioMontadoEntity::class,
//        ServicioPendienteEntity::class,
//        FormularioServicioEntity::class,
//        FormularioServicioRelacionesEntity::class,
    ], version = 1,
    exportSchema = false
)
abstract class DataBase : RoomDatabase() {
    abstract fun getSesionDao(): SesionDao
    abstract fun getEmpleadoDao(): EmpleadoDao
//    abstract fun getListaDeValoresDao(): ListaDeValoresDao
//    abstract fun getOrdenDao(): OrdenDao
//    abstract fun getClienteDao(): ClienteDao
//    abstract fun getDireccionDao(): DireccionDao
//    abstract fun getContactoDao(): ContactoDao
//    abstract fun getServicioDao(): ServicioDao
//    abstract fun getEstadoDao(): EstadoDao
//    abstract fun getMultimediaDao(): MultimediaDao
//    abstract fun getFirmaDao(): FirmaDao
//    abstract fun getFormularioServicioDao(): FormularioServicioDao
}