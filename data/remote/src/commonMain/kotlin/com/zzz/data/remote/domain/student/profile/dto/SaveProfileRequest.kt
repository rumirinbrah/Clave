package com.zzz.data.remote.domain.student.profile.dto

import kotlinx.serialization.Serializable

@Serializable
data class SaveStudentProfileRequest(
    val name : String,
    val gender: Gender,
    val rollNumber : String,
    val branch : String,
    val year : String,
    val dateOfBirth: Long
)
enum class Gender{
    MALE,
    FEMALE,
    OTHERS
}