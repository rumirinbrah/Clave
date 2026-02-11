package com.zzz.data.remote.domain.auth.dto

/**
 * If even refresh token is expired, Unauthorized status will be returned.
 *
 * @author zyzz
*/
data class RefreshTokenRequest(
    val refreshToken : String
)