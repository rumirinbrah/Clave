package com.zzz.data.remote.data.student.profile.dto

import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant


/**
 * May not work as server expects a LocalDate obj for DOB
 *
 * *(Use the same for update requests)*
 *
 * @author zyzz
*/
@OptIn(ExperimentalTime::class)
internal data class CreateProfileRequest(
    val name : String,
    val gender: String,
    val rollNumber : String,
    val branch : String,
    val year : String,
    val dateOfBirth: Instant
)