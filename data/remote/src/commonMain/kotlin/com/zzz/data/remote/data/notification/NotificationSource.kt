package com.zzz.data.remote.data.notification
import com.zzz.core.util.domain.Result
import com.zzz.data.remote.domain.NetworkError

interface NotificationSource {

    suspend fun getNotifications(): Result<List<NotificationResponse>, NetworkError>

    suspend fun getNotifCount() : Result<Long, NetworkError>
}