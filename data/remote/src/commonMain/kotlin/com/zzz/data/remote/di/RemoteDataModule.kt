package com.zzz.data.remote.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import com.zzz.data.remote.HttpClientFactory
import com.zzz.data.remote.data.auth.RemoteAuthSource
import com.zzz.data.remote.data.prefs.RemoteDatastoreSource
import com.zzz.data.remote.data.prefs.datastoreName
import com.zzz.data.remote.domain.auth.AuthSource
import io.ktor.client.HttpClient
import org.koin.core.module.Module
import org.koin.dsl.module
import kotlin.math.sin

val remoteDataModule = module {
    single<HttpClient> {
        HttpClientFactory.create(
            engine = get(),
            tokenSource = get()
        )
    }
    single<RemoteDatastoreSource> {
        RemoteDatastoreSource(
            datastore = get()
        )
    }
    //--------AUTH--------
    single<AuthSource> {
        RemoteAuthSource(
            client = get()
        )
    }

}

expect val engineModule : Module
