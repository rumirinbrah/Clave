package com.zzz.data.remote.domain.student.profile

import com.zzz.core.util.domain.Result
import com.zzz.data.remote.domain.NetworkError
import com.zzz.data.remote.domain.student.experience.dto.ExperienceResponse
import com.zzz.data.remote.domain.student.experience.dto.SaveExperienceRequest
import com.zzz.data.remote.domain.student.profile.dto.SaveStudentProfileRequest
import com.zzz.data.remote.domain.student.profile.dto.StudentProfileResponse

interface ProfileSource {
    suspend fun create(request: SaveStudentProfileRequest) : Result<Unit , NetworkError>

    suspend fun update(id : String, request: SaveStudentProfileRequest) : Result<Unit , NetworkError>

    suspend fun delete(id : String) : Result<Unit , NetworkError>

    suspend fun getById(id : String) : Result<StudentProfileResponse , NetworkError>

    suspend fun get() : Result<StudentProfileResponse , NetworkError>

}