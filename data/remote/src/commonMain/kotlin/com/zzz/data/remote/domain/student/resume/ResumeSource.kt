package com.zzz.data.remote.domain.student.resume

import com.zzz.core.util.domain.Result
import com.zzz.data.remote.domain.NetworkError
import com.zzz.data.remote.domain.student.resume.dto.EmbeddedResumeResponse
import com.zzz.data.remote.domain.student.resume.dto.ResumeResponse
import com.zzz.data.remote.domain.student.resume.dto.SaveResumeRequest

interface ResumeSource {

    suspend fun createResume(
        request : SaveResumeRequest
    ) : Result<Unit , NetworkError>

    suspend fun updateResume(
        id : String,
        request : SaveResumeRequest
    ) : Result<Unit , NetworkError>

    suspend fun deleteResume(
        id : String,
    ) : Result<Unit , NetworkError>

    /**
     * @param id - Stud ID
     * @author zyzz
    */
    suspend fun getResumeById(
        id : String,
    ) : Result<ResumeResponse , NetworkError>

    /**
     * Get resume having all attrs such as edu, projs, exps, etc.
     *
     * @param id Stud ID
     *
     * @author zyzz
    */
    suspend fun getEmbeddedResumeById(
        id : String,
    ) : Result<EmbeddedResumeResponse, NetworkError>


}