package com.zzz.data.remote.domain.auth.dto
import kotlinx.serialization.Serializable

/**
 * When successful, OTP will be sent
 *
 * @author zyzz
*/
@Serializable
data class CreateAccountResponse(
    val userId: String,
    val successful : Boolean,
    val errorMessage : String? = null
)