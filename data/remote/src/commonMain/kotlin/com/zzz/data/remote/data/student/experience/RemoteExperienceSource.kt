package com.zzz.data.remote.data.student.experience

import com.zzz.core.util.domain.Result
import com.zzz.data.remote.domain.NetworkError
import com.zzz.data.remote.domain.student.experience.ExperienceSource
import com.zzz.data.remote.domain.student.experience.dto.ExperienceResponse
import com.zzz.data.remote.domain.student.experience.dto.SaveExperienceRequest
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

internal class RemoteExperienceSource(
    private val client : HttpClient
) : ExperienceSource{

    override suspend fun createExperience(request: SaveExperienceRequest): Result<Unit, NetworkError> {
        return safeNetworkCall<Unit> {
            // /profile/experiences
            val url = constructUrl { ApiRoutes.PROFILE_BASE + ApiRoutes.PROFILE_EXPERIENCE }
            client.post(url){
                setBody(request)
            }
        }
    }

    override suspend fun updateExperience(
        id: String ,
        request: SaveExperienceRequest
    ): Result<Unit , NetworkError> {
        return safeNetworkCall<Unit> {
            val url = constructUrl { ApiRoutes.PROFILE_BASE + ApiRoutes.PROFILE_EXPERIENCE }
            client.put(url){
                setBody(request)
            }
        }
    }

    override suspend fun deleteExperience(id: String): Result<Unit , NetworkError> {
        return safeNetworkCall<Unit> {
            val url = constructUrl { ApiRoutes.PROFILE_BASE + ApiRoutes.PROFILE_EXPERIENCE }

            client.delete(url){
                url{
                    appendPathSegments(id)
                }
            }
        }
    }

    override suspend fun getExperienceById(id: String): Result<ExperienceResponse , NetworkError> {
        return safeNetworkCall<ExperienceResponse> {
            val url = constructUrl { ApiRoutes.PROFILE_BASE + ApiRoutes.PROFILE_EXPERIENCE }
            client.get(url){
                url {
                    appendPathSegments(id)
                }
            }
        }
    }
}