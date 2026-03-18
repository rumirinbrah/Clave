package com.zzz.feature.job.user.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zzz.core.ui.presentation.components.VerticalSpace
import com.zzz.feature.job.user.presentation.components.ProfileActionCard
import com.zzz.feature.job.user.presentation.components.ProfileHeaderCard
import placementapp.feature.job.generated.resources.Res
import placementapp.feature.job.generated.resources.edit
import placementapp.feature.job.generated.resources.filter
import placementapp.feature.job.generated.resources.gear
import placementapp.feature.job.generated.resources.logout
import placementapp.feature.job.generated.resources.outline_account_circle_24
import placementapp.feature.job.generated.resources.resume
import placementapp.feature.job.generated.resources.support

@Composable
fun UserProfilePageRoot(
    modifier: Modifier = Modifier
){
    UserProfilePage(
        modifier
    )
}
@Composable
fun UserProfilePage(
    modifier: Modifier
){
    Box(

    ){
        Column(
            modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp)
        ) {
            //--------HEADER--------
            ProfileHeaderCard(
                Modifier.align(Alignment.CenterHorizontally)
            )
            VerticalSpace()


            //--------ACTIONS--------
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ProfileActionCard(
                    icon = Res.drawable.outline_account_circle_24,
                    actionText = "Profile",
                    onClick = {}
                )

                ProfileActionCard(
                    icon = Res.drawable.resume,
                    actionText = "Resume",
                    onClick = {}
                )

                ProfileActionCard(
                    icon = Res.drawable.filter,
                    actionText = "Preferences",
                    onClick = {}
                )

                ProfileActionCard(
                    icon = Res.drawable.gear,
                    actionText = "Settings",
                    onClick = {}
                )
            }

            VerticalSpace(15.dp)
            HorizontalDivider(
                thickness = 1.5.dp
            )
            VerticalSpace(15.dp)

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ProfileActionCard(
                    icon = Res.drawable.edit ,
                    actionText = "Change password" ,
                    onClick = {}
                )

                ProfileActionCard(
                    icon = Res.drawable.support ,
                    actionText = "Support" ,
                    onClick = {}
                )

                ProfileActionCard(
                    icon = Res.drawable.logout ,
                    actionText = "Log out" ,
                    onClick = {}
                )
            }
        }

    }
}