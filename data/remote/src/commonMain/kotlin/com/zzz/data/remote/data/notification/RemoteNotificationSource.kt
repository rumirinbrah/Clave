package com.zzz.data.remote.data.notification

import com.zzz.core.util.domain.map
import com.zzz.data.remote.data.job.dto.toJob
import com.zzz.data.remote.domain.ApiResponse
import io.ktor.client.HttpClient
import com.zzz.data.remote.domain.NetworkError
import com.zzz.data.remote.util.constructUrl
import com.zzz.data.remote.util.safeNetworkCall
import com.zzz.data.remote.util.unwrap
import kotlin.collections.map
import com.zzz.core.util.domain.Result
import io.ktor.client.request.get

class RemoteNotificationSource(
    private val client: HttpClient
) : NotificationSource {

    override suspend fun getNotifications(): Result<List<NotificationResponse>, NetworkError> {

        return safeNetworkCall<ApiResponse<List<NotificationResponse>>> {

            val url = constructUrl { "notifications" }

            client.get(url)

        }.unwrap().map {
            it.map {notificationResponse ->
                notificationResponse
            }
        }
    }
}