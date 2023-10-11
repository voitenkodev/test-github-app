package com.voitenko.testgithubapp.remote.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.voitenko.testgithubapp.remote.Api
import com.voitenko.testgithubapp.remote.AuthAuthenticator
import com.voitenko.testgithubapp.remote.RemoteDataSource
import com.voitenko.testgithubapp.remote.TokenStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import it.czerwinski.android.hilt.BuildConfig
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthInterceptor

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class UserAuthAuthenticator

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthOkHttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthRetrofit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRemoteDataSource(
        @AuthRetrofit retrofit: Retrofit,
    ): RemoteDataSource {
        return RemoteDataSource(
            retrofit.create(Api::class.java)
        )
    }

    @AuthRetrofit
    @Provides
    @Singleton
    fun provideAuthRetrofit(
        json: Json,
        @AuthOkHttpClient okHttpClient: OkHttpClient,
    ): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(BuildConfig.API_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .client(okHttpClient)
            .build()
    }

    @AuthOkHttpClient
    @Provides
    @Singleton
    fun provideAuthInterceptorOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        @AuthInterceptor authInterceptor: Interceptor,
        @UserAuthAuthenticator authAuthenticator: AuthAuthenticator
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(100, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(authInterceptor)
            .authenticator(authAuthenticator)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        }
    }

    @AuthInterceptor
    @Provides
    @Singleton
    fun provideAuthInterceptor(
        tokenStore: TokenStore,
    ): Interceptor {
        return Interceptor { chain ->
            val token = runBlocking { tokenStore.getAuthToken() }
            val request = chain.request().newBuilder()
            request.addHeader("Authorization", "Bearer $token")
            chain.proceed(request.build())
        }
    }

    @Provides
    @Singleton
    fun provideJson(): Json {
        return Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        }
    }
}