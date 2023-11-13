package com.randrez.projectmarvel.domain.useCase

import com.randrez.projectmarvel.BuildConfig
import com.randrez.projectmarvel.domain.repository.dataStore.DataStoreRepository
import com.randrez.projectmarvel.tools.Utils.calculateMd5

class GenerateMD5UseCase constructor(
    private val dataStoreRepository: DataStoreRepository
) {
    suspend operator fun invoke() {
        val md5 =
            calculateMd5("${BuildConfig.TS}${BuildConfig.PRIVATE_API_KEY}${BuildConfig.PUBLIC_API_KEY}")
        dataStoreRepository.saveDataStoreMD5(md5 = md5)
    }
}