package com.zzz.data.remote.domain.auth.dto

import kotlinx.serialization.Serializable

/**
 * @author zyzz
*/
@Serializable
data class LoginRequest(
    val rollNumber : String,
    val email : String,
    val password : String,
)