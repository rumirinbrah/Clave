package com.zzz.feature.job.user

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zzz.core.ui.domain.network.UIEvent
import com.zzz.core.ui.presentation.components.ActionHeader
import com.zzz.core.ui.presentation.components.GradientButton
import com.zzz.core.ui.presentation.components.NormalTextField
import com.zzz.core.ui.presentation.components.VerticalSpace
import com.zzz.feature.job.user.presentation.viewmodel.ProfileEvents
import com.zzz.feature.job.user.presentation.viewmodel.UpdateProfileViewModel
import com.zzz.feature.job.user.presentation.viewmodel.UserProfileState
import com.zzz.feature.job.user.presentation.viewmodel.UserProfileViewModel
import org.koin.compose.viewmodel.koinViewModel
import placementapp.feature.job.generated.resources.Res
import placementapp.feature.job.generated.resources.baseline_arrow_back_24
import placementapp.feature.job.generated.resources.outline_arrow_forward_ios_24

@Composable
fun UpdateProfileRoot(
    modifier : Modifier = Modifier,
    viewModel: UserProfileViewModel = koinViewModel(),
    onBack : ()->Unit
){
    val state by viewModel.state.collectAsStateWithLifecycle()
    ProfileScreen(
        modifier = modifier ,
        state = state,
        onBack = onBack
    )
}

@Composable
private fun ProfileScreen(
    state: UserProfileState,
    modifier: Modifier = Modifier,
    onBack: () -> Unit
) {

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(
            top = 24.dp,
            bottom = 32.dp
        ),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        item {
            Text(
                text = "Profile",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        }

        item {
            ProfileInfoCard(
                title = "Name",
                value = state.name
            )
        }

        item {
            ProfileInfoCard(
                title = "Roll no",
                value = state.rollNo
            )
        }

        item {
            ProfileInfoCard(
                title = "Branch",
                value = state.branch
            )
        }

    }
}

@Composable
fun ProfileInfoCard(
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .dropShadow(
                shape = RoundedCornerShape(18.dp),
                shadow = Shadow(
                    radius = 3.dp,
                    spread = 2.dp,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.18f),
                    offset = DpOffset(2.dp, 2.dp)
                )
            )
            .clip(RoundedCornerShape(18.dp))
            .background(MaterialTheme.colorScheme.surface)
            .padding(18.dp)
    ) {

        Text(
            text = title,
            fontSize = 13.sp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )

        Spacer(Modifier.height(6.dp))

        Text(
            text = value,
            fontSize = 17.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

//@Composable
//private fun UpdateProfileDetailsPage(
//    modifier : Modifier = Modifier,
//    profileState: UserProfileState,
//    onBack : ()->Unit
//){
//    val viewModel = koinViewModel<UpdateProfileViewModel>()
//    val state by viewModel.state.collectAsStateWithLifecycle()
//    val events = viewModel.events
//
//    LaunchedEffect(Unit){
//        events.collect {
//            when(it){
//                UIEvent.Success -> {
//                   onBack()
//                }
//                is UIEvent.Error -> {
//
//                }
//            }
//        }
//    }
//
//    Column(
//        modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.spacedBy(8.dp)
//    ) {
//        ActionHeader(
//            title = "Profile",
//            onBack = {
//                onBack()
//            },
//            icon = Res.drawable.baseline_arrow_back_24
//        )
//        VerticalSpace()
//
//        NormalTextField(
//            value = state.name,
//            onValueChange = {
//                viewModel.onNameChange(it)
//            },
//            placeholder = profileState.name,
//            titleText = "Name"
//        )
//        NormalTextField(
//            value = state.branch,
//            onValueChange = {
//                viewModel.onBranchChange(it)
//
//            } ,
//            placeholder =profileState.branch,
//            titleText = "Branch"
//        )
//        NormalTextField(
//            value = state.rollNo,
//            onValueChange = {
//                viewModel.onRollNoChange(it)
//            },
//            placeholder =profileState.rollNo,
//            titleText = "Roll No"
//        )
//
//        VerticalSpace()
//
//        GradientButton(
//            text = "Update details",
//            onClick = {
//                viewModel.updateProfile(profileState.id,profileState)
//            }
//        )
//    }
//
//}
