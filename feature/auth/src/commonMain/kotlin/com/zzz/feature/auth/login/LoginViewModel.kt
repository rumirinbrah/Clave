package com.zzz.feature.auth.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class LoginUiState(
    val rollNo: String = "",
    val mobileNo: String = ""
)

class LoginViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState

    fun onRollNoChange(value: String) {
        _uiState.update {
            it.copy(rollNo = value)
        }
    }

    fun onMobileNoChange(value: String) {
        _uiState.update {
            it.copy(mobileNo = value)
        }
    }
}