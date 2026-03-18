package com.zzz.data.remote.data.auth

import com.zzz.core.util.domain.Result
import com.zzz.data.remote.domain.NetworkError
import com.zzz.data.remote.domain.auth.AuthSource
import com.zzz.data.remote.domain.auth.dto.CreateAccountRequest
import com.zzz.data.remote.domain.auth.dto.CreateAccountResponse
import com.zzz.data.remote.domain.auth.dto.LoginRequest
import com.zzz.data.remote.domain.auth.dto.LoginResponse
import com.zzz.data.remote.domain.auth.dto.RefreshTokenRequest
import com.zzz.data.remote.domain.model.TokenPair
import com.zzz.data.remote.util.ApiRoutes
import com.zzz.data.remote.util.constructUrl
import com.zzz.data.remote.util.safeNetworkCall
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody

/**
 * DO NOT MAKE PUBLIC!
 *
 * @author zyzz
*/
internal class RemoteAuthSource(
    private val client : HttpClient
) : AuthSource{

    override suspend fun createAccount(request: CreateAccountRequest): Result<CreateAccountResponse , NetworkError> {
        return safeNetworkCall<CreateAccountResponse> {
            val url = constructUrl { ApiRoutes.AUTH_BASE+ApiRoutes.AUTH_CREATE }
            client.post(url){
                setBody(request)
            }
        }
    }

    override suspend fun login(request: LoginRequest): Result<LoginResponse , NetworkError> {
        return safeNetworkCall<LoginResponse> {
            val url = constructUrl { ApiRoutes.AUTH_BASE+ApiRoutes.AUTH_LOGIN }
            client.post(url){
                setBody(request)
            }
        }
    }

    override suspend fun refreshToken(request: RefreshTokenRequest): Result<TokenPair , NetworkError> {
        return safeNetworkCall<TokenPair> {
            val url = constructUrl { ApiRoutes.AUTH_BASE+ApiRoutes.AUTH_REFRESH_TOKEN }
            client.post(url){
                setBody(request)
            }
        }
    }
}