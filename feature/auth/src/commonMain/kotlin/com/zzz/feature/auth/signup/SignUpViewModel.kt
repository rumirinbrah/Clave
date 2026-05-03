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
import com.zzz.data.remote.domain.job.JobSource
import com.zzz.data.remote.domain.toUIError
import com.zzz.feature.auth.isValidEmail
import com.zzz.feature.auth.login.LoginEvents
import kotlinx.coroutines.selects.select

data class SignupUiState(
    val rollNo: String = "22CO078" ,
    val email: String = "atharvapajgade@gmail.com" ,
    val name: String = "Atharva" ,
    val password: String = "P@jgade55" ,
    val isAdmin: Boolean = false ,
    val errorMsg: String? = null ,
    val isLoading: Boolean = false,
    val courses : List<String> = emptyList(),
    val selectedCourse : String? = null
)

class SignupViewModel(
    private val authSource: AuthSource,
    private val jobSource: JobSource
) : ViewModel() {

    private val _uiState = MutableStateFlow(SignupUiState())
    val uiState: StateFlow<SignupUiState> = _uiState

    private val _events = Channel<UIEvent>()
    val events = _events.receiveAsFlow()

    private var coursesMap : Map<Int,String> = emptyMap()

    init {
        loadCourses()
    }

    private fun loadCourses(){
        if(coursesMap.isNotEmpty()){
            return
        }
        viewModelScope.launch {
            val result = authSource.getCourses()
            when(result){
                is Result.Error -> {
                    _events.send(UIEvent.Error(result.error.toUIError()))
                }
                is Result.Success -> {
                    coursesMap = result.data
                    val mapped = coursesMap.values.toList()
                    _uiState.update {
                        it.copy(
                            courses = mapped
                        )
                    }
                }
            }
        }
    }

    fun onCourseSelect(course : String){
        _uiState.update {
            it.copy(selectedCourse = course)
        }
    }

    fun onRollNoChange(value: String) {
        _uiState.update { it.copy(rollNo = value) }
    }
    fun onNameChange(value: String) {
        _uiState.update { it.copy(name = value) }
    }

    fun onEmailChange(value: String) {
        _uiState.update { it.copy(email = value) }
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
                !isValidEmail(values.email)
            ) {
                val error = "Please enter valid details"
                _uiState.update { it.copy(errorMsg = error) }
                _events.send(UIEvent.Error(error))
                return@launch
            }

            _uiState.update {
                it.copy(
                    isLoading = true ,
                    errorMsg = null
                )
            }

            val courseId = coursesMap.entries
            .firstOrNull { it.value == values.selectedCourse }
            ?.key ?: run {
                _events.send(UIEvent.Error("Invalid course!"))
                return@launch
            }

            val request = CreateAccountRequest(
                rollNumber = values.rollNo ,
                email = values.email ,
                password = values.password ,
                isAdmin = values.isAdmin,
                name = values.name,
                courseId = courseId
            )

            when (val result = authSource.createAccount(request)) {

                is Result.Error -> {
                    val error = result.error.toUIError()

                    logD { "FULL RESULT = $result" }

                    _uiState.update {
                        it.copy(
                            errorMsg = error ,
                            isLoading = false
                        )
                    }

                    _events.send(UIEvent.Error(error))
                }

                is Result.Success -> {
                    //on success, simply navigate to OTP page
//                    val data = result.data

                    _uiState.update {
                        it.copy(isLoading = false)
                    }

                    _events.send(
                        LoginEvents.OtpVerification(values.email)
                    )
//                    if (data.successful) {
//                        logD {
//                            "login : Success ${result.data}"
//                        }
//                        _uiState.update {
//                            it.copy(isLoading = false)
//                        }
//
//                        _events.send(LoginEvents.OtpVerification)
//
//                    } else {
//                        val error = data.errorMessage ?: "Something went wrong"
//
//                        _uiState.update {
//                            it.copy(
//                                errorMsg = error,
//                                isLoading = false
//                            )
//                        }
//
//                        _events.send(UIEvent.Error(error))
//                    }
                }
            }
        }
    }

    fun verifyOtp(){
        TODO()
    }

    fun resendOtp(){
        TODO()
    }
}