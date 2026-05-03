package com.zzz.feature.job.details.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zzz.core.ui.util.ClaveLogger.logD
import com.zzz.core.ui.util.ClaveLogger.logE
import com.zzz.core.util.domain.Result
import com.zzz.data.remote.data.job.dto.ApplyJobRequest
import com.zzz.data.remote.domain.job.JobApplicationSource
import com.zzz.data.remote.domain.job.JobSource
import com.zzz.data.remote.domain.model.Job
import com.zzz.data.remote.domain.toUIError
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


data class JobDetailState(
    val job: Job? = null ,
    val loading: Boolean = false ,
    val showDidYouApply: Boolean = false
)

sealed interface JobDetailEvents {
    data class OpenJobLink(val url: String) : JobDetailEvents
}

class JobDescriptionViewModel(
    private val jobSource: JobSource ,
    private val jobApplicationSource: JobApplicationSource ,
) : ViewModel() {

    private val _state = MutableStateFlow(JobDetailState())
    val state = _state.asStateFlow()

    private val _events = Channel<JobDetailEvents>()
    val events = _events.receiveAsFlow()

    fun openJobLink() {
        viewModelScope.launch {
            _state.value.job?.applicationLink?.let {
                _events.send(JobDetailEvents.OpenJobLink(it))
            }
            this@JobDescriptionViewModel.logD {
                "Show did you apply"
            }
            _state.update {
                it.copy(
                    showDidYouApply = true
                )
            }
        }
    }

    fun onDidYouApply(applied: Boolean) {
        this.logD {
            "onDidYouApply : Marking as applied"
        }
        viewModelScope.launch {
            if (applied) {
                //api call
                val id = _state.value.job?.id ?: return@launch
                _state.update {
                    it.copy(
                        showDidYouApply = false ,
                        loading = true
                    )
                }
                val result = jobApplicationSource.apply(ApplyJobRequest(id))
                when (result) {
                    is Result.Error -> {
                        this.logE {
                            "onDidYouApply : Error ${result.error}"
                        }
                        val error = result.error.toUIError()
                        this@JobDescriptionViewModel.logE {
                            "applyJob : error $error"
                        }
                    }

                    is Result.Success -> {
                        this.logD {
                            "onDidYouApply : Marked as applied"
                        }
                        this@JobDescriptionViewModel.logD {
                            "applyJob : success"
                        }
                    }
                }

            }
            _state.update {
                it.copy(
                    showDidYouApply = false ,
                    loading = false
                )
            }
        }
    }

    fun getJobById(id: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(loading = true)
            }
            val result = jobSource.getById(id)
            when (result) {
                is Result.Error -> {
                    val uiError = result.error.toUIError()
                    this@JobDescriptionViewModel.logE {
                        "getJobById : $uiError"
                    }
                    _state.update {
                        it.copy(
                            loading = false
                        )
                    }
                }

                is Result.Success -> {
                    this@JobDescriptionViewModel.logD {
                        "getJobById : Success ${result.data}"
                    }
                    _state.update {
                        it.copy(
                            loading = false ,
                            job = result.data
                        )
                    }
                }
            }
        }
    }
}