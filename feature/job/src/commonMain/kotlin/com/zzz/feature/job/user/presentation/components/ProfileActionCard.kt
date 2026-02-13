package com.zzz.feature.job.user.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.draw.innerShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zzz.core.ui.presentation.components.ImageComponent
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import placementapp.feature.job.generated.resources.Recordify_logo
import placementapp.feature.job.generated.resources.Res
import placementapp.feature.job.generated.resources.outline_account_circle_24
import placementapp.feature.job.generated.resources.outline_arrow_forward_ios_24
import placementapp.feature.job.generated.resources.quality_badge

@Composable
fun ProfileActionCard(
    modifier: Modifier = Modifier,
    icon : DrawableResource,
    actionText : String,
    onClick : ()->Unit,
    enabled : Boolean = true,
    shape: Shape = MaterialTheme.shapes.large,
    background : Color = MaterialTheme.colorScheme.surface,
    onBackground : Color = MaterialTheme.colorScheme.onBackground,
){
    Row (
        modifier
            .fillMaxWidth()
            .dropShadow(
                shape = shape,
                shadow = Shadow(
                    radius = 3.dp ,
                    color = onBackground.copy(0.2f) ,
                    spread = 2.dp ,
                    offset = DpOffset(x = 2.dp , y = 2.dp)
                )
            )
            .clip(shape)
            .background(background)
            .clickable(
                enabled = enabled,
                onClick = {
                    onClick()
                }
            )
            .padding(horizontal = 8.dp , vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Row (
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                painter = painterResource(icon),
                contentDescription = actionText,
                modifier = Modifier.size(20.dp),
                tint = onBackground
            )
            Text(
                text = actionText,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = onBackground
            )
        }
        Icon(
            painter = painterResource(Res.drawable.outline_arrow_forward_ios_24),
            contentDescription = actionText,
            modifier = Modifier.size(15.dp),
            tint = onBackground
        )
    }
}
