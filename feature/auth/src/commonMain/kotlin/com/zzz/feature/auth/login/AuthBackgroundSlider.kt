package com.zzz.feature.auth.login

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import placementapp.feature.auth.generated.resources.Res
import placementapp.feature.auth.generated.resources.auth_slider

@Composable
fun AuthBackgroundSlider(
    modifier: Modifier = Modifier,
    page : Int,
){
    val bich = painterResource(Res.drawable.auth_slider)

    val animationFraction = animateFloatAsState(
        targetValue = if (page==0) 0f else 1f ,
        animationSpec = tween(1000)
    )

    Canvas(
        Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(300.dp)
    ){
        with(bich) {
            val scale = size.width / intrinsicSize.width
            val scaledHeight = intrinsicSize.height * scale
            translate(
                top = (size.height - scaledHeight) * (animationFraction.value)
            ) {
                draw(
                    Size(size.width , scaledHeight)
                )
            }

        }
    }

}