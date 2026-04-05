package com.zzz.data.remote.data.job.dto

import com.zzz.data.remote.domain.model.EmploymentType
import com.zzz.data.remote.domain.model.Job
import com.zzz.data.remote.domain.model.JobStatus
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.todayIn
import kotlinx.serialization.Serializable
import kotlin.time.ExperimentalTime
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Serializable
data class JobsResponse(
    val jobs : List<JobResponse> = emptyList()
)
@Serializable
data class JobResponse(
    val id: String ,
    val companyName: String ,
    val companyLogoUrl: String? = null ,
    val role: String ,
    val employmentType: EmploymentType ,
    val location: String ,
    val ctc: String ,
    val bondYears: Int? = null ,
    val eligibleCourses: List<String> ,
    val eligibilityCriteria: String ,
    val description: String ,
    val requiredSkills: List<String> = emptyList() ,
    val selectionProcess: String ,
    val lastDateToApply: Long ,
    val applicationLink: String? = null ,
    val jobStatus: JobStatus = JobStatus.OPEN ,
    val lastUpdated: Long
)
fun JobResponse.toJob(): Job {
    return Job(
        id = id,
        companyName = companyName,
        companyLogoUrl = companyLogoUrl,
        role = role,
        employmentType = employmentType,
        location = location,
        ctc = ctc,
        bondYears = bondYears,
        eligibleCourses = eligibleCourses,
        eligibilityCriteria = eligibilityCriteria,
        description = description,
        requiredSkills = requiredSkills,
        selectionProcess = selectionProcess,
        lastDateToApply = lastDateToApply,
        formattedDate = lastDateToApply.toFormattedDate(),
        applicationLink = applicationLink,
        jobStatus = jobStatus,
        lastUpdated = lastUpdated
    )
}
@OptIn(ExperimentalTime::class)
public fun Long.toFormattedDate() : String{
    val instant = Instant.fromEpochMilliseconds(this)
    val localDate = instant.toLocalDateTime(TimeZone.currentSystemDefault())
    return "${localDate.dayOfMonth} ${localDate.month} ${localDate.year}"
}

@OptIn(ExperimentalTime::class)
fun a (){
    val a = Instant.fromEpochMilliseconds(9L)
}
