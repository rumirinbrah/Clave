package com.zzz.data.remote.domain.auth.dto

import kotlinx.serialization.Serializable

/**
 * Create acc
 *
 * @author zyzz
*/
@Serializable
data class CreateAccountRequest(
    val rollNumber : String,
    val password : String
)