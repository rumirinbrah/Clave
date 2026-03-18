package com.zzz.feature.job.home.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zzz.core.ui.presentation.components.VerticalSpace
import com.zzz.feature.job.home.presentation.components.JobAnnouncementsCard
import com.zzz.feature.job.home.presentation.components.RecentJobsCard
import com.zzz.feature.job.home.presentation.viewmodel.JobHomeState
import com.zzz.feature.job.home.presentation.viewmodel.JobHomeViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun JobHomePageRoot(
    modifier: Modifier = Modifier
){
    val viewModel = koinViewModel<JobHomeViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    JobHomePage(
        modifier,
        state = state
    )
}
@Composable
private fun JobHomePage(
    modifier: Modifier = Modifier,
    state : JobHomeState
){
    Box(
        Modifier.fillMaxSize()
    ){
        Column(
            modifier
        ) {
            JobAnnouncementsCard()

            VerticalSpace(40.dp)

            RecentJobsCard(
                state = state
            )
        }
    }
}