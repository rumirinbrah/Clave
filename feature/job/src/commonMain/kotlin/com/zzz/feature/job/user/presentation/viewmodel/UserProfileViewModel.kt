package com.zzz.feature.job.user.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zzz.core.ui.util.ClaveLogger.logD
import com.zzz.core.ui.util.ClaveLogger.logE
import com.zzz.core.util.domain.Result
import com.zzz.data.remote.data.prefs.RemoteDatastoreSource
import com.zzz.data.remote.domain.student.profile.ProfileSource
import com.zzz.data.remote.domain.student.profile.dto.Gender
import com.zzz.data.remote.domain.toUIError
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed interface ProfileEvents{
    data object LogOut : ProfileEvents
}
class UserProfileViewModel(
    //change to profile source
    private val profileSource: ProfileSource,
    private val datastoreSource: RemoteDatastoreSource
) : ViewModel() {

    private val _state = MutableStateFlow(UserProfileState())
    internal val state = _state.asStateFlow()

    private val _events = Channel<ProfileEvents>(capacity = 3)
    val events = _events.receiveAsFlow()

    init {
        loadProfileData()
    }
    fun loadProfileData(){
        viewModelScope.launch {
            val username = datastoreSource.getUsername()
            if(username!=null){
                logD {
                    "loadProfileData : cache avaialble"
                }
                val rollNo = datastoreSource.getRollNo()
                val branch = datastoreSource.getBranch()

                _state.update {
                    it.copy(
                        name = username,
                        gender = Gender.MALE,
                        rollNo = rollNo ?: "Fallback rollno",
                        branch = branch ?: "Fallback branch",
                        year = "Fallback year",
                        dob = 9080900,
                    )
                }
                return@launch
            }

            val result = profileSource.get()
            when(result){
                is Result.Error -> {
                    val uiError = result.error.toUIError()
                    logE{
                        "loadProfileData : $uiError"
                    }
                    _state.update {
                        it.copy(
                            id = "",
                            name = "Fallback name",
                            gender = Gender.MALE,
                            rollNo = "Fallback rollno",
                            branch = "Fallback branch",
                            year = "Fallback year",
                            dob = 9080900,
                        )
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

    /**
     * Logout
     *
     * @author zyzz
    */
    fun clearTokens(){
        viewModelScope.launch {
            datastoreSource.clearTokens()
            _events.send(ProfileEvents.LogOut)
        }
    }

}