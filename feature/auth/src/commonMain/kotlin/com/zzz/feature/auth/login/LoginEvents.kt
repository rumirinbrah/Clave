package com.zzz.feature.auth.login

import com.zzz.core.ui.domain.network.UIEvent

sealed interface LoginEvents : UIEvent{
    data object OtpVerification : LoginEvents

}