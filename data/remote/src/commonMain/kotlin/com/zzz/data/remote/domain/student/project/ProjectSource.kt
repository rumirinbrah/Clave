package com.zzz.data.remote.domain.student.project

import com.zzz.core.util.domain.Result
import com.zzz.data.remote.domain.NetworkError
import com.zzz.data.remote.domain.student.project.dto.ProjectResponse
import com.zzz.data.remote.domain.student.project.dto.SaveProjectRequest

/**
 * @author zyzz
*/
interface ProjectSource {
    suspend fun createProject(
        request : SaveProjectRequest
    ) : Result<Unit, NetworkError>

    suspend fun updateProject(
        id : String,
        request : SaveProjectRequest
    ) : Result<Unit, NetworkError>

    suspend fun deleteProject(
        id: String
    ) : Result<Unit, NetworkError>

    suspend fun getProject(
        id: String
    ) : Result<ProjectResponse, NetworkError>

}