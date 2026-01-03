package com.zzz.data.remote.domain.model

/**
 * JWT token pair
 *
 * @author zyzz
*/
data class TokenPair(
    val accessToken : String,
    val refreshToken : String,
)
