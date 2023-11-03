package com.gruposami.gruposamiapp.data.di

import android.content.Context
import androidx.room.Room
import com.gruposami.gruposamiapp.data.database.DataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val MOBILE_DATABASE_NAME = "mobile_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, DataBase::class.java, MOBILE_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun providerSesionDao(db: DataBase) = db.getSesionDao()

    @Singleton
    @Provides
    fun providerEmpleadoDao(db: DataBase) = db.getEmpleadoDao()

    @Singleton
    @Provides
    fun providerListaDeValoresDao(db: DataBase) = db.getListaDeValoresDao()

    @Singleton
    @Provides
    fun providerOrdenDao(db: DataBase) = db.getOrdenDao()

    @Singleton
    @Provides
    fun providerClienteDao(db: DataBase) = db.getClienteDao()

    @Singleton
    @Provides
    fun providerDireccionDao(db: DataBase) = db.getDireccionDao()

    @Singleton
    @Provides
    fun providerContactoDao(db: DataBase) = db.getContactoDao()

//    @Singleton
//    @Provides
//    fun providerServicioDao(db: DataBase) = db.getServicioDao()
//
//    @Singleton
//    @Provides
//    fun providerEstadoDao(db: DataBase) = db.getEstadoDao()
//
//    @Singleton
//    @Provides
//    fun providerMultimediaDao(db: DataBase) = db.getMultimediaDao()
//
//    @Singleton
//    @Provides
//    fun providerFirmaDao(db: DataBase) = db.getFirmaDao()
//
//    @Singleton
//    @Provides
//    fun providerFormularioServicioDao(db: DataBase) = db.getFormularioServicioDao()

}
