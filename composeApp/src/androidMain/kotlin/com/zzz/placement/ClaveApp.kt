package com.zzz.placement

import android.app.Application
import com.zzz.placement.platform.AndroidApplicationComponent
import com.zzz.placement.platform.application

class ClaveApp : Application() {
    override fun onCreate() {
        super.onCreate()
//        application(AndroidApplicationComponent())
    }
}