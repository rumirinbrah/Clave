package com.zzz.data.remote.domain.student.profile.dto

import kotlinx.serialization.Serializable

/**
 * Student profile response
 *
 * @author zyzz
*/
@Serializable
data class StudentProfileResponse(
    val id : String,
    val name : String,
    val gender: Gender,
    val rollNumber : String,
    val course : String,
    val year : String,
    val dateOfBirth: Long?,
)

