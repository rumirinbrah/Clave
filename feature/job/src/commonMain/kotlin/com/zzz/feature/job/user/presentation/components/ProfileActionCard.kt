package com.zzz.feature.job.user.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.zzz.core.ui.presentation.components.VerticalSpace
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import placementapp.feature.job.generated.resources.Recordify_logo
import placementapp.feature.job.generated.resources.Res
import placementapp.feature.job.generated.resources.outline_account_circle_24
import placementapp.feature.job.generated.resources.outline_arrow_forward_ios_24
import placementapp.feature.job.generated.resources.quality_badge

@Composable
fun ProfileActionCard(
    icon: DrawableResource,
    title: String,
    subtitle: String? = null,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = RoundedCornerShape(22.dp),
    background: Color = MaterialTheme.colorScheme.surface,
    onBackground: Color = MaterialTheme.colorScheme.onSurface,
    iconTint: Color = onBackground
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .dropShadow(
                shape = shape,
                shadow = Shadow(
                    radius = 3.dp,
                    spread = 2.dp,
                    color = onBackground.copy(alpha = 0.2f),
                    offset = DpOffset(2.dp, 2.dp)
                )
            )
            .clip(shape)
            .background(background)
            .clickable(
                enabled = enabled,
                interactionSource = null,
                indication = null,
                onClick = onClick
            )
            .padding(horizontal = 18.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                painter = painterResource(icon),
                contentDescription = title,
                modifier = Modifier.size(28.dp).padding(end = 6.dp),
                tint = iconTint
            )

            Column {

                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = onBackground
                )

                subtitle?.let {
                    VerticalSpace(2.dp)

                    Text(
                        text = it,
                        fontSize = 12.sp,
                        color = onBackground.copy(alpha = 0.65f)
                    )
                }
            }
        }

        Icon(
            painter = painterResource(Res.drawable.outline_arrow_forward_ios_24),
            contentDescription = null,
            modifier = Modifier.size(16.dp),
            tint = onBackground.copy(alpha = 0.8f)
        )
    }
}
