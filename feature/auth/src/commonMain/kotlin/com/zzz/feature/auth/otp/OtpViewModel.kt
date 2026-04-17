package com.zzz.feature.auth.otp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zzz.data.remote.domain.auth.AuthSource
import com.zzz.data.remote.domain.auth.dto.VerifyOtpRequest
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.zzz.core.ui.domain.network.UIEvent
import kotlinx.coroutines.flow.receiveAsFlow
import com.zzz.core.util.domain.Result
import com.zzz.data.remote.domain.auth.dto.ResendOtpRequest
import com.zzz.data.remote.domain.toUIError
import kotlinx.coroutines.delay

data class OtpUiState(
    val otp: String = "",
    val isLoading: Boolean = false,
    val errorMsg: String? = null,
    val resendEnabled: Boolean = true,
    val secondsLeft: Int = 0
)

class OtpViewModel(
    private val authSource: AuthSource
) : ViewModel() {

    private val _uiState = MutableStateFlow(OtpUiState())
    val uiState: StateFlow<OtpUiState> = _uiState

    private val _events = Channel<UIEvent>()
    val events = _events.receiveAsFlow()

    fun onOtpChange(value: String) {
        if (value.length <= 6) {
            _uiState.update { it.copy(otp = value) }
        }
    }

    fun verifyOtp(email: String) {
        viewModelScope.launch {
            val values = _uiState.value

            if (values.otp.length != 6) {
                val error = "Enter valid 6-digit OTP"
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

            val request = VerifyOtpRequest(
                email = email,
                otp = values.otp
            )

            when (val result = authSource.verifyOtp(request)) {

                is Result.Error -> {
                    val error = result.error.toUIError()

                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMsg = error
                        )
                    }

                    _events.send(UIEvent.Error(error))
                }

                is Result.Success -> {

                    _uiState.update {
                        it.copy(isLoading = false)
                    }

                    _events.send(UIEvent.Success)
                }
            }
        }
    }

    fun resendOtp(email: String) {
        viewModelScope.launch {

            if (!_uiState.value.resendEnabled) return@launch

            val request = ResendOtpRequest(email)
            when (val result = authSource.resendOtp(request)) {

                is Result.Success -> {

                    _events.send(UIEvent.Success)

                    _uiState.update {
                        it.copy(
                            resendEnabled = false,
                            secondsLeft = 60
                        )
                    }

                    startResendTimer()
                }

                is Result.Error -> {
                    val error = result.error.toUIError()

                    _uiState.update {
                        it.copy(errorMsg = error)
                    }

                    _events.send(UIEvent.Error(error))
                }
            }
        }
    }


    private fun startResendTimer() {
        viewModelScope.launch {
            for (i in 60 downTo 1) {
                delay(1000)
                _uiState.update {
                    it.copy(secondsLeft = i)
                }
            }

            _uiState.update {
                it.copy(
                    resendEnabled = true,
                    secondsLeft = 0
                )
            }
        }
    }
}