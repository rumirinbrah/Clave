package com.zzz.feature.job.user.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zzz.core.ui.presentation.components.ImageComponent
import com.zzz.core.ui.presentation.components.VerticalSpace
import placementapp.feature.job.generated.resources.Recordify_logo
import placementapp.feature.job.generated.resources.Res

@Composable
internal fun ProfileHeaderCard(
    modifier: Modifier = Modifier
){
    Column(
        modifier,
//        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //TODO(ADD URL)
        ImageComponent(
            imageRes = Res.drawable.Recordify_logo,
            modifier = Modifier.clip(CircleShape)
                .background(Color.Gray),
            size = 80.dp
        )
        VerticalSpace(5.dp)
        Text(
            text = "Rahul Shinde",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        VerticalSpace(5.dp)
        Text(
            text = "22CO078",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onBackground.copy(0.7f),
            lineHeight = 14.sp
        )
        Text(
            text = "Computer Engineering",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onBackground.copy(0.7f),
            lineHeight = 14.sp
        )
    }
}