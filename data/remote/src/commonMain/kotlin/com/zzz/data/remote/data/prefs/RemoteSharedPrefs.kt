package com.zzz.data.remote.data.prefs

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

//expect fun createDatastore(
//    createPath : ()->String
//) : DataStore<Preferences>

internal const val datastoreName = "RemoteData.preferences_pb"

internal class RemoteDatastoreSource(
    private val datastore : DataStore<Preferences>
){
    private val accessTokenKey = stringPreferencesKey("access_token")
    private val refreshTokenKey = stringPreferencesKey("refresh_token")

    suspend fun saveTokens(
        access : String,
        refresh : String,
    ){
        datastore.edit {prefs->
            prefs[accessTokenKey] = access
            prefs[refreshTokenKey] = refresh
        }
    }
    suspend fun getAccessToken() : String?{
        return datastore.data
            .map {
                it[accessTokenKey]
            }.firstOrNull()
    }

    suspend fun getRefreshToken() : String?{
        return datastore.data
            .map {
                it[refreshTokenKey]
            }.firstOrNull()
    }

}
