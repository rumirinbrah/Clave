package com.zzz.data.remote.domain.student.experience.dto

data class SaveExperienceRequest(
    val companyName : String,
    val location : String,
    val role : String,
    val roleDescription : String,
    val jobType : String,
    val employmentType : String,
    val currentlyWorkingHere : Boolean,
    val startDate : Long,
    val endDate : Long?,
    val resumeId : String
)