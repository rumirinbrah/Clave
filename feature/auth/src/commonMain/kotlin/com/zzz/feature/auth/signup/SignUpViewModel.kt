package com.zzz.feature.auth.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zzz.core.ui.domain.network.UIEvent
import com.zzz.data.remote.domain.auth.AuthSource
import com.zzz.data.remote.domain.auth.dto.CreateAccountRequest
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.zzz.core.ui.util.ClaveLogger.logD
import com.zzz.core.util.domain.Result
import com.zzz.feature.auth.login.LoginEvents

data class SignupUiState(
    val rollNo: String = "",
    val mobileNo: String = "",
    val password: String = "",
    val isAdmin: Boolean = false,
    val errorMsg: String? = null,
    val isLoading: Boolean = false
)

class SignupViewModel(
    private val authSource: AuthSource
) : ViewModel() {

    private val _uiState = MutableStateFlow(SignupUiState())
    val uiState: StateFlow<SignupUiState> = _uiState

    private val _events = Channel<UIEvent>()
    val events = _events.receiveAsFlow()


    fun onRollNoChange(value: String) {
        _uiState.update { it.copy(rollNo = value) }
    }

    fun onMobileNoChange(value: String) {
        _uiState.update { it.copy(mobileNo = value) }
    }

    fun onPwdChange(value: String) {
        _uiState.update { it.copy(password = value) }
    }

    fun onRoleChange(isAdmin: Boolean) {
        _uiState.update { it.copy(isAdmin = isAdmin) }
    }

    fun createAccount() {
        viewModelScope.launch {

            val values = _uiState.value

            // Validation
            if (values.rollNo.isBlank() ||
                values.mobileNo.length != 10
            ) {
                val error = "Please enter valid details"
                _uiState.update { it.copy(errorMsg = error) }
                _events.send(UIEvent.Error(error))
                return@launch
            }

            _uiState.update {
                it.copy(
                    isLoading = true,
                    errorMsg = null
                )
            }

            val request = CreateAccountRequest(
                rollNumber = values.rollNo,
                phoneNumber = values.mobileNo,
                password = values.password,
                isAdmin = values.isAdmin
            )

            when (val result = authSource.createAccount(request)) {

                is Result.Error -> {
                    val error = result.error.errorMsg

                    logD { "FULL RESULT = $result" }

                    _uiState.update {
                        it.copy(
                            errorMsg = error,
                            isLoading = false
                        )
                    }

                    _events.send(UIEvent.Error(error))
                }

                is Result.Success -> {
                    val data = result.data

                    if (data.successful) {
                        logD {
                            "login : Success ${result.data}"
                        }
                        _uiState.update {
                            it.copy(isLoading = false)
                        }

                        _events.send(LoginEvents.OtpVerification)

                    } else {
                        val error = data.errorMessage ?: "Something went wrong"

                        _uiState.update {
                            it.copy(
                                errorMsg = error,
                                isLoading = false
                            )
                        }

                        _events.send(UIEvent.Error(error))
                    }
                }
            }
        }
    }
}