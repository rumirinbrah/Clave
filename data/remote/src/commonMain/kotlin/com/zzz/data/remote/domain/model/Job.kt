package com.zzz.data.remote.domain.model

//Temporary Job entity TODO - update it later according to backend
data class Job(
    val id: String,
    val companyName: String,
    val companyLogoUrl: String,
    val role: String,
    val employmentType: EmploymentType,
    val location: String,
    val ctc: String,
    val bondYears: Int? = null,
    val eligibleCourses: List<String>,
    val eligibilityCriteria: String,
    val description: String,
    val requiredSkills: List<String> = emptyList(),
    val selectionProcess: String,
    val lastDateToApply: String,
    val applicationLink: String? = null,
    val isApplied: Boolean = false,
    val isBookmarked: Boolean = false,
    val jobStatus: JobStatus = JobStatus.OPEN
)


enum class EmploymentType {
    FULL_TIME,
    INTERNSHIP,
    PPO
}

enum class JobStatus {
    OPEN,
    CLOSED,
    UPCOMING
}