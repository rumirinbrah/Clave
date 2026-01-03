package com.zzz.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNamingStrategy

/**
 * Creates HTTP Client for both android and IOS
 *
 * @author zyzz
*/
object HttpClientFactory {

    @OptIn(ExperimentalSerializationApi::class)
    fun create(
        engine : HttpClientEngine
    ): HttpClient{
        return HttpClient(engine){
            expectSuccess = false

            //-----------SERIALIZATION-----------
            install(ContentNegotiation){
                json(
                    json= Json{
                        ignoreUnknownKeys = true
//                        this.namingStrategy = JsonNamingStrategy.SnakeCase
                    }
                )
            }

            //-----------LOGGER-----------
            install(HttpTimeout){
                requestTimeoutMillis = 20_000L
                socketTimeoutMillis = 20_000L
            }

            //-----------LOGGER-----------
            install(Logging){
                this.level = LogLevel.ALL
            }
        }
    }
}