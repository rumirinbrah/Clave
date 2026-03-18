package com.zzz.data.remote

import com.zzz.data.remote.data.prefs.RemoteDatastoreSource
import com.zzz.data.remote.util.ApiRoutes
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.auth.AuthScheme
import io.ktor.http.encodedPath
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
    internal fun create(
        engine : HttpClientEngine,
        tokenSource : RemoteDatastoreSource
    ): HttpClient{
        val testing = true
        return HttpClient(engine){
            expectSuccess = false

            defaultRequest {
                host = if(testing){
                    "localhost:8080"
                }else{
                    "localhost:8080"
                }
            }

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

            //-----------AUTH HEADER-----------
            install(Auth){
                bearer {
                    loadTokens {
                        val accessToken = tokenSource.getAccessToken() ?: run {
                            return@loadTokens null
                        }
                        val refreshToken = tokenSource.getRefreshToken() ?: run {
                            return@loadTokens null
                        }

                        BearerTokens(accessToken,refreshToken)
                    }
                    //TODO(Refresh logic)
                    /*
                    Request is sent → server returns 401 Unauthorized →
                    try to refresh access token using refresh token →
                    retry original request → if server returns 401 again →
                    treat session as expired, throw exception, redirect user to login
                     */
                    //-----------NO AUTH FOR AUTH ROUTES-----------
                    sendWithoutRequest {
                        val path = it.url.encodedPath
                        path.startsWith(ApiRoutes.AUTH_BASE)
                    }
                }
            }

            //-----------LOGGER-----------
            install(Logging){
                this.level = LogLevel.ALL
            }



        }
    }
}