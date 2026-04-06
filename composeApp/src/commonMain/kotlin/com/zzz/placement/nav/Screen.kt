package com.zzz.placement.nav

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {

    @Serializable
    data object Auth : Screen(){
        @Serializable
        data object LogIn : Screen()

        @Serializable
        data object SignUp : Screen()
    }

    @Serializable
    data object Home : Screen(){
        @Serializable
        data object HomeFeed : Screen()
        @Serializable
        data class JobDescription(val jobId : String) : Screen()
    }

    @Serializable
    data object Jobs : Screen()

    @Serializable
    data object Community : Screen()

    @Serializable
    data object Account : Screen(){
        @Serializable
        data object Profile

        @Serializable
        data object Preferences

        @Serializable
        data object Settings
    }

}