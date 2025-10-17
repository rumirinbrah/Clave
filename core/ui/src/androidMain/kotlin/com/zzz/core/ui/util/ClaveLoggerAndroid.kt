package com.zzz.core.ui.util

import android.util.Log

/**
 * Android logger
 * @author zyzz
*/
actual object ClaveLogger {

    actual fun Any.logD(msg: () -> String) {
        if (loggingEnabled) {
            val tag = this::class.simpleName ?: "AppLog"
            Log.d(tag , "🐛 ${msg()}")
        }
    }

    actual fun Any.logI(msg: () -> String) {
        val tag = this::class.simpleName ?: "AppLog"
        Log.i(tag , "ℹ️ ${msg()}")
    }

    actual fun Any.logV(msg: () -> String) {
        val tag = this::class.simpleName ?: "AppLog"
        Log.v(tag , "❓ ${msg()}")
    }

    actual fun Any.logE(msg: () -> String , e: Throwable?) {
        val tag = this::class.simpleName ?: "AppLog"
        Log.e(tag , "⚠️ ${msg()}" , e)
    }

}