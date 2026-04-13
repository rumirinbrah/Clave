package com.zzz.data.remote.data.job

import com.zzz.core.util.domain.Result
import com.zzz.data.remote.data.job.dto.ApplyJobRequest
import com.zzz.data.remote.domain.ApiResponse
import com.zzz.data.remote.domain.NetworkError
import com.zzz.data.remote.domain.job.JobApplicationSource
import com.zzz.data.remote.util.ApiRoutes
import com.zzz.data.remote.util.constructUrl
import com.zzz.data.remote.util.safeNetworkCall
import com.zzz.data.remote.util.unwrap
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class RemoteJobApplicationSource(
    private val client: HttpClient
) : JobApplicationSource {
    override suspend fun apply(request: ApplyJobRequest): Result<Unit , NetworkError> {
        return safeNetworkCall<ApiResponse<Unit>> {
            client.post {
                val url = constructUrl { ApiRoutes.APPLY_JOB }
                client.post(url){
                    setBody(request)
                    contentType(ContentType.Application.Json)
                }
            }
        }.unwrap()
    }
}