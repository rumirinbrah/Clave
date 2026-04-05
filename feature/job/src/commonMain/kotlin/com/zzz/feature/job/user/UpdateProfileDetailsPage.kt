package com.zzz.feature.job.user

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zzz.core.ui.presentation.components.ActionHeader
import com.zzz.core.ui.presentation.components.GradientButton
import com.zzz.core.ui.presentation.components.NormalTextField
import com.zzz.core.ui.presentation.components.VerticalSpace
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
    viewModel: UserProfileViewModel = koinViewModel()
){
    val state by viewModel.state.collectAsStateWithLifecycle()
    UpdateProfileDetailsPage(modifier,state)
}

@Composable
internal fun UpdateProfileDetailsPage(
    modifier : Modifier = Modifier,
    profileState: UserProfileState
){
    val viewModel = koinViewModel<UpdateProfileViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        ActionHeader(
            title = "Update profile",
            onBack = {

            },
            icon = Res.drawable.baseline_arrow_back_24
        )
        VerticalSpace()

        NormalTextField(
            value = state.name,
            onValueChange = {
                viewModel.onNameChange(it)
            },
            placeholder ="Your name",
            titleText = "Name"
        )
        NormalTextField(
            value = state.branch,
            onValueChange = {
                viewModel.onBranchChange(it)

            } ,
            placeholder ="Your branch",
            titleText = "Branch"
        )
        NormalTextField(
            value = state.rollNo,
            onValueChange = {
                viewModel.onRollNoChange(it)
            },
            placeholder ="Your roll number",
            titleText = "Roll No"
        )

        VerticalSpace()

        GradientButton(
            text = "Update details",
            onClick = {
                viewModel.updateProfile(profileState.id,profileState)
            }
        )
    }

}




