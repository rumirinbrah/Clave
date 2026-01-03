package com.zzz.data.remote.domain

import com.zzz.core.util.domain.Error

/**
 * Network errors
 *
 * @author zyzz
*/
enum class NetworkError(val errorMsg : String) : Error{
    ERROR_UNKNOWN(""),
    SERIALIZATION_ERROR(""),

    SERVER(""),
    CLIENT(""),
    NOT_FOUND(""),
    FORBIDDEN(""),
    UNAUTHORIZED(""),
    BAD_REQUEST(""),
}
