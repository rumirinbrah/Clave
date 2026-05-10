package com.zzz.data.remote.data.job

import com.zzz.core.util.domain.Result
import com.zzz.core.util.domain.map
import com.zzz.data.remote.data.job.dto.JobResponse
import com.zzz.data.remote.data.job.dto.JobsResponse
import com.zzz.data.remote.data.job.dto.toJob
import com.zzz.data.remote.domain.ApiResponse
import com.zzz.data.remote.domain.NetworkError
import com.zzz.data.remote.domain.job.JobSource
import com.zzz.data.remote.domain.model.Job
import com.zzz.data.remote.util.constructUrl
import com.zzz.data.remote.util.safeNetworkCall
import com.zzz.data.remote.util.unwrap
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.http.appendPathSegments

class RemoteJobSource(
    private val client: HttpClient
) : JobSource {
    override suspend fun getJobs(): Result<List<Job> , NetworkError> {
        return safeNetworkCall<ApiResponse<List<JobResponse>>> {
            val url = constructUrl { "job" }
            client.get(url) {

            }
        }.unwrap()
            .map {
                it.map { response ->
                    response.toJob()
                }
            }
    }

    override suspend fun getById(id: String): Result<Job , NetworkError> {
        return safeNetworkCall<ApiResponse<JobResponse>> {
            val url = constructUrl { "job" }
            client.get(url) {
                url {
                    appendPathSegments(id)
                }
            }
        }.unwrap()
            .map {
                it.toJob()
            }
    }

    override suspend fun getCourses(): Result<Map<Int , String> , NetworkError> {
        return safeNetworkCall<ApiResponse<Map<Int , String>>> {
            val url = constructUrl { "job"+"/college-courses" }
            client.get(url) {

            }
        }.unwrap()
    }

    override suspend fun getAppliedJobs(): Result<List<Job> , NetworkError> {
        return safeNetworkCall<ApiResponse<List<JobResponse>>> {
            val url = "/job/apply/user-applied"
            client.get(url)
        }.unwrap()
            .map {
                it.map {job->
                    job.toJob()
                }
            }
    }
}