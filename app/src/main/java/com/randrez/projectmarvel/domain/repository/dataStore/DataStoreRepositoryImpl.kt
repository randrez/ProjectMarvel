package com.randrez.projectmarvel.domain.repository.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.randrez.projectmarvel.domain.repository.dataStore.ConstantsDataStore.DATASTORE_NAME
import com.randrez.projectmarvel.domain.repository.dataStore.ConstantsDataStore.LOCAL_MD5
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map


val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = DATASTORE_NAME)

class DataStoreRepositoryImpl(context: Context) : DataStoreRepository {

    private val dataStore = context.datastore

    override suspend fun saveDataStoreMD5(md5: String) {
        dataStore.edit { data ->
            data[LOCAL_MD5] = md5
        }
    }

    override suspend fun getDataStoreMD5(): String =
        dataStore.data.map { it[LOCAL_MD5] ?: "" }.first()
}