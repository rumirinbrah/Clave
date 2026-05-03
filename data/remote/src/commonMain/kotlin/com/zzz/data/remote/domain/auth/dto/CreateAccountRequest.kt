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
    val email : String,
    val name : String,
    val courseId : Int,
    val password : String,
    val isAdmin : Boolean = false
)