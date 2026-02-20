package com.zzz.feature.job.home.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zzz.core.ui.presentation.components.VerticalSpace
import com.zzz.feature.job.home.presentation.components.JobAnnouncementsCard
import com.zzz.feature.job.home.presentation.components.RecentJobsCard

@Composable
fun JobHomePageRoot(
    modifier: Modifier = Modifier
){
    JobHomePage(
        modifier
    )
}
@Composable
private fun JobHomePage(
    modifier: Modifier = Modifier
){
    Box(
        Modifier.fillMaxSize()
    ){
        Column(
            modifier
        ) {
            JobAnnouncementsCard()

            VerticalSpace(40.dp)

            RecentJobsCard()
        }
    }
}