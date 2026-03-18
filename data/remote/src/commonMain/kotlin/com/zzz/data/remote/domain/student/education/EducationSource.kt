package com.zzz.data.remote.domain.student.education

import com.zzz.core.util.domain.Result
import com.zzz.data.remote.domain.NetworkError
import com.zzz.data.remote.domain.student.education.dto.EducationResponse
import com.zzz.data.remote.domain.student.education.dto.SaveEducationRequest

/**
 * @author zyzz
*/
interface EducationSource {

    suspend fun createProject(
        request : SaveEducationRequest
    ) : Result<Unit , NetworkError>

    suspend fun updateProject(
        id : String,
        request : SaveEducationRequest
    ) : Result<Unit , NetworkError>

    suspend fun deleteProject(
        id : String,
        request : SaveEducationRequest
    ) : Result<Unit , NetworkError>

    suspend fun getProject(
        id : String,
    ) : Result<EducationResponse , NetworkError>


}