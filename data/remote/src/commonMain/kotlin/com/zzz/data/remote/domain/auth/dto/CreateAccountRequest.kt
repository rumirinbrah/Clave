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
    val phoneNumber : String,
    val password : String,
    val isAdmin : Boolean = false
)