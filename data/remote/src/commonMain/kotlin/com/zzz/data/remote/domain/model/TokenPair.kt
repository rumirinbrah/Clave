package com.zzz.data.remote.domain.model

import kotlinx.serialization.Serializable

/**
 * JWT token pair
 *
 * @author zyzz
*/
@Serializable
data class TokenPair(
    val accessToken : String,
    val refreshToken : String,
)
