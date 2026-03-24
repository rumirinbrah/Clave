package com.zzz.feature.auth.login

data class AuthTabItem(
    val title: String,
    val page: Int
)

val authTabs = listOf(
    AuthTabItem("Student", 0),
    AuthTabItem("Coordinator", 1)
)