package com.zzz.data.remote.util

import com.zzz.core.util.domain.Error
import com.zzz.data.remote.domain.NetworkError
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import com.zzz.core.util.domain.Result

/**
 * Tries to map the HTTP response to the expected type T
 *
 * Also handles HTTP error response codes
 *
 * @author zyzz
*/
suspend inline fun<reified T> responseToResult(
    response : HttpResponse
) : Result<T, NetworkError>{
    return when(response.status.value){
        in 200..299->{
            try {
                Result.Success(response.body<T>())
            } catch (_ : NoTransformationFoundException) {
                Result.Error(NetworkError.SERIALIZATION_ERROR)
            }
        }
        400->{
            Result.Error(NetworkError.BAD_REQUEST)
        }
        401->{
            Result.Error(NetworkError.UNAUTHORIZED)
        }
        403->{
            Result.Error(NetworkError.FORBIDDEN)
        }
        404->{
            Result.Error(NetworkError.NOT_FOUND)
        }
        in 400..499->{
            Result.Error(NetworkError.CLIENT)
        }
        in 500..599->{
            Result.Error(NetworkError.SERVER)
        }
        else->{
            Result.Error(NetworkError.ERROR_UNKNOWN)
        }
    }
}