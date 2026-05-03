package com.zzz.data.remote.data.student.announcements

import com.zzz.core.util.domain.Result
import com.zzz.data.remote.domain.ApiResponse
import com.zzz.data.remote.domain.NetworkError
import com.zzz.data.remote.domain.student.announcements.AnnouncementSource
import com.zzz.data.remote.util.constructUrl
import com.zzz.data.remote.util.safeNetworkCall
import com.zzz.data.remote.util.unwrap
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class RemoteAnnouncementSource(
    private val client: HttpClient
) : AnnouncementSource {

    override suspend fun getAnnouncements(): Result<List<AnnouncementResponse> , NetworkError> {
        return safeNetworkCall<ApiResponse<List<AnnouncementResponse>>> {
            val url = constructUrl { "/announcements" }
            client.get(url)
        }.unwrap()
    }
}