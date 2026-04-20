package com.zzz.feature.job.di

import com.zzz.feature.job.details.presentation.JobDescriptionViewModel
import com.zzz.feature.job.details.presentation.viewmodel.JobsPageViewModel
import com.zzz.feature.job.home.presentation.viewmodel.JobHomeViewModel
import com.zzz.feature.job.user.presentation.viewmodel.UpdateProfileViewModel
import com.zzz.feature.job.user.presentation.viewmodel.UserProfileViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val jobModule = module {
    viewModel {
        UserProfileViewModel(
            profileSource = get(),
            datastoreSource = get()
        )
    }
    viewModel {
        UpdateProfileViewModel(
            profileSource = get()
        )
    }
    viewModel{
        JobHomeViewModel(
            profileSource = get(),
            jobSource = get(),
            prefs = get()
        )
    }
    viewModel{
        JobDescriptionViewModel(
            jobSource = get(),
            jobApplicationSource = get()
        )
    }
    viewModel{
        JobsPageViewModel(
            jobSource = get()
        )
    }
}