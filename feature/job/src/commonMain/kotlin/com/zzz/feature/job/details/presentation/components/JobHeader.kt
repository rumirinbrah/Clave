package com.zzz.feature.job.details.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.zzz.core.ui.presentation.components.ActionHeader
import com.zzz.core.ui.presentation.components.ImageComponent
import com.zzz.data.remote.domain.model.Job
import com.zzz.data.remote.domain.model.formatted
import placementapp.feature.job.generated.resources.Res
import placementapp.feature.job.generated.resources.baseline_arrow_back_24

@Composable
fun JobHeader(jobPost: Job, onBack: () -> Unit){
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ActionHeader(
                    title = "Job details",
                    onBack = { onBack() },
                    icon = Res.drawable.baseline_arrow_back_24
                )

                Spacer(modifier = Modifier.height(16.dp))

                Box(
                    modifier = Modifier
                        .size(70.dp)
                        .background(Color.White, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    ImageComponent(
                        imageUrl = jobPost.companyLogoUrl ?: "",
                        contentScale = ContentScale.Fit,
                        size = 60.dp
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = jobPost.companyName,
                    style = MaterialTheme.typography.titleLarge
                )

                Text(
                    text = jobPost.role,
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = jobPost.location,
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(16.dp))
            }

            JobInfoCard(
                ctc = jobPost.ctc,
                jobType = jobPost.employmentType.formatted(),
                deadline = jobPost.formattedDate,
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (-20).dp)
            )
        }
    }
}