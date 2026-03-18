package com.zzz.data.remote.data.student.project

import com.zzz.core.util.domain.Result
import com.zzz.data.remote.domain.NetworkError
import com.zzz.data.remote.domain.student.project.ProjectSource
import com.zzz.data.remote.domain.student.project.dto.ProjectResponse
import com.zzz.data.remote.domain.student.project.dto.SaveProjectRequest
import com.zzz.data.remote.util.ApiRoutes
import com.zzz.data.remote.util.constructUrl
import com.zzz.data.remote.util.safeNetworkCall
import io.ktor.client.HttpClient
import io.ktor.client.request.delete
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.appendPathSegments

class RemoteProjectSource(
    private val client : HttpClient
) : ProjectSource{

    override suspend fun createProject(request: SaveProjectRequest): Result<Unit , NetworkError> {
        return safeNetworkCall<Unit> {
            // /profile/experiences
            val url = constructUrl { ApiRoutes.PROFILE_BASE + ApiRoutes.PROFILE_PROJECT }
            client.post(url){
                setBody(request)
            }
        }
    }

    override suspend fun updateProject(
        id: String ,
        request: SaveProjectRequest
    ): Result<Unit , NetworkError> {
        return safeNetworkCall<Unit> {
            // /profile/experiences
            val url = constructUrl { ApiRoutes.PROFILE_BASE + ApiRoutes.PROFILE_PROJECT }
            client.put(url){
                setBody(request)
            }
        }
    }

    override suspend fun deleteProject(id: String): Result<Unit , NetworkError> {
        return safeNetworkCall<Unit> {
            // /profile/experiences
            val url = constructUrl { ApiRoutes.PROFILE_BASE + ApiRoutes.PROFILE_PROJECT }
            client.delete(url){
                url{
                    appendPathSegments(id)
                }
            }
        }
    }

    override suspend fun getProject(id: String): Result<ProjectResponse , NetworkError> {
        return safeNetworkCall<ProjectResponse> {
            // /profile/experiences
            val url = constructUrl { ApiRoutes.PROFILE_BASE + ApiRoutes.PROFILE_PROJECT }
            client.delete(url){
                url{
                    appendPathSegments(id)
                }
            }
        }
    }
}