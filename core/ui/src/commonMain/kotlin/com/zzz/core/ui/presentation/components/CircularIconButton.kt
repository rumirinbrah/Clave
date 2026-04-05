package com.zzz.core.ui.presentation.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
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
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if(isPressed) 0.95f else 1f
    )

    Box (
        modifier
            .graphicsLayer{
                scaleX = scale
                scaleY = scale
            }
            .clip(CircleShape)
            .background(background)
            .clickable(
                indication = null,
                interactionSource = interactionSource,
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