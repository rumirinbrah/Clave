package com.zzz.feature.job.details.presentation

import kotlinx.serialization.Serializable

data class JobTab(
    val title: String,
    val type: JobTabType
)

enum class JobTabType {
    JOBS,
    APPLICATIONS,
    OFFERS
}

val jobTabs = listOf(
    JobTab("Jobs", JobTabType.JOBS),
    JobTab("Applications", JobTabType.APPLICATIONS),
    JobTab("Offers", JobTabType.OFFERS)
)

