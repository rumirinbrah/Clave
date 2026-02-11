package com.zzz.feature.job.user.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zzz.core.ui.presentation.components.ImageComponent
import org.jetbrains.compose.resources.painterResource
import placementapp.feature.job.generated.resources.Recordify_logo
import placementapp.feature.job.generated.resources.Res
import placementapp.feature.job.generated.resources.outline_account_circle_24
import placementapp.feature.job.generated.resources.quality_badge

@Composable
internal fun ProfileActionCard(
    modifier: Modifier = Modifier,
    icon : Int,
    actionText : String,
    onClick : ()->Unit,
    enabled : Boolean = true,
){
    Row (
        modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Row (
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                painter = painterResource(Res.drawable.quality_badge),
                contentDescription = actionText
            )
            Image(
                painter = painterResource(Res.drawable.Recordify_logo),
                contentDescription = ""
            )
        }
    }
}
