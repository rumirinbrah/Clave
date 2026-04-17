package com.zzz.feature.job.details.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zzz.core.ui.util.ClaveLogger.logD
import com.zzz.core.ui.util.ClaveLogger.logE
import com.zzz.core.util.domain.Result
import com.zzz.data.remote.domain.job.JobSource
import com.zzz.data.remote.domain.model.Job
import com.zzz.data.remote.domain.toUIError
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


data class JobDetailState(
    val job : Job? = null,
    val loading : Boolean = false
)

class JobDescriptionViewModel(
    private val jobSource: JobSource
) : ViewModel() {

    private val _state = MutableStateFlow(JobDetailState())
    val state = _state.asStateFlow()


    fun getJobById(id : String){
        viewModelScope.launch {
            _state.update {
                it.copy( loading = true)
            }
            val result = jobSource.getById(id)
            when(result){
                is Result.Error -> {
                    this@JobDescriptionViewModel.logE {
                        "getJobById : ${result.error.toUIError()}"
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
                            loading = false,
                            job = result.data
                        )
                    }
                }
            }
        }
    }
}