package com.zzz.data.remote.data.auth

import com.zzz.core.util.domain.Result
import com.zzz.core.util.domain.map
import com.zzz.data.remote.domain.ApiResponse
import com.zzz.data.remote.domain.NetworkError
import com.zzz.data.remote.domain.auth.AuthSource
import com.zzz.data.remote.domain.auth.dto.CreateAccountRequest
import com.zzz.data.remote.domain.auth.dto.CreateAccountResponse
import com.zzz.data.remote.domain.auth.dto.LoginRequest
import com.zzz.data.remote.domain.auth.dto.LoginResponse
import com.zzz.data.remote.domain.auth.dto.RefreshTokenRequest
import com.zzz.data.remote.domain.auth.dto.ResendOtpRequest
import com.zzz.data.remote.domain.auth.dto.ResendOtpResponse
import com.zzz.data.remote.domain.auth.dto.VerifyOtpRequest
import com.zzz.data.remote.domain.model.TokenPair
import com.zzz.data.remote.util.ApiRoutes
import com.zzz.data.remote.util.constructUrl
import com.zzz.data.remote.util.safeNetworkCall
import com.zzz.data.remote.util.unwrap
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

/**
 * DO NOT MAKE PUBLIC!
 *
 * @author zyzz
*/
internal class RemoteAuthSource(
    private val client : HttpClient
) : AuthSource{

    override suspend fun createAccount(request: CreateAccountRequest): Result<CreateAccountResponse , NetworkError> {
        val result = safeNetworkCall<ApiResponse<CreateAccountResponse>> {
            val url = constructUrl { ApiRoutes.AUTH_BASE + ApiRoutes.AUTH_CREATE }
            client.post(url) {
                contentType(ContentType.Application.Json)
                setBody(request)
            }
        }

        return result.unwrap()
    }

    override suspend fun login(request: LoginRequest): Result<LoginResponse , NetworkError> {
        val result = safeNetworkCall<ApiResponse<LoginResponse>> {
            val url = constructUrl { ApiRoutes.AUTH_BASE+ApiRoutes.AUTH_LOGIN }
            client.post(url){
                contentType(ContentType.Application.Json)
                setBody(request)
            }
        }

//        val mapped = result.unwrap()
//        println(mapped)

        return result.unwrap()
    }

    override suspend fun verifyOtp(request: VerifyOtpRequest): Result<Unit , NetworkError> {
        return safeNetworkCall<ApiResponse<Unit>> {
            val url = constructUrl { ApiRoutes.AUTH_BASE+ApiRoutes.AUTH_VERIFY_OTP }
            client.post(url) {
                contentType(ContentType.Application.Json)
                setBody(request)
            }
        }.unwrap()
    }

    override suspend fun resendOtp(request: ResendOtpRequest): Result<ResendOtpResponse , NetworkError> {
        return safeNetworkCall<ApiResponse<ResendOtpResponse>> {
            val url = constructUrl { ApiRoutes.AUTH_BASE+ApiRoutes.AUTH_RESEND_OTP }
            client.post(url) {
                contentType(ContentType.Application.Json)
                setBody(request)
            }
        }.unwrap()
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