package com.zzz.feature.job.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zzz.core.ui.presentation.components.CircularIconButton
import com.zzz.core.ui.presentation.components.ImageComponent
import com.zzz.core.ui.presentation.components.VerticalSpace
import com.zzz.feature.job.home.presentation.components.JobAnnouncementsCard
import com.zzz.feature.job.home.presentation.components.RecentJobsCard
import com.zzz.feature.job.home.presentation.viewmodel.JobHomeState
import com.zzz.feature.job.home.presentation.viewmodel.JobHomeViewModel
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import placementapp.feature.job.generated.resources.Res
import placementapp.feature.job.generated.resources.gear

@Composable
fun JobHomePageRoot(
    modifier: Modifier = Modifier,
    onJobClick: (id: String) -> Unit
){
    val viewModel = koinViewModel<JobHomeViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    JobHomePage(
        modifier,
        state = state,
        onJobClick = onJobClick
    )
}
@Composable
private fun JobHomePage(
    modifier: Modifier = Modifier,
    state : JobHomeState,
    onJobClick : (id : String)->Unit
){
    Box(
        Modifier.fillMaxSize()
    ){
        Column(
            modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Row (
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
            ){
                Row (
                    Modifier.weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                ){
                    ImageComponent(
                        Modifier.clip(CircleShape),
                        imageUrl = "https://img.freepik.com/premium-vector/tik-tok-logo_578229-290.jpg?semt=ais_hybrid&w=740&q=80",
                        size = 40.dp,
                    )
                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    fontSize = 14.sp
                                )
                            ){
                                append("Welcome back")
                            }
                            appendLine()
                            withStyle(
                                style = SpanStyle(
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            ){
                                append(state.name)
                            }
                        }
                    )
                }

                CircularIconButton(
                    icon = Res.drawable.gear,
                    contentDescription = "Alerts",
                    onClick = {},
                    iconSize = 25.dp,
                    contentPadding = 16.dp
                )

            }
            VerticalSpace(40.dp)

            JobAnnouncementsCard(
                items = state.announcements
            )

            VerticalSpace(40.dp)

            RecentJobsCard(
                state = state,
                onClick = onJobClick
            )
        }
    }
}