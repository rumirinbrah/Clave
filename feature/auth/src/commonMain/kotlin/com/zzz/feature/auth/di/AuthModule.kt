package com.zzz.feature.auth.di

import com.zzz.feature.auth.login.LoginViewModel
import com.zzz.feature.auth.signup.SignupViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val authModule = module {
    viewModel {
        LoginViewModel(
            authSource = get()
        )
    }

    viewModel {
        SignupViewModel(
            authSource = get()
        )
    }
}