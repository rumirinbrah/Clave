package com.zzz.feature.job.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zzz.core.ui.util.ClaveLogger.logD
import com.zzz.core.ui.util.ClaveLogger.logE
import com.zzz.core.ui.util.ClaveLogger.logI
import com.zzz.core.util.domain.Result
import com.zzz.data.remote.data.prefs.RemoteDatastoreSource
import com.zzz.data.remote.domain.job.JobSource
import com.zzz.data.remote.domain.student.announcements.AnnouncementSource
import com.zzz.data.remote.domain.student.profile.ProfileSource
import com.zzz.data.remote.domain.toUIError
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class JobHomeViewModel(
    private val profileSource: ProfileSource,
    private val jobSource : JobSource,
    private val announcementSource: AnnouncementSource,
    private val prefs : RemoteDatastoreSource,
)  : ViewModel(){

    private val _state = MutableStateFlow(JobHomeState())
    val state = _state.asStateFlow()

    init {
        logD {
            "JobHomeViewModel init..."
        }
        getProfile()
        getAnnouncements()
        getFeedJobs()
    }

    private fun getAnnouncements() {
        this.logD {
            "getAnnouncements : getting..."
        }
        viewModelScope.launch {
            val result = announcementSource.getAnnouncements()
            when(result){
                is Result.Error -> {
                    this@JobHomeViewModel.logE {
                        "getAnnouncements : ${result.error.toUIError()}"
                    }
                }
                is Result.Success -> {
                    val announcements = result.data
                    this@JobHomeViewModel.logD {
                        "getAnnouncements : $announcements"
                    }
                    _state.update {
                        it.copy(
                            announcements = announcements
                        )
                    }
                }
            }
        }
    }

    private fun getProfile(){
        viewModelScope.launch {
            //TODO(Add caching logic)
            //try to get cached data
            val username = prefs.getUsername()
            if(username!=null){
                this@JobHomeViewModel.logD {
                    "getProfile : cached data available"
                }
                _state.update {
                    it.copy(
                        name = username
                    )
                }
                return@launch
            }

            val result = profileSource.get()
            when(result){
                is Result.Error -> {
                    logE {
                        "getProfile : Error ${result.error}"
                    }
                    _state.update {
                        it.copy(
                            name = "Fallback name!!"
                        )
                    }
                }
                is Result.Success -> {
                    //save to cache
                    val data = result.data
                    logD {
                        "getProfile : Success $data"
                    }
                    _state.update {
                        it.copy(
                            name = data.name
                        )
                    }
                    prefs.setBranch(data.course)
                    prefs.setUsername(data.name)
                    prefs.setRollNo(data.rollNumber)
                    logD {
                        "getProfile : cached!"
                    }
                }
            }
        }
    }

    private fun getFeedJobs(){
        viewModelScope.launch {
            logD {
                "getFeedJobs : Getting jobs"
            }
            val result = jobSource.getJobs()
            when(result){
                is Result.Error -> {
                    val uiError = result.error.toUIError()
                    logE {
                        "getFeedJobs : Error $uiError}"
                    }
                }
                is Result.Success -> {
                    logI {
                        "getFeedJobs : Success ${result.data}"
                    }
                    _state.update {
                        it.copy(jobs = result.data)
                    }
                }
            }
        }
    }


}