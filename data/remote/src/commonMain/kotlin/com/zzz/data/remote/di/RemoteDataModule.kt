package com.zzz.data.remote.di

import com.zzz.data.remote.HttpClientFactory
import io.ktor.client.HttpClient
import org.koin.core.module.Module
import org.koin.dsl.module

val remoteDataModule = module {
    single<HttpClient> {
        HttpClientFactory.create(
            engine = get()
        )
    }
}

expect val engineModule : Module
