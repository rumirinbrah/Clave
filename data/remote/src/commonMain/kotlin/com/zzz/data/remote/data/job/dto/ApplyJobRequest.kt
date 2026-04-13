package com.zzz.data.remote.data.job.dto

import kotlinx.serialization.Serializable

@Serializable
data class ApplyJobRequest(
    val jobId : String
)