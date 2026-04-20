package com.zzz.feature.auth

fun isValidEmail(email: String): Boolean {
    return email.trim().matches(
        Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$")
    )
}