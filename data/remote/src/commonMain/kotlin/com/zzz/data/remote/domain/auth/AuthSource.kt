package com.zzz.data.remote.domain.auth

import com.zzz.data.remote.domain.ApiResponse
import com.zzz.data.remote.domain.auth.dto.CreateAccountRequest
import com.zzz.data.remote.domain.auth.dto.CreateAccountResponse
import com.zzz.data.remote.domain.auth.dto.LoginRequest
import com.zzz.data.remote.domain.auth.dto.LoginResponse
import com.zzz.data.remote.domain.auth.dto.RefreshTokenRequest
import com.zzz.data.remote.domain.model.TokenPair


/**
 * @author zyzz
*/
interface AuthSource {

    fun createAccount(request: CreateAccountRequest) : ApiResponse<CreateAccountResponse>

    fun login(request : LoginRequest) : ApiResponse<LoginResponse>

    fun refreshToken(request : RefreshTokenRequest) : ApiResponse<TokenPair>

}