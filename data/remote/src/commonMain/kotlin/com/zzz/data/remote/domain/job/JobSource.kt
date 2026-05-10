package com.zzz.data.remote.domain.job

import com.zzz.core.util.domain.Result
import com.zzz.data.remote.domain.NetworkError
import com.zzz.data.remote.domain.model.Job

interface JobSource {
    suspend fun getJobs() : Result<List<Job>, NetworkError>
    // job/apply/user-applied
    suspend fun getAppliedJobs() : Result<List<Job>, NetworkError>

    suspend fun getById(id : String) : Result<Job, NetworkError>

    suspend fun getCourses() : Result<Map<Int,String>, NetworkError>

}