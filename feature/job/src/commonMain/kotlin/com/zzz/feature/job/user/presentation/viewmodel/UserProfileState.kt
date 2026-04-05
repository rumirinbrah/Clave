package com.zzz.feature.job.user.presentation.viewmodel

import com.zzz.data.remote.domain.student.profile.dto.Gender

internal data class UserProfileState(
    val id : String = "",
    val name : String = "",
    val rollNo : String = "",
    val gender: Gender = Gender.OTHERS,
    val year : String = "",
    val branch : String = "",
    val dob : Long = 0L
)
