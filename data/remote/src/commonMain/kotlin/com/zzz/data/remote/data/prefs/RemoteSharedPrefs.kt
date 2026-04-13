package com.zzz.data.remote.data.prefs

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

//expect fun createDatastore(
//    createPath : ()->String
//) : DataStore<Preferences>

internal const val datastoreName = "RemoteData.preferences_pb"

class RemoteDatastoreSource(
    private val datastore : DataStore<Preferences>
){
    private val accessTokenKey = stringPreferencesKey("access_token")
    private val refreshTokenKey = stringPreferencesKey("refresh_token")

    private val loggedInKey = booleanPreferencesKey("logged_in")
    private val usernameKey = stringPreferencesKey("username")
    private val rollNoKey = stringPreferencesKey("roll_no")
    private val branchKey = stringPreferencesKey("branch")

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

    suspend fun clearTokens(){
        datastore.edit {
            it.remove(accessTokenKey)
            it.remove(refreshTokenKey)
        }
    }

    suspend fun setLoggedIn(loggedIn : Boolean){
        datastore.edit {prefs->
            prefs[loggedInKey] = loggedIn
        }
    }

    suspend fun getLoggedIn() : Boolean{
        return datastore.data.map {
            it[loggedInKey]
        }.firstOrNull() ?: false
    }

    suspend fun setUsername(
        name : String
    ){
        datastore.edit {prefs->
            prefs[usernameKey] = name
        }
    }

    suspend fun getUsername():String?{
        return datastore.data.map {
            it[usernameKey]
        }.firstOrNull()
    }

    suspend fun setRollNo(rn : String){
        datastore.edit {prefs->
            prefs[rollNoKey] = rn
        }
    }
    suspend fun getRollNo():String?{
        return datastore.data.map {
            it[rollNoKey]
        }.firstOrNull()
    }

    suspend fun setBranch(branch : String){
        datastore.edit {prefs->
            prefs[branchKey] = branch
        }
    }
    suspend fun getBranch():String?{
        return datastore.data.map {
            it[branchKey]
        }.firstOrNull()
    }

}
