package com.zzz.data.remote.data.auth.dto.login

import com.zzz.data.remote.domain.model.TokenPair

/**
 * @param verificationRequired If OTP verification is req. In such cases, redirect the user to enter OTP page or ask the user about verification again.
 *
 * @author zyzz
 */
data class LoginResponse(
    val verificationRequired : Boolean = false,
    val tokenPair : TokenPair?
)
