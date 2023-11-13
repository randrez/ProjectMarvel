package com.randrez.projectmarvel.domain.repository.dataStore

import androidx.datastore.preferences.core.stringPreferencesKey

object ConstantsDataStore {
    const val DATASTORE_NAME = "localStore"
    val LOCAL_MD5 = stringPreferencesKey("localMD5")
}