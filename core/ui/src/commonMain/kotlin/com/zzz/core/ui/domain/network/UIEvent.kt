package com.zzz.core.ui.domain.network

interface UIEvent {
    data object Success : UIEvent
    data class Error(val errorMsg : String) : UIEvent
}