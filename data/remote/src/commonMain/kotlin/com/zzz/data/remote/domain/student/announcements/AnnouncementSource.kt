package com.zzz.data.remote.domain.student.announcements

import com.zzz.core.util.domain.Result
import com.zzz.data.remote.data.student.announcements.AnnouncementResponse
import com.zzz.data.remote.domain.NetworkError

interface AnnouncementSource {

    suspend fun getAnnouncements() : Result<List<AnnouncementResponse>, NetworkError>
}