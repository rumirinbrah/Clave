package com.zzz.core.ui.util

import platform.Foundation.NSLog

/**
 * IOS logger
 * @author zyzz
*/
actual object ClaveLogger{

    actual fun Any.logD(msg: () -> String) {
        val tag = this::class.simpleName ?: "AppLog"
        NSLog("$tag 🐛 ${msg()}")
    }

    actual fun Any.logE(msg: () -> String , e: Throwable?) {
        val tag = this::class.simpleName ?: "AppLog"
        NSLog("$tag ⚠️ ${msg()}")
    }

    actual fun Any.logI(msg: () -> String) {
        val tag = this::class.simpleName ?: "AppLog"
        NSLog("$tag ℹ️ ${msg()}")
    }

    actual fun Any.logV(msg: () -> String) {
        val tag = this::class.simpleName ?: "AppLog"
        NSLog("$tag ❓ ${msg()}")
    }

}