package com.zzz.feature.job.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.zzz.core.ui.util.ClaveLogger.logD
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class JobHomeViewModel  : ViewModel(){

    private val _state = MutableStateFlow(JobHomeState())
    val state = _state.asStateFlow()

    init {
        logD {
            "JobHomeViewModel init..."
        }
    }

}