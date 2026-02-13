package com.zzz.core.ui.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp

/**
 * Card container with elevation
 *
 * @author zyzz
*/
@Composable
fun CardContainer(
    modifier: Modifier = Modifier,
    background : Color = MaterialTheme.colorScheme.surface,
    shadowColor : Color = MaterialTheme.colorScheme.onBackground,
    shape : Shape = MaterialTheme.shapes.large,
    content : @Composable ()->Unit
){
    Box(
        modifier
            .dropShadow(
                shape = shape,
                shadow = Shadow(
                    radius = 3.dp ,
                    color = shadowColor.copy(0.2f) ,
                    spread = 2.dp ,
                    offset = DpOffset(x = 2.dp , y = 2.dp)
                )
            )
            .clip(shape)
            .background(background)
    ){
        content()
    }
}