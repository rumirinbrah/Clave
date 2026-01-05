package com.zzz.data.remote.domain.auth.dto

/**
 * When successful, OTP will be sent
 *
 * @author zyzz
*/
data class CreateAccountResponse(
    val successful : Boolean,
    val errorMessage : String? = null
)