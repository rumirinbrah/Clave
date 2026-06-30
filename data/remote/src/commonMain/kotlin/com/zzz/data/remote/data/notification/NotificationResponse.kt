package com.zzz.data.remote.data.notification

import kotlinx.serialization.Serializable

@Serializable
data class NotificationResponse(
    val id: String,
    val title: String,
    val message: String,
    val jobId: String?,
    val createdAt: Long,
    val isRead: Boolean
)