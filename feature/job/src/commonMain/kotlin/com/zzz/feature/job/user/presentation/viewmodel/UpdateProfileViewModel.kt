package com.zzz.feature.job.user.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zzz.core.ui.util.ClaveLogger.logD
import com.zzz.core.ui.util.ClaveLogger.logE
import com.zzz.core.util.domain.Result
import com.zzz.data.remote.domain.student.profile.ProfileSource
import com.zzz.data.remote.domain.student.profile.dto.SaveStudentProfileRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


internal data class UpdateProfileState(
    val name : String = "",
    val rollNo : String = "",
    val branch : String = "",
)

class UpdateProfileViewModel(
    private val profileSource: ProfileSource
) : ViewModel(){

    private val _state = MutableStateFlow(UpdateProfileState())
    internal val state = _state.asStateFlow()

    fun onNameChange(newName: String) {
        _state.update { it.copy(name = newName) }
    }

    fun onRollNoChange(newRollNo: String) {
        _state.update { it.copy(rollNo = newRollNo) }
    }

    fun onBranchChange(newBranch: String) {
        _state.update { it.copy(branch = newBranch) }
    }

    internal fun updateProfile(
        id : String,
        state: UserProfileState
    ){
        viewModelScope.launch {
            logD {
                "updateProfile : Updating profile"
            }
            val values = _state.value
            val request = SaveStudentProfileRequest(
                name =  values.name.ifBlank { state.name },
                gender =  state.gender,
                rollNumber =  values.rollNo.ifBlank { state.rollNo },
                branch =  values.branch.ifBlank { state.branch },
                year =  state.year,
                dateOfBirth = state.dob
            )
            val result = profileSource.update(id,request)
            when(result ){
                is Result.Error -> {
                    logE {
                        "updateProfile : Error updating profile"
                    }
                }
                is Result.Success -> {
                    logD {
                        "updateProfile : Success"
                    }
                }
            }
        }
    }

}