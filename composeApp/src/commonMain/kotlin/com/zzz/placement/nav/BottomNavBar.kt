package com.zzz.placement.nav

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.draw.innerShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import placementapp.composeapp.generated.resources.Res
import placementapp.composeapp.generated.resources.account_person
import placementapp.composeapp.generated.resources.briefcase
import placementapp.composeapp.generated.resources.chat
import placementapp.composeapp.generated.resources.shopping_bag


@Composable
fun BottomNavBar(
    modifier: Modifier = Modifier ,
    navController: NavController ,
    currentRoute: Screen ,
    onRouteChange: (Screen) -> Unit ,
    shape: Shape = RoundedCornerShape(
        topStart = 10.dp ,
        topEnd = 10.dp
    ) ,
    shadowColor: Color = MaterialTheme.colorScheme.onBackground.copy(0.1f),
    height: Dp = 60.dp ,
    width: Float = 0.95f ,
    iconSize: Dp = 25.dp
) {

    Row(
        modifier
            .clip(shape)
            .height(height)
            .fillMaxWidth(width)
            .background(MaterialTheme.colorScheme.surface)
            .innerShadow(
                shape = shape,
                shadow = Shadow(
                    radius = 2.dp ,
                    color = shadowColor ,
                    spread = 2.dp ,
                    offset = DpOffset(x = 1.dp , y = 1.dp)
                )
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomNavItem(
            icon = Res.drawable.briefcase ,
            label = "Home" ,
            onClick = {
                onRouteChange(Screen.Home)
                TODO()
            } ,
            selected = currentRoute == Screen.Home ,
            modifier = Modifier
                .height(height)
                .weight(1f) ,
            size = iconSize
        )
        BottomNavItem(
            icon = Res.drawable.shopping_bag ,
            label = "Jobs" ,
            onClick = {
                onRouteChange(Screen.Jobs)
                TODO()
            } ,
            selected = currentRoute == Screen.Jobs ,
            size = iconSize ,
            modifier = Modifier
                .height(height)
                .weight(1f)
        )

        BottomNavItem(
            icon = Res.drawable.chat ,
            label = "Community" ,
            onClick = {
                onRouteChange(Screen.Community)
                TODO()
            } ,
            selected = currentRoute == Screen.Community ,
            size = iconSize ,
            modifier = Modifier
                .height(height)
                .weight(1f)
        )

        BottomNavItem(
            icon = Res.drawable.account_person ,
            label = "Account" ,
            onClick = {
                onRouteChange(Screen.Account)
                TODO()
            } ,
            selected = currentRoute == Screen.Account ,
            size = iconSize ,
            modifier = Modifier
                .height(height)
                .weight(1f)
        )
    }
}

@Composable
private fun BottomNavItem(
    modifier: Modifier = Modifier ,
    icon: DrawableResource ,
    label: String ,
    onClick: () -> Unit ,
    selected: Boolean = false ,
    size: Dp = 25.dp
) {
    val background = animateColorAsState(
        targetValue = if (selected) {
            MaterialTheme.colorScheme.surfaceContainer
        } else {
            MaterialTheme.colorScheme.surface
        } ,
        animationSpec = tween(150)
    )
    Box(
        modifier
            .clickable(
                interactionSource = null ,
                indication = null
            ) {
                if (!selected) {
                    onClick()
                }
            }
            .padding(5.dp) ,
        contentAlignment = Alignment.Center
    ) {
        Box(
            Modifier
                .matchParentSize()
                .drawBehind {
                    drawCircle(color = background.value)
                }
        )
        Icon(
            painter = painterResource(icon) ,
            contentDescription = label ,
            modifier = Modifier.size(size) ,
            tint = MaterialTheme.colorScheme.onBackground
        )
    }
}