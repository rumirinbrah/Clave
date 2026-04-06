package com.zzz.feature.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zzz.core.ui.domain.network.UIEvent
import com.zzz.core.ui.util.ClaveLogger.logD
import com.zzz.core.util.domain.Result
import com.zzz.data.remote.data.prefs.RemoteDatastoreSource
import com.zzz.data.remote.domain.auth.AuthSource
import com.zzz.data.remote.domain.auth.dto.LoginRequest
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class LoginUiState(
    val rollNo: String = "",
    val mobileNo: String = "",
    val password : String = "",
    val errorMsg : String? = null
)

class LoginViewModel(
    private val authSource: AuthSource,
    private val datastore : RemoteDatastoreSource,
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState

    private val _events = Channel<UIEvent>()
    val events = _events.receiveAsFlow()

    init {
        checkLogin()
    }
    fun checkLogin(){
        viewModelScope.launch {
            val access = datastore.getAccessToken()
            if(access!=null){
                this@LoginViewModel.logD {
                    "User already logged in"
                }
                _events.send(LoginEvents.AlreadyLoggedIn)
            }
        }
    }

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

    fun onPwdChange(value: String) {
        _uiState.update {
            it.copy(password = value)
        }
    }

    fun simulateSuccessLogin(){

    }
    fun login(){
        viewModelScope.launch {
            _uiState.update {
                it.copy(errorMsg = null)
            }

            val values = _uiState.value
            val request = LoginRequest(
                rollNumber =  values.rollNo,
                phoneNumber =  values.mobileNo,
                password = values.password
            )
            when(val result = authSource.login(request)){
                is Result.Error -> {
                    logD {
                        "login : Error ${result.error.errorMsg}"
                    }
                    _uiState.update {
                        it.copy(errorMsg = result.error.errorMsg)
                    }
                    _events.send(UIEvent.Error(result.error.errorMsg))
                }
                is Result.Success -> {
                    logD {
                        "login : Success ${result.data}"
                    }
                    val data = result.data
                    if(data.verificationRequired){
                        _events.send(LoginEvents.OtpVerification)
                    }else{
                        //save tokens
                        data.tokenPair?.let {
                            datastore.saveTokens(
                                access = it.accessToken,
                                refresh = it.refreshToken
                            )
                        }
                        _events.send(UIEvent.Success)
                    }
                }
            }
        }
    }

}