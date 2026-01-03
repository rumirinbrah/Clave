package com.zzz.data.remote.data.auth.dto.create

/**
 * When successful, OTP will be sent
 *
 * @author zyzz
*/
data class CreateAccountResponse(
    val successful : Boolean,
    val errorMessage : String? = null
)