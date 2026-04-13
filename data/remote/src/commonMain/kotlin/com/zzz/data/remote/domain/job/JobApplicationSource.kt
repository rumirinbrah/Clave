package com.zzz.data.remote.domain.job

import com.zzz.core.util.domain.Result
import com.zzz.data.remote.data.job.dto.ApplyJobRequest
import com.zzz.data.remote.domain.NetworkError

interface JobApplicationSource {
    suspend fun apply(request: ApplyJobRequest) : Result<Unit, NetworkError>
}