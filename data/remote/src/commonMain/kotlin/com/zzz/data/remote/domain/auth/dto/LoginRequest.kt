package com.zzz.data.remote.domain.auth.dto

/**
 * @author zyzz
*/
data class LoginRequest(
    val rollNumber : String,
    val phoneNumber : String,
    val password : String,
)