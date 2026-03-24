package com.zzz.placement.di

import com.zzz.data.remote.di.engineModule
import com.zzz.data.remote.di.remoteDataModule
import com.zzz.feature.auth.di.authModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

/**
 * Start koin
 *
 * @author zyzz
 */
fun initKoin(
    config : KoinAppDeclaration? = null
){
    startKoin {
        config?.invoke(this)
        modules(
            engineModule,
            remoteDataModule,
            authModule
        )
    }
}