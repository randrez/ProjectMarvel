package com.randrez.projectmarvel.domain.repository.dataStore

import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    suspend fun saveDataStoreMD5(md5:String)
    suspend fun getDataStoreMD5(): String
}