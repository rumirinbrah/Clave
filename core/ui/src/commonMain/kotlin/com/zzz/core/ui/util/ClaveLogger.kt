package com.zzz.core.ui.util

const val loggingEnabled = true

/**
 * Clave logger
 * @author zyzz
*/
expect object ClaveLogger{

    /**
     * Debug log
     * @author zyzz
    */
    fun Any.logD(msg : ()->String)

    /**
     * Error log
     * @author zyzz
    */
    fun Any.logE(
        e: Throwable? = null,
        msg: () -> String ,
    )

    /**
     * Information log
     * @author zyzz
    */
    fun Any.logI(msg: () -> String)

    /**
     * Verbose log
     * @author zyzz
    */
    fun Any.logV(msg: () -> String)

}

