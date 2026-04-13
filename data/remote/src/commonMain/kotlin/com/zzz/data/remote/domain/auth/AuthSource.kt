package com.zzz.data.remote.domain.auth

import com.zzz.core.util.domain.Result
import com.zzz.data.remote.domain.ApiResponse
import com.zzz.data.remote.domain.NetworkError
import com.zzz.data.remote.domain.auth.dto.CreateAccountRequest
import com.zzz.data.remote.domain.auth.dto.CreateAccountResponse
import com.zzz.data.remote.domain.auth.dto.LoginRequest
import com.zzz.data.remote.domain.auth.dto.LoginResponse
import com.zzz.data.remote.domain.auth.dto.RefreshTokenRequest
import com.zzz.data.remote.domain.auth.dto.ResendOtpRequest
import com.zzz.data.remote.domain.auth.dto.ResendOtpResponse
import com.zzz.data.remote.domain.auth.dto.VerifyOtpRequest
import com.zzz.data.remote.domain.model.TokenPair


/**
 * @author zyzz
*/
interface AuthSource {

    suspend fun createAccount(request: CreateAccountRequest) : Result<Unit, NetworkError>

    /**
     * 2 min expiry
     * @author zyzz
    */
    suspend fun verifyOtp(request : VerifyOtpRequest) : Result<Unit, NetworkError>

    /**
     * 30 sec cooldown
     *
     * @author zyzz
    */
    suspend fun resendOtp(request : ResendOtpRequest) : Result<ResendOtpResponse, NetworkError>

    suspend fun login(request : LoginRequest) : Result<LoginResponse , NetworkError>

    suspend fun refreshToken(request : RefreshTokenRequest) : Result<TokenPair, NetworkError>

}