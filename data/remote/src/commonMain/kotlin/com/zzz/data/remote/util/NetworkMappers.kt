package com.zzz.data.remote.util

import com.zzz.core.util.domain.DomainError
import com.zzz.core.util.domain.Error
import com.zzz.data.remote.domain.NetworkError
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import com.zzz.core.util.domain.Result
import com.zzz.data.remote.domain.ApiResponse

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
                Result.Error(NetworkError.SerializationError)
            }
        }
        400->{
            Result.Error(NetworkError.BadRequest)
        }
        401->{
            Result.Error(NetworkError.Unauthorized)
        }
        403->{
            Result.Error(NetworkError.Forbidden)
        }
        404->{
            Result.Error(NetworkError.NotFound)
        }
        in 400..499->{
            Result.Error(NetworkError.Client)
        }
        in 500..599->{
            Result.Error(NetworkError.Server)
        }
        else->{
            Result.Error(NetworkError.ErrorUnknown)
        }
    }
}

inline fun <T> Result<ApiResponse<T> , NetworkError>.unwrap() : Result<T, NetworkError>{
    return when(this){
        is Result.Error -> {
            this
        }
        is Result.Success -> {
            if(this.data.status){
                if(data.data!=null){
                    Result.Success(data.data!!)
                }else{
                    Result.Success(data = Unit as T)
                }
            }else{
                Result.Error(NetworkError.CustomError(data.error))
            }
        }
    }
}

