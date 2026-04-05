package com.zzz.data.remote.data.student.profile

import com.zzz.core.util.domain.Result
import com.zzz.core.util.domain.map
import com.zzz.data.remote.domain.ApiResponse
import com.zzz.data.remote.domain.NetworkError
import com.zzz.data.remote.domain.student.profile.ProfileSource
import com.zzz.data.remote.domain.student.profile.dto.SaveStudentProfileRequest
import com.zzz.data.remote.domain.student.profile.dto.StudentProfileResponse
import com.zzz.data.remote.util.ApiRoutes
import com.zzz.data.remote.util.constructUrl
import com.zzz.data.remote.util.safeNetworkCall
import com.zzz.data.remote.util.unwrap
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class RemoteProfileSource(
    private val client: HttpClient
) : ProfileSource {

    override suspend fun create(request: SaveStudentProfileRequest): Result<Unit , NetworkError> {
        return safeNetworkCall<ApiResponse<Unit>> {
            val url = constructUrl { ApiRoutes.PROFILE_BASE + ApiRoutes.PROFILE_STUDENT }
            client.post(url) {
                setBody(request)
            }
        }.map {
            it.data
        }
    }

    override suspend fun update(
        id: String ,
        request: SaveStudentProfileRequest
    ): Result<Unit , NetworkError> {
        return safeNetworkCall<ApiResponse<Unit>> {
            val url = constructUrl { ApiRoutes.PROFILE_BASE + ApiRoutes.PROFILE_STUDENT }
            client.put("$url/$id"){
                contentType(ContentType.Application.Json)
                setBody(request)
            }
        }.map { it.data }
    }

    override suspend fun delete(id: String): Result<Unit , NetworkError> {
        TODO()
    }

    override suspend fun getById(id: String): Result<StudentProfileResponse , NetworkError> {
        TODO()
    }

    override suspend fun get(): Result<StudentProfileResponse , NetworkError> {
        return safeNetworkCall<ApiResponse<StudentProfileResponse>> {
            val url = constructUrl { ApiRoutes.PROFILE_BASE + ApiRoutes.PROFILE_STUDENT }
            client.get(url) {

            }
        }.unwrap()
    }
}