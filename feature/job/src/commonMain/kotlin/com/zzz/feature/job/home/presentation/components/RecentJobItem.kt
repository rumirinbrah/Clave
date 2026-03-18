package com.zzz.feature.job.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.zzz.core.ui.presentation.components.CardContainer
import com.zzz.core.ui.presentation.components.ImageComponent
import org.jetbrains.compose.resources.painterResource
import placementapp.feature.job.generated.resources.Recordify_logo
import placementapp.feature.job.generated.resources.Res
import placementapp.feature.job.generated.resources.gear
import placementapp.feature.job.generated.resources.heart

@Composable
fun RecentJobItem(
    modifier: Modifier = Modifier
){
    CardContainer(
        modifier.fillMaxWidth(),
        padding = 16.dp,
    ) {
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            //---------COMPANY LOGO--------
            Box(
                Modifier
                    .background(
                        Color.White,
                        CircleShape
                    )
                    .padding(12.dp)
            ){
                ImageComponent(
                    imageRes = Res.drawable.Recordify_logo,
                    modifier = Modifier.clip(CircleShape),
                    size = 50.dp
                )
            }
            //---------JOB DETAILS--------
            Column(
                Modifier.weight(1f)
            ) {
                Text(
                    "TikTok",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    "Content editor",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground.copy(0.7f)
                )
                Text(
                    "Apply by 26 OCT",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground.copy(0.7f)
                )
            }
            //---------SAVED--------
            Icon(
                painter = painterResource(Res.drawable.heart),
                contentDescription = "Save/wishlist",
            )

        }
    }
}