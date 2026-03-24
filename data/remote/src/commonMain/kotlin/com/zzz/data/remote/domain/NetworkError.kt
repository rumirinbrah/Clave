package com.zzz.data.remote.domain

import com.zzz.core.util.domain.Error

/**
 * Network errors
 *
 * @author zyzz
*/
sealed class NetworkError(val errorMsg: String) : Error {

    data class CustomError(val msg: String?) : NetworkError(msg ?: "Unknown")

    data object ErrorUnknown : NetworkError("Something went wrong. Please try again.")

    data object SerializationError : NetworkError("Failed to process server response.")

    data object Server : NetworkError("Server error. Please try again later.")

    data object Client : NetworkError("Request error. Please check your input.")

    data object NotFound : NetworkError("Requested resource was not found.")

    data object Forbidden : NetworkError("You don’t have permission to perform this action.")

    data object Unauthorized : NetworkError("Authentication failed. Please log in again.")

    data object BadRequest : NetworkError("Invalid request. Please check your data.")
}
