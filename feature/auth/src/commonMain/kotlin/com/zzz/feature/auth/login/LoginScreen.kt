package com.zzz.feature.auth.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zzz.core.ui.presentation.components.ClaveDefaults
import com.zzz.core.ui.presentation.components.GradientButton
import com.zzz.core.ui.presentation.components.NormalTextField
import com.zzz.core.ui.presentation.components.VerticalSpace
import kotlinx.coroutines.launch
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun LoginScreen(
    onRegister: () -> Unit,
    viewModel: LoginViewModel = viewModel()
) {

    val pagerState = rememberPagerState { authTabs.size }

    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = ClaveDefaults.CONTAINER_PADDING),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        VerticalSpace(40.dp)

        // Logo
        Box(
            modifier = Modifier
                .size(80.dp)
                .background(
                    MaterialTheme.colorScheme.surface,
                    RoundedCornerShape(20.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "C",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }

        VerticalSpace(16.dp)

        Text(
            text = "WELCOME TO CLAVE",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        VerticalSpace(8.dp)

        Text(
            text = "Using the app as",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground.copy(0.7f)
        )

        VerticalSpace(16.dp)

        RoleToggle(
            pagerState = pagerState,
            tabs = authTabs
        )

        VerticalSpace(24.dp)

        HorizontalPager(
            state = pagerState,
            userScrollEnabled = true,
            modifier = Modifier.fillMaxWidth()
        ) { page ->

            when (page) {

                0 -> { // Student
                    Column (
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ){

                        NormalTextField(
                            value = uiState.rollNo,
                            onValueChange = { viewModel.onRollNoChange(it) },
                            placeholder = "Enter your Roll no"
                        )


                        NormalTextField(
                            value = uiState.mobileNo,
                            onValueChange = { viewModel.onMobileNoChange(it) },
                            placeholder = "Enter your Mobile no"
                        )

                        NormalTextField(
                            value = uiState.mobileNo,
                            onValueChange = { viewModel.onPwdChange(it) },
                            placeholder = "Enter password"
                        )
                    }
                }

                1 -> { // Coordinator

                    Column{

                        NormalTextField(
                            value = uiState.rollNo,
                            onValueChange = { viewModel.onRollNoChange(it) },
                            placeholder = "Enter your Roll no"
                        )

                        VerticalSpace(16.dp)

                        NormalTextField(
                            value = uiState.mobileNo,
                            onValueChange = { viewModel.onMobileNoChange(it) },
                            placeholder = "Enter your Mobile no"
                        )
                    }
                }
            }
        }

        VerticalSpace(24.dp)

        Row {
            Text(text = "New User? ")
            Text(
                text = "Register",
                color = MaterialTheme.colorScheme.error,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable(
                    indication = null,
                    interactionSource = null
                ) {
                    onRegister()
                }
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        GradientButton(
            text = "Login",
            onClick = { }
        )

        VerticalSpace(24.dp)
    }
}

@Composable
internal fun RoleToggle(
    pagerState: PagerState,
    tabs: List<AuthTabItem>
) {
    val scope = rememberCoroutineScope()

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.onSurface, RoundedCornerShape(30.dp))
            .padding(4.dp)
    ) {

        val itemWidth = maxWidth / 2

        Row {
            tabs.forEachIndexed { index, tab ->

                val isSelected = pagerState.currentPage == index

                RoleItem(
                    text = tab.title,
                    isSelected = isSelected,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    width = itemWidth
                )
            }
        }
    }
}

@Composable
internal fun RoleItem(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    width: Dp
) {
    Box(
        modifier = Modifier
            .width(width)
            .background(
                if (isSelected) MaterialTheme.colorScheme.surface else Color.Transparent,
                RoundedCornerShape(26.dp)
            )
            .clickable { onClick() }
            .padding(vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = if (isSelected)
                MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onPrimary,
            fontWeight = FontWeight.SemiBold
        )
    }
}

