package com.gruposami.gruposamiapp.data.di

import com.gruposami.gruposamiapp.domain.sesion.model.Sesion
import com.gruposami.gruposamiapp.data.network.empleado.EmpleadoApiClient
import com.gruposami.gruposamiapp.data.network.formularioservicio.FormularioServicioApiClient
import com.gruposami.gruposamiapp.data.network.listadevalores.ListaDeValoresApiClient
import com.gruposami.gruposamiapp.data.network.login.LoginApiClient
import com.gruposami.gruposamiapp.data.network.multimedia.MultimediaApiClient
import com.gruposami.gruposamiapp.data.network.orden.OrdenApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    // DESARROLLO
//    private const val SERVER_IP = "10.0.2.2"
    private const val SERVER_IP = "192.168.1.150"
    private const val PUERTO = "8000"

    // PRODUCCION
    // private const val SERVER_IP = "servidor001.hopto.org"
    // private const val PUERTO = "80"

    private const val RUTA = "$SERVER_IP:$PUERTO"

    @Singleton
    @Provides
    @Named("Login")
    fun getRetrofitLogin(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://$RUTA/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApiClient(@Named("Login") retrofit: Retrofit): LoginApiClient {
        return retrofit.create(LoginApiClient::class.java)
    }

    @Singleton
    @Provides
    fun providesOkhttpInterceptor(): Interceptor {
        return Interceptor { chain: Interceptor.Chain ->
            val original: Request = chain.request()
            val token = Sesion.token
            val requestBuilder: Request.Builder = original.newBuilder()
                .addHeader("Authorization", "Token $token")
            val request: Request = requestBuilder.build()
            chain.proceed(request)
        }
    }

    @Singleton
    @Provides
    @Named("Token")
    fun getRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(providesOkhttpInterceptor())

            .retryOnConnectionFailure(true)
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .cache(null)
            .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("http://$SERVER_IP:$PUERTO/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideEmpleadoApiClient(@Named("Token") retrofit: Retrofit): EmpleadoApiClient {
        return retrofit.create(EmpleadoApiClient::class.java)
    }

    @Singleton
    @Provides
    fun provideListaDeValoresApiClient(@Named("Token") retrofit: Retrofit): ListaDeValoresApiClient {
        return retrofit.create(ListaDeValoresApiClient::class.java)
    }

    @Singleton
    @Provides
    fun provideOrdenApiClient(@Named("Token") retrofit: Retrofit): OrdenApiClient {
        return retrofit.create(OrdenApiClient::class.java)
    }

    @Singleton
    @Provides
    fun provideFormularioServicioApiClient(@Named("Token") retrofit: Retrofit): FormularioServicioApiClient {
        return retrofit.create(FormularioServicioApiClient::class.java)
    }

    @Singleton
    @Provides
    fun providerMultimediaApiClient(@Named("Token") retrofit: Retrofit): MultimediaApiClient {
        return retrofit.create(MultimediaApiClient::class.java)
    }

}