package com.zzz.data.remote.util



import com.zzz.data.remote.domain.NetworkError
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import com.zzz.core.util.domain.Result

/**
 * Makes network call and handles exceptions, HTTP errors
 *
 * @author zyzz
 */
suspend inline fun <reified T> safeNetworkCall(
    crossinline block: () -> HttpResponse,
    coroutineContext : CoroutineDispatcher = Dispatchers.IO
) : Result<T , NetworkError> {
    return withContext(coroutineContext){
        val response = try {
            block()
        } catch (e : SerializationException) {
            e.printStackTrace()
            return@withContext Result.Error(NetworkError.SERIALIZATION_ERROR)
        }catch (e : Exception) {
            coroutineContext.ensureActive()
            e.printStackTrace()
            return@withContext Result.Error(NetworkError.ERROR_UNKNOWN)
        }

        return@withContext responseToResult(response)
    }
}
