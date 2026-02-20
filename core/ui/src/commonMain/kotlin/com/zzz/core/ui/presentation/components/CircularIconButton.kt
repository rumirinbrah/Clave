package com.zzz.core.ui.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun CircularIconButton(
    modifier: Modifier = Modifier ,
    contentDescription: String ,
    icon: DrawableResource ,
    onClick: () -> Unit ,
    enabled: Boolean = true ,
    background: Color = MaterialTheme.colorScheme.primaryContainer ,
    tint: Color = MaterialTheme.colorScheme.onPrimaryContainer ,
    iconSize : Dp = 30.dp ,
    contentPadding : Dp = 20.dp
) {
    Box (
        modifier
            .clip(CircleShape)
            .background(background)
            .clickable(
                enabled = enabled,
                onClick = {
                    onClick()
                }
            )
            .padding(contentPadding)
    ){
        Icon(
            painter = painterResource(icon) ,
            contentDescription = contentDescription,
            modifier = Modifier.size(iconSize),
            tint = tint
        )
    }
}