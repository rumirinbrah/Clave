package com.zzz.data.remote.data.student.education

import com.zzz.core.util.domain.Result
import com.zzz.data.remote.domain.NetworkError
import com.zzz.data.remote.domain.student.education.EducationSource
import com.zzz.data.remote.domain.student.education.dto.EducationResponse
import com.zzz.data.remote.domain.student.education.dto.SaveEducationRequest
import com.zzz.data.remote.util.ApiRoutes
import com.zzz.data.remote.util.constructUrl
import com.zzz.data.remote.util.safeNetworkCall
import io.ktor.client.HttpClient
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.appendPathSegments

class EducationRemoteSource(
    private val client : HttpClient
) : EducationSource {

    override suspend fun createProject(request: SaveEducationRequest): Result<Unit , NetworkError> {
        return safeNetworkCall<Unit> {
            val url = constructUrl { ApiRoutes.PROFILE_BASE + ApiRoutes.PROFILE_EDUCATIONS }
            client.post(url){
                setBody(request)
            }
        }
    }

    override suspend fun updateProject(
        id: String ,
        request: SaveEducationRequest
    ): Result<Unit , NetworkError> {
        return safeNetworkCall<Unit> {
            val url = constructUrl { ApiRoutes.PROFILE_BASE + ApiRoutes.PROFILE_EDUCATIONS }
            client.put(url){
                setBody(request)
            }
        }
    }

    override suspend fun deleteProject(
        id: String ,
        request: SaveEducationRequest
    ): Result<Unit , NetworkError> {
        return safeNetworkCall<Unit> {
            val url = constructUrl { ApiRoutes.PROFILE_BASE + ApiRoutes.PROFILE_EDUCATIONS }
            client.delete(url){
                url{
                    appendPathSegments(id)
                }
            }
        }
    }

    override suspend fun getProject(id: String): Result<EducationResponse , NetworkError> {
        return safeNetworkCall<EducationResponse> {
            val url = constructUrl { ApiRoutes.PROFILE_BASE + ApiRoutes.PROFILE_EDUCATIONS }
            client.get(url){
                url{
                    appendPathSegments(id)
                }
            }
        }
    }
}