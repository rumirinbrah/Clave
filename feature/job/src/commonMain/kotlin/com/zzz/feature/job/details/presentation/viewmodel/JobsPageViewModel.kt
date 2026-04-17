package com.zzz.feature.job.details.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zzz.core.ui.util.ClaveLogger.logD
import com.zzz.core.ui.util.ClaveLogger.logE
import com.zzz.core.ui.util.ClaveLogger.logI
import com.zzz.core.util.domain.Result
import com.zzz.data.remote.domain.job.JobSource
import com.zzz.data.remote.domain.model.Job
import com.zzz.data.remote.domain.toUIError
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class AllJobsState(
    val jobs : List<Job> = emptyList()
)

class JobsPageViewModel(
    private val jobSource: JobSource
) : ViewModel() {

    private val _state = MutableStateFlow(AllJobsState())
    val state = _state.asStateFlow()

    init {
        getJobs()
    }

    fun getJobs(){
        viewModelScope.launch {
            this@JobsPageViewModel.logD {
                "getFeedJobs : Getting jobs"
            }
            val result = jobSource.getJobs()
            when(result){
                is Result.Error -> {
                    this@JobsPageViewModel.logE {
                        "getFeedJobs : Error ${result.error.toUIError()}"
                    }
                }
                is Result.Success -> {
                    this@JobsPageViewModel.logI {
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