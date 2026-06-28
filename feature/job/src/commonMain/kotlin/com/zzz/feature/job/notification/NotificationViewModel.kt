package com.zzz.feature.job.notification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zzz.data.remote.data.notification.NotificationResponse
import com.zzz.data.remote.data.notification.NotificationSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.zzz.core.util.domain.Result
import com.zzz.data.remote.domain.toUIError


data class NotificationState(
    val notifications: List<NotificationResponse> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
class NotificationViewModel(
    private val notificationSource: NotificationSource
) : ViewModel() {

    private val _state = MutableStateFlow(NotificationState())
    val state = _state.asStateFlow()

    init {
        getNotifications()
    }

    fun getNotifications() {

        viewModelScope.launch {

            _state.update {
                it.copy(
                    isLoading = true,
                    error = null
                )
            }
            val result = notificationSource.getNotifications()

            when (result) {

                is Result.Success -> {

                    _state.update {
                        it.copy(
                            notifications = result.data,
                            isLoading = false
                        )
                    }
                }

                is Result.Error -> {

                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = result.error.toUIError()
                        )
                    }
                }
            }
        }
    }
}