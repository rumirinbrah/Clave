package com.zzz.placement

import android.app.Application
import com.zzz.placement.di.initKoin
import com.zzz.placement.platform.AndroidApplicationComponent
import com.zzz.placement.platform.application
import org.koin.android.ext.koin.androidContext

class ClaveApp : Application() {
    override fun onCreate() {
        super.onCreate()
//        application(AndroidApplicationComponent())
        initKoin {
            androidContext(this@ClaveApp)
        }
    }
}