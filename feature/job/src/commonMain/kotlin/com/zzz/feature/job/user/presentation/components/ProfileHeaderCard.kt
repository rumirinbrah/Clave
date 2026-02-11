package com.zzz.feature.job.user.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zzz.core.ui.presentation.components.ImageComponent

@Composable
internal fun UserProfileCard(
    modifier: Modifier
){
    Column(
        modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //TODO(ADD URL)
        ImageComponent(
            imageUrl = ""
        )
        Text(
            text = "Rahul Shinde",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = "Computer Engineering",
            fontSize = 15.sp,
            color = MaterialTheme.colorScheme.onBackground.copy(0.7f)
        )
    }
}