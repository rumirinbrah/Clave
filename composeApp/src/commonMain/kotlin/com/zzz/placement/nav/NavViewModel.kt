package com.zzz.placement.nav

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class NavState(
    val navBarVisible : Boolean = false,
    val currentRoute : Screen = Screen.Home
)

class NavViewModel (): ViewModel(){

    private val _state = MutableStateFlow(NavState())
    val state = _state.asStateFlow()

    fun changeRoute(route: Screen){
        _state.update {
            it.copy(currentRoute = route)
        }
    }

    fun navBarVisible(visible : Boolean){
        _state.update {
            it.copy(navBarVisible = visible)
        }
    }

}