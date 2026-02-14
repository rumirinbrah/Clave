package com.zzz.data.remote.data.student.resume

import com.zzz.core.util.domain.Result
import com.zzz.data.remote.domain.NetworkError
import com.zzz.data.remote.domain.student.resume.ResumeSource
import com.zzz.data.remote.domain.student.resume.dto.EmbeddedResumeResponse
import com.zzz.data.remote.domain.student.resume.dto.ResumeResponse
import com.zzz.data.remote.domain.student.resume.dto.SaveResumeRequest
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

class ResumeRemoteSource (
    private val client: HttpClient
): ResumeSource {

    override suspend fun createResume(request: SaveResumeRequest): Result<Unit , NetworkError> {
        return safeNetworkCall<Unit> {
            val url = constructUrl { ApiRoutes.PROFILE_BASE + ApiRoutes.PROFILE_RESUME }
            client.post(url){
                setBody(request)
            }
        }
    }

    override suspend fun updateResume(
        id: String ,
        request: SaveResumeRequest
    ): Result<Unit , NetworkError> {
        return safeNetworkCall<Unit> {
            val url = constructUrl { ApiRoutes.PROFILE_BASE + ApiRoutes.PROFILE_RESUME }
            client.put(url){
                setBody(request)
            }
        }
    }

    override suspend fun deleteResume(id: String): Result<Unit , NetworkError> {
        return safeNetworkCall<Unit> {
            val url = constructUrl { ApiRoutes.PROFILE_BASE + ApiRoutes.PROFILE_RESUME }
            client.delete(url){
                url{
                    appendPathSegments(id)
                }
            }
        }
    }

    override suspend fun getResumeById(id: String): Result<ResumeResponse , NetworkError> {
        return safeNetworkCall<ResumeResponse> {
            val url = constructUrl { ApiRoutes.PROFILE_BASE + ApiRoutes.PROFILE_RESUME }
            client.get(url){
                url{
                    appendPathSegments(id)
                }
            }
        }
    }

    override suspend fun getEmbeddedResumeById(id: String): Result<EmbeddedResumeResponse , NetworkError> {
        return safeNetworkCall<EmbeddedResumeResponse> {
            val url = constructUrl { ApiRoutes.PROFILE_BASE + ApiRoutes.PROFILE_RESUME_EMBEDDED }
            client.get(url){
                url{
                    appendPathSegments(id)
                }
            }
        }
    }
}