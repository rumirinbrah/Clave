package com.zzz.feature.auth.di

import com.zzz.feature.auth.login.LoginViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val authModule = module {
    viewModel {
        LoginViewModel(
            authSource = get(),
            datastore = get()
        )
    }
}