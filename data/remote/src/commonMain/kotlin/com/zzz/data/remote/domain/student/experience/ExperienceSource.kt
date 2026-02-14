package com.zzz.data.remote.domain.student.experience

import com.zzz.core.util.domain.Result
import com.zzz.data.remote.domain.ApiResponse
import com.zzz.data.remote.domain.NetworkError
import com.zzz.data.remote.domain.student.experience.dto.ExperienceResponse
import com.zzz.data.remote.domain.student.experience.dto.SaveExperienceRequest

/**
 * @author zyzz
*/
interface ExperienceSource {

    suspend fun createExperience(request: SaveExperienceRequest) : Result<Unit, NetworkError>

    suspend fun updateExperience(id : String, request: SaveExperienceRequest) : Result<Unit, NetworkError>

    suspend fun deleteExperience(id : String) : Result<Unit, NetworkError>

    suspend fun getExperienceById(id : String) : Result<ExperienceResponse, NetworkError>

}