package com.zzz.feature.job.home.presentation.viewmodel

import com.zzz.data.remote.data.student.announcements.AnnouncementResponse
import com.zzz.data.remote.domain.model.Job

/**
 * @author zyzz
*/
data class JobHomeState(
    val applied : String = "-",
    val shortlisted : String = "-",
    val offers : String = "-",
    val name : String = "",
    val jobs : List<Job> = emptyList(),
    val announcements : List<AnnouncementResponse> = emptyList()
)
