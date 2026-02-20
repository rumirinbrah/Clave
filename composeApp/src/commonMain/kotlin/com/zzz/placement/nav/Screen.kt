package com.zzz.placement.nav

import kotlinx.serialization.Serializable

sealed class Screen {

    @Serializable
    data object Home : Screen()

    @Serializable
    data object Jobs : Screen()

    @Serializable
    data object Community : Screen()

    @Serializable
    data object Account : Screen()

}