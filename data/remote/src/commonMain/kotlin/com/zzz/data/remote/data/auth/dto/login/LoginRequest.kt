package com.zzz.data.remote.data.auth.dto.login

data class LoginRequest(
    val rollNumber : String,
    val phoneNumber : String,
    val password : String,
)