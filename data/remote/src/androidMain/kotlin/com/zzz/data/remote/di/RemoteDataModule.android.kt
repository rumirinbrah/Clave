package com.zzz.data.remote.di

import android.os.Build
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import com.zzz.data.remote.data.prefs.datastoreName
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import okio.Path.Companion.toPath
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual val engineModule: Module
    get() = module {
        single<HttpClientEngine> {
            OkHttp.create()
        }
        single<DataStore<Preferences>> {
            val context = androidContext()
            PreferenceDataStoreFactory.createWithPath(
                produceFile = {
                    context.filesDir.resolve(datastoreName).absolutePath.toPath()
                }
            )
        }
    }