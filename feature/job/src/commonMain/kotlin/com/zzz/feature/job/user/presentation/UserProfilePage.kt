package com.zzz.feature.job.user.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zzz.core.ui.presentation.components.VerticalSpace
import com.zzz.feature.job.user.presentation.components.ProfileActionCard
import com.zzz.feature.job.user.presentation.components.ProfileHeaderCard
import com.zzz.feature.job.user.presentation.viewmodel.ProfileEvents
import com.zzz.feature.job.user.presentation.viewmodel.UserProfileState
import com.zzz.feature.job.user.presentation.viewmodel.UserProfileViewModel
import org.koin.compose.viewmodel.koinViewModel
import placementapp.feature.job.generated.resources.Res
import placementapp.feature.job.generated.resources.edit
import placementapp.feature.job.generated.resources.filter
import placementapp.feature.job.generated.resources.gear
import placementapp.feature.job.generated.resources.logout
import placementapp.feature.job.generated.resources.outline_account_circle_24
import placementapp.feature.job.generated.resources.resume
import placementapp.feature.job.generated.resources.support

@Composable
fun UserAccountPageRoot(
    modifier: Modifier = Modifier,
    onProfileClick: () -> Unit,
    onThemeClick: () -> Unit,
    onAboutClick: () -> Unit,
    onLogoutClick: () -> Unit,
){
    val viewModel = koinViewModel<UserProfileViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    val events = viewModel.events

    LaunchedEffect(Unit){
        events.collect {
            when(it){
                ProfileEvents.LogOut -> {
                    onLogoutClick()
                }
            }
        }
    }

    UserAccountPage(
        modifier ,
        state = state ,
        onLogoutClick = {
            viewModel.clearTokens()
        },
        onThemeClick = onThemeClick,
        onAboutClick = onAboutClick,
        onProfileClick = onProfileClick
    )
}


@Composable
private fun UserAccountPage(
    modifier: Modifier = Modifier,
    state: UserProfileState,
    onProfileClick: () -> Unit,
    onThemeClick: () -> Unit,
    onAboutClick: () -> Unit,
    onLogoutClick: () -> Unit,
) {

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(
            top = 28.dp,
            bottom = 32.dp
        ),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        item {
            ProfileHeaderCard(
                state = state
            )
        }

        item {
            Spacer(Modifier.height(18.dp))
        }

        item {
            ProfileActionCard(
                icon = Res.drawable.outline_account_circle_24,
                title = "Profile",
                subtitle = "View your personal information",
                onClick = onProfileClick
            )
        }

        item {
            ProfileActionCard(
                icon = Res.drawable.gear,
                title = "Theme",
                subtitle = "Choose light or dark mode",
                onClick = onThemeClick
            )
        }

        item {
            ProfileActionCard(
                icon = Res.drawable.gear,
                title = "About Clave",
                subtitle = "App information",
                onClick = onAboutClick
            )
        }

        item {
            HorizontalDivider(
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f)
            )
        }

        item {
            ProfileActionCard(
                icon = Res.drawable.logout,
                title = "Log out",
                subtitle = "Sign out from your account",
                onClick = onLogoutClick
            )
        }

        item {
            Spacer(Modifier.height(8.dp))
        }

        item {
            VersionFooter()
        }

        item {
            Spacer(Modifier.height(40.dp))
        }

    }
}



@Composable
fun VersionFooter() {

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Version 1.0.0",
            fontSize = 13.sp,
            color = MaterialTheme.colorScheme.onBackground.copy(.55f)
        )

        Text(
            text = "Made with ❤ by Team Clave",
            fontSize = 13.sp,
            color = MaterialTheme.colorScheme.onBackground.copy(.55f)
        )
    }
}

//@Composable
//private fun UserAccountPage(
//    modifier: Modifier,
//    state : UserProfileState,
//    onLogOutClick : ()->Unit,
//    editProfile : ()->Unit,
//    editSettings : ()->Unit,
//    editPrefs : ()->Unit,
//    editResume : ()->Unit,
//){
//    Box(
//
//    ){
//        Column(
//            modifier
//                .fillMaxWidth()
//                .background(MaterialTheme.colorScheme.background)
//                .padding(16.dp)
//        ) {
//            //--------HEADER--------
//            ProfileHeaderCard(
//                Modifier.align(Alignment.CenterHorizontally),
//                state
//            )
//            VerticalSpace()
//
//
//            //--------ACTIONS--------
//            Column(
//                verticalArrangement = Arrangement.spacedBy(8.dp)
//            ) {
//                ProfileActionCard(
//                    icon = Res.drawable.outline_account_circle_24,
//                    actionText = "Profile",
//                    onClick = editProfile
//                )
//
//                ProfileActionCard(
//                    icon = Res.drawable.resume,
//                    actionText = "Resume",
//                    onClick = editResume
//                )
//
//                ProfileActionCard(
//                    icon = Res.drawable.filter,
//                    actionText = "Preferences",
//                    onClick = editPrefs
//                )
//
//                ProfileActionCard(
//                    icon = Res.drawable.gear,
//                    actionText = "Settings",
//                    onClick = editSettings
//                )
//            }
//
//            VerticalSpace(15.dp)
//            HorizontalDivider(
//                thickness = 1.5.dp
//            )
//            VerticalSpace(15.dp)
//
//            Column(
//                verticalArrangement = Arrangement.spacedBy(8.dp)
//            ) {
//                ProfileActionCard(
//                    icon = Res.drawable.edit ,
//                    actionText = "Change password" ,
//                    onClick = {}
//                )
//
//                ProfileActionCard(
//                    icon = Res.drawable.support ,
//                    actionText = "Support" ,
//                    onClick = {}
//                )
//
//                ProfileActionCard(
//                    icon = Res.drawable.logout ,
//                    actionText = "Log out" ,
//                    onClick = {
//                        onLogOutClick()
//                    }
//                )
//            }
//        }
//
//    }
//}