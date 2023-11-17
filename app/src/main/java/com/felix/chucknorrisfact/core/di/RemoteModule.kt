package com.felix.chucknorrisfact.core.di

import com.felix.chucknorrisfact.core.data.remote.ApiService
import com.google.android.datatransport.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    private const val TIMEOUT = 15L

    @Provides
    fun provideLoggingInterceptor() = HttpLoggingInterceptor().apply {
        setLevel(
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        )
    }

    @Provides
    fun provideOkhttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ) = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT, TimeUnit.SECONDS)
        .build()

    @Provides
    fun provideGsonConverterFactory() = GsonConverterFactory.create()

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ) = Retrofit
        .Builder()
        .baseUrl("https://api.chucknorris.io/jokes/")
        .client(okHttpClient)
        .addConverterFactory(gsonConverterFactory)
        .build()

    @Provides
    fun provideApiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)

}