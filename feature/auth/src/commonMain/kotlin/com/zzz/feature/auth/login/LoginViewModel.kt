package com.zzz.feature.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zzz.core.ui.domain.network.UIEvent
import com.zzz.core.ui.util.ClaveLogger.logD
import com.zzz.core.util.domain.Result
import com.zzz.data.remote.data.prefs.RemoteDatastoreSource
import com.zzz.data.remote.domain.NetworkError
import com.zzz.data.remote.domain.auth.AuthSource
import com.zzz.data.remote.domain.auth.dto.LoginRequest
import com.zzz.data.remote.domain.toUIError
import com.zzz.feature.auth.isValidEmail
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class LoginUiState(
    val email: String = "",
    val password: String = "" ,
    val isLoading: Boolean = false,
    val errorMsg: String? = null
)

class LoginViewModel(
    private val authSource: AuthSource ,
    private val datastore: RemoteDatastoreSource ,
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState

    private val _events = Channel<UIEvent>()
    val events = _events.receiveAsFlow()

    init {
        checkLogin()
    }

    fun checkLogin() {
        viewModelScope.launch {
            val access = datastore.getAccessToken()
            if (access != null) {
                this@LoginViewModel.logD {
                    "User already logged in"
                }
                _events.send(LoginEvents.AlreadyLoggedIn)
            }
        }
    }

    fun onPwdChange(value: String) {
        _uiState.update {
            it.copy(password = value)
        }
    }

    fun onEmailChange(value: String) {
        _uiState.update {
            it.copy(email = value)
        }
    }

    fun simulateSuccessLogin() {

    }

    fun login() {
        viewModelScope.launch {

            val values = _uiState.value
            val email = values.email.trim()
            val password = values.password.trim()

            if (!isValidEmail(email)) {
                val error = "Please enter valid email"
                _uiState.update { it.copy(errorMsg = error) }
                _events.send(UIEvent.Error(error))
                return@launch
            }

            if (password.isBlank()) {
                val error = "Please enter password"
                _uiState.update { it.copy(errorMsg = error) }
                _events.send(UIEvent.Error(error))
                return@launch
            }

            _uiState.update {
                it.copy(isLoading = true, errorMsg = null)
            }

            val request = LoginRequest(
                email = email,
                password = password
            )

            when (val result = authSource.login(request)) {

                is Result.Error -> {
                    val uiError = result.error.toUIError()

                    _uiState.update {
                        it.copy(
                            errorMsg = uiError,
                            isLoading = false
                        )
                    }

                    _events.send(UIEvent.Error(uiError))
                }

                is Result.Success -> {

                    val data = result.data

                    if (data.verificationRequired) {

                        _uiState.update { it.copy(isLoading = false) }

                        _events.send(LoginEvents.OtpVerification(email))

                    } else {

                        data.tokenPair?.let {
                            datastore.saveTokens(
                                access = it.accessToken,
                                refresh = it.refreshToken
                            )
                        }

                        _uiState.update { it.copy(isLoading = false) }

                        _events.send(UIEvent.Success)
                    }
                }
            }
        }
    }

//    fun login() {
//        viewModelScope.launch {
//
//            val values = _uiState.value
//            val email = values.email
//
//            if (!isValidEmail(values.email)) {
//                val error = "Please enter valid details"
//                _uiState.update { it.copy(errorMsg = error) }
//                _events.send(UIEvent.Error(error))
//                return@launch
//            }
//
//            _uiState.update {
//                it.copy(errorMsg = null)
//            }
//
//            val request = LoginRequest(
//                email = values.email ,
//                password = values.password
//            )
//
//            when (val result = authSource.login(request)) {
//                is Result.Error -> {
//                    val uiError = result.error.toUIError()
//                    logD {
//                        "login : Error $uiError"
//                    }
//                    if (result.error is NetworkError.Unauthorized) {
//                        _events.send(LoginEvents.OtpVerification(email))
//                    } else {
//                        _events.send(UIEvent.Error(uiError))
//                    }
//                    _uiState.update {
//                        it.copy(errorMsg = uiError)
//                    }
//                }
//
//                is Result.Success -> {
//                    logD {
//                        "login : Success ${result.data}"
//                    }
//                    val data = result.data
//
//                    //save tokens
//                    data.tokenPair?.let {
//                        datastore.saveTokens(
//                            access = it.accessToken ,
//                            refresh = it.refreshToken
//                        )
//                    }
//                    _events.send(UIEvent.Success)
//
//                }
//            }
//        }
//    }

}