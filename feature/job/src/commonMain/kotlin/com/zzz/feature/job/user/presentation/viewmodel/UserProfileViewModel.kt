package com.zzz.feature.job.user.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zzz.core.ui.util.ClaveLogger.logD
import com.zzz.core.ui.util.ClaveLogger.logE
import com.zzz.core.util.domain.Result
import com.zzz.data.remote.domain.student.profile.ProfileSource
import com.zzz.data.remote.domain.student.project.ProjectSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserProfileViewModel(
    //change to profile source
    private val profileSource: ProfileSource
) : ViewModel() {

    private val _state = MutableStateFlow(UserProfileState())
    internal val state = _state.asStateFlow()

    init {
        loadProfileData()
    }
    fun loadProfileData(){
        viewModelScope.launch {
            val result = profileSource.get()
            when(result){
                is Result.Error -> {
                    logE{
                        "loadProfileData : ${result.error.errorMsg}"
                    }
                }
                is Result.Success -> {
                    val data = result.data
                    logD {
                        "loadProfileData : $data"
                    }
                    _state.update {
                        it.copy(
                            id = data.id,
                            name = data.name,
                            gender = data.gender,
                            rollNo = data.rollNumber,
                            branch = data.branch,
                            year = data.year,
                            dob = data.dateOfBirth,
                        )
                    }
                }
            }
        }
    }

}