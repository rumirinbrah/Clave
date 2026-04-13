package com.zzz.data.remote.domain.auth.dto

import kotlinx.serialization.Serializable

@Serializable
data class VerifyOtpRequest(
    val email : String,
    val otp : String
)

@Serializable
data class ResendOtpRequest(
    val email : String
)
/**
 * @param message Whether resent or not
 * @author zyzz
 */
@Serializable
data class ResendOtpResponse(
    val message : String
)