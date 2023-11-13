package com.randrez.projectmarvel.data.remote.interceptors

import com.randrez.projectmarvel.BuildConfig
import com.randrez.projectmarvel.domain.repository.dataStore.DataStoreRepository
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class MD5Interceptor @Inject constructor(private val dataStoreRepository: DataStoreRepository) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalHttpUrl = originalRequest.url

        val md5Value = runBlocking {
            dataStoreRepository.getDataStoreMD5()
        }

        val urlWithMd5 = originalHttpUrl.newBuilder()
            .addQueryParameter("ts", BuildConfig.TS)
            .addQueryParameter("apikey", BuildConfig.PUBLIC_API_KEY)
            .addQueryParameter("hash", md5Value)
            .build()

        val requestWithMd5 = originalRequest.newBuilder()
            .url(urlWithMd5)
            .build()

        return chain.proceed(requestWithMd5)
    }
}

