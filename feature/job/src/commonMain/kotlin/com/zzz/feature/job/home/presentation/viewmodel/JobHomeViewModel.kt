package com.zzz.feature.job.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zzz.core.ui.util.ClaveLogger.logD
import com.zzz.core.ui.util.ClaveLogger.logE
import com.zzz.core.ui.util.ClaveLogger.logI
import com.zzz.core.util.domain.Result
import com.zzz.data.remote.domain.job.JobSource
import com.zzz.data.remote.domain.student.profile.ProfileSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class JobHomeViewModel(
    private val profileSource: ProfileSource,
    private val jobSource : JobSource
)  : ViewModel(){

    private val _state = MutableStateFlow(JobHomeState())
    val state = _state.asStateFlow()

    init {
        logD {
            "JobHomeViewModel init..."
        }
        getProfile()
        getFeedJobs()
    }

    private fun getProfile(){
        viewModelScope.launch {
            //TODO(Add caching logic)
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
                    val data = result.data
                    logD {
                        "getProfile : Success $data"
                    }
                    _state.update {
                        it.copy(
                            name = data.name
                        )
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
                    logE {
                        "getFeedJobs : Error ${result.error.errorMsg}"
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