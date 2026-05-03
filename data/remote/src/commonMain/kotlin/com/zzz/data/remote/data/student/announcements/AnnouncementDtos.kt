package com.zzz.data.remote.data.student.announcements

import kotlinx.serialization.Serializable

@Serializable
data class AnnouncementResponse(
    val id : String,
    val title : String,
    val description : String
)