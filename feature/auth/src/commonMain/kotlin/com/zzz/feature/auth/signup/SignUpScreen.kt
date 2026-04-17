package com.zzz.feature.auth.signup

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zzz.core.ui.presentation.components.GradientButton
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zzz.core.ui.domain.network.UIEvent
import com.zzz.core.ui.presentation.components.NormalTextField
import com.zzz.core.ui.presentation.components.VerticalSpace
import com.zzz.core.ui.util.ClaveLogger.logD
import com.zzz.feature.auth.login.LoginEvents
import com.zzz.feature.auth.login.RoleToggle
import com.zzz.feature.auth.login.authTabs
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SignUpScreen(
    onLoginClick: () -> Unit,
    onNavigateToOtp: (String) -> Unit,
    viewModel: SignupViewModel = koinViewModel()
) {

    val pagerState = rememberPagerState { authTabs.size }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val events = viewModel.events

    LaunchedEffect(pagerState.currentPage) {
        viewModel.onRoleChange(
            isAdmin = pagerState.currentPage == 1
        )
    }

    LaunchedEffect(events){
        events.collect{event->
            when(event){
                is LoginEvents.OtpVerification -> {
                    logD {
                        "OTP verification req"
                    }
                    onNavigateToOtp(event.email)
                }
                is UIEvent.Error ->{
                    logD {
                        event.errorMsg
                    }
                }
                is UIEvent.Success ->{
                    logD {
                        "Succees"
                    }
                }
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

//            Spacer(modifier = Modifier.height(40.dp))
//
//            Box(
//                modifier = Modifier
//                    .size(80.dp)
//                    .background(
//                        MaterialTheme.colorScheme.surface,
//                        RoundedCornerShape(20.dp)
//                    ),
//                contentAlignment = Alignment.Center
//            ) {
//                Text(
//                    text = "C",
//                    fontSize = 36.sp,
//                    fontWeight = FontWeight.Bold,
//                    color = MaterialTheme.colorScheme.primary
//                )
//            }
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            Text(
//                text = "WELCOME TO CLAVE",
//                style = MaterialTheme.typography.titleLarge,
//                fontWeight = FontWeight.Bold
//            )
//
//            Spacer(modifier = Modifier.height(8.dp))
//
//            Text(
//                text = "Let's set you up!",
//                style = MaterialTheme.typography.bodyMedium,
//                color = MaterialTheme.colorScheme.onBackground.copy(0.7f)
//            )
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            RoleToggle(
//                pagerState = pagerState,
//                tabs = authTabs
//            )
//
//            Spacer(modifier = Modifier.height(24.dp))
//
//            VerticalSpace(24.dp)

            AnimatedVisibility(uiState.errorMsg != null) {
                Text(
                    uiState.errorMsg ?: "",
                    color = MaterialTheme.colorScheme.error
                )
            }

            VerticalSpace(24.dp)

            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxWidth(),
                userScrollEnabled = false
            ) { page ->

                when (page) {

                    0 -> { // Student
                        Column {

                            NormalTextField(
                                value = uiState.rollNo,
                                onValueChange = { viewModel.onRollNoChange(it) },
                                placeholder = "Enter your Roll no"
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            NormalTextField(
                                value = uiState.email,
                                onValueChange = { viewModel.onEmailChange(it) },
                                placeholder = "Enter your Mobile no"
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            NormalTextField(
                                value = uiState.password,
                                onValueChange = { viewModel.onPwdChange(it) },
                                placeholder = "Enter your Password"
                            )

                        }
                    }

                    1 -> { // Coordinator
                        Column(
                            modifier = Modifier.fillMaxWidth()
                        ) {

                            NormalTextField(
                                value = uiState.email,
                                onValueChange = { viewModel.onEmailChange(it) },
                                placeholder = "Enter your Email"
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            NormalTextField(
                                value = uiState.password,
                                onValueChange = { viewModel.onPwdChange(it) },
                                placeholder = "Enter your Password"
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row {
                Text(text = "Have an account? ")
                Text(
                    text = "Login",
                    color = MaterialTheme.colorScheme.error,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable { onLoginClick() }
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            GradientButton(
                text = "Create account",
                onClick = {
                    viewModel.createAccount()
                }
            )

            Spacer(modifier = Modifier.height(24.dp))
        }

        if (uiState.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.3f)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}