package com.zzz.data.remote.domain

import com.zzz.core.util.domain.Error

/**
 * Network errors
 *
 * @author zyzz
*/
//sealed class NetworkError(val errorMsg: String) : Error {
//
//    data class CustomError(val msg: String?) : NetworkError(msg ?: "Unknown")
//
//    data object ErrorUnknown : NetworkError("Something went wrong. Please try again.")
//
//    data object SerializationError : NetworkError("Failed to process server response.")
//
//    data object Server : NetworkError("Server error. Please try again later.")
//
//    data object Client : NetworkError("Request error. Please check your input.")
//
//    data object NotFound : NetworkError("Requested resource was not found.")
//
//    data object Forbidden : NetworkError("You don’t have permission to perform this action.")
//
//    data object Unauthorized : NetworkError("Authentication failed. Please log in again.")
//
//    data object BadRequest : NetworkError("Invalid request. Please check your data.")
//}
sealed class NetworkError() : Error {

    data class CustomError(val msg: String?) : NetworkError()

    data object ErrorUnknown : NetworkError()

    data object SerializationError : NetworkError()

    data object Server : NetworkError()

    data object Client : NetworkError()

    data object NotFound : NetworkError()

    data object Forbidden : NetworkError()

    data object Unauthorized : NetworkError()

    data object BadRequest : NetworkError()

    data object NoInternet : NetworkError()

    data object Timeout : NetworkError()
}
fun NetworkError.toUIError() : String{
    return when(this){
        is NetworkError.CustomError ->
            this.msg ?: "Something went wrong. Please try again."

        NetworkError.BadRequest ->
            "Something wasn’t quite right. Please check your input and try again."

        NetworkError.Client ->
            "We couldn’t process your request. Please try again later."

        NetworkError.ErrorUnknown ->
            "Oops! Something went wrong on our side. Please try again in a moment."

        NetworkError.Forbidden ->
            "You don’t have permission to perform this action."

        NetworkError.NoInternet ->
            "Please check your network and try again."

        NetworkError.NotFound ->
            "We couldn’t find what you’re looking for."

        NetworkError.SerializationError ->
            "We ran into an issue processing the data. Please try again later."

        NetworkError.Server ->
            "Our servers are having trouble right now. Please try again later."

        NetworkError.Timeout ->
            "The request took too long. Please check your connection and try again."

        NetworkError.Unauthorized ->
            "Your session has expired. Please Log In again."
    }
}
