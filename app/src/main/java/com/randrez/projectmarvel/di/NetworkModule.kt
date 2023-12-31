package com.randrez.projectmarvel.di

import com.randrez.projectmarvel.BuildConfig
import com.randrez.projectmarvel.data.remote.MarvelApi
import com.randrez.projectmarvel.data.remote.interceptors.MD5Interceptor
import com.randrez.projectmarvel.domain.repository.dataStore.DataStoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideHttpLoginInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG) {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }

    @Singleton
    @Provides
    fun provideGson(): GsonConverterFactory = GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideMD5Interceptor(dataStoreRepository: DataStoreRepository): MD5Interceptor =
        MD5Interceptor(dataStoreRepository = dataStoreRepository)

    @Singleton
    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        mD5Interceptor: MD5Interceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(mD5Interceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(gson: GsonConverterFactory, client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(gson)
            .client(client)
            .baseUrl(BuildConfig.URL_BASE).build()

    @Singleton
    @Provides
    fun provideMarvelApi(retrofit: Retrofit): MarvelApi =
        retrofit.create(MarvelApi::class.java)
}