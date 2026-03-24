package com.zzz.feature.job.user.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.zzz.data.remote.domain.student.project.ProjectSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class UserProfileViewModel(
    //change to profile source
    private val projectSource: ProjectSource
) : ViewModel() {

    private val _state = MutableStateFlow(UserProfileState())
    internal val state = _state.asStateFlow()

    fun loadProfileData(){

    }

}