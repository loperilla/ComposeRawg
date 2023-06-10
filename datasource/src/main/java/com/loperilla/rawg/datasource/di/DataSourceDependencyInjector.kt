package com.loperilla.rawg.datasource.di

import android.content.Context
import android.util.Log
import com.loperilla.rawg.datasource.network.NetworkInterceptor
import com.loperilla.rawg.datasource.network.api.GameApi
import com.loperilla.rawg.datasource.network.api.GameGenreApi
import com.loperilla.rawg.datasource.network.impl.GameApiImpl
import com.loperilla.rawg.datasource.network.impl.GenreApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.statement.request
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceDependencyInjector {

    @Provides
    @Singleton
    fun provideGenreApi(
        httpClient: HttpClient,
        json: Json
    ): GameGenreApi = GenreApiImpl(httpClient, json)

    @Provides
    @Singleton
    fun provideGameApi(
        httpClient: HttpClient,
        json: Json
    ): GameApi = GameApiImpl(httpClient, json)

//    @Provides
//    @Singleton
//    fun provideCreatorsApi(
//        httpClient: HttpClient,
//        json: Json
//    ): CreatorsApi = CreatorsApiImpl(
//        httpClient, json
//    )

    @Provides
    @Singleton
    fun provideHttpClient(networkInterceptor: NetworkInterceptor, json: Json): HttpClient {
        return HttpClient(OkHttp) {
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.v("Logger Ktor =>", message)
                    }
                }
                level = LogLevel.INFO
            }

            install(ResponseObserver) {
                onResponse { response ->
                    Log.d("HTTP status:", "${response.status.value}")
                    Log.d("HTTP requestTime:", "${response.requestTime}")
                    Log.d("HTTP responseTime:", "${response.responseTime}")
                    Log.d("HTTP url:", "${response.request.url}")
                    Log.d("HTTP value:", "${response.status.value}")
                }
            }

            install(HttpTimeout) {
                requestTimeoutMillis = 10000
            }

            install(ContentNegotiation) {
                json(
                    json
                )
                engine {
                    addInterceptor { chain: Interceptor.Chain ->
                        chain.request().let {
                            chain.proceed(it)
                        }
                    }

                    addNetworkInterceptor(networkInterceptor)
                }
            }
        }
    }

    @Singleton
    @Provides
    fun provideNetworkInterceptor(context: Context) = NetworkInterceptor(context)

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    fun providesJson() = Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
            explicitNulls = true
        }

    @Provides
    @Singleton
    fun provideApplicationContext(
        @ApplicationContext context: Context
    ): Context = context
}
