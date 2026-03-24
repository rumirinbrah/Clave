package com.zzz.data.remote.domain

import kotlinx.serialization.Serializable

/**
 * ### Generic API response
 * @param status Call successful or not i.e. True/false
 * @param data Generic data
 * @param error Error msg
 * @param statusCode Response code
 *
 * @author zyzz
 */
@Serializable
data class ApiResponse<T>(
    val status : Boolean,
    val data : T? = null,
    val error : String? = null,
    val statusCode : Int,
)