package com.zzz.core.ui.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import placementapp.core.ui.generated.resources.Res

@Composable
fun ActionHeader(
    modifier : Modifier = Modifier,
    title : String,
    icon : DrawableResource,
    onBack : ()->Unit,
){
    Box(
        modifier.fillMaxWidth()
    ){
        CircularIconButton(
            contentDescription = title,
            icon = icon,
            onClick = onBack,
            modifier = Modifier.align(Alignment.CenterStart),
            contentPadding = 12.dp,
            iconSize = 25.dp
        )

        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}
