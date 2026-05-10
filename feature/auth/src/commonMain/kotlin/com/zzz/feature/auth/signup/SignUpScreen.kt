package com.zzz.feature.auth.signup

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.rememberScrollableState
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
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
    onLoginClick: () -> Unit ,
    onNavigateToOtp: (String) -> Unit ,
    viewModel: SignupViewModel = koinViewModel()
) {

    val pagerState = rememberPagerState { authTabs.size }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val events = viewModel.events

    val (rollFocus, emailFocus, nameFocus, passwordFocus) = FocusRequester.createRefs()
    val focusManager = LocalFocusManager.current

    LaunchedEffect(pagerState.currentPage) {
        viewModel.onRoleChange(
            isAdmin = pagerState.currentPage == 1
        )
    }

    LaunchedEffect(events) {
        events.collect { event ->
            when (event) {
                is LoginEvents.OtpVerification -> {
                    logD {
                        "OTP verification req"
                    }
                    onNavigateToOtp(event.email)
                }

                is UIEvent.Error -> {
                    logD {
                        event.errorMsg
                    }
                }

                is UIEvent.Success -> {
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
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(horizontal = 20.dp) ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            AnimatedVisibility(uiState.errorMsg != null) {
                Text(
                    uiState.errorMsg ?: "" ,
                    color = MaterialTheme.colorScheme.error
                )
            }

            VerticalSpace(24.dp)

            Column {

                NormalTextField(
                    value = uiState.rollNo ,
                    onValueChange = { viewModel.onRollNoChange(it) } ,
                    placeholder = "Enter your Roll no",
                    modifier = Modifier.focusRequester(rollFocus),
                    imeAction = ImeAction.Next,
                    onImeAction = {
                        emailFocus.requestFocus()
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                NormalTextField(
                    value = uiState.email ,
                    onValueChange = { viewModel.onEmailChange(it) } ,
                    placeholder = "Enter your Email",
                    imeAction = ImeAction.Next,
                    modifier = Modifier.focusRequester(emailFocus),
                    onImeAction = {
                        passwordFocus.requestFocus()
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                NormalTextField(
                    value = uiState.name ,
                    onValueChange = {
                        viewModel.onNameChange(it)
                    } ,
                    placeholder = "Enter your name",
                    imeAction = ImeAction.Next,
                    modifier = Modifier.focusRequester(nameFocus),
                    onImeAction = {
                        passwordFocus.requestFocus()
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))
                var expanded by remember { mutableStateOf(false) }
                Box {
                    Button(
                        onClick = {
                            expanded = true
                        },
                        shape = MaterialTheme.shapes.small
                    ) {
                        Text(
                            "Course ${uiState.selectedCourse?:""}"
                        )
                    }

                    DropdownMenu(
                        expanded = expanded ,
                        onDismissRequest = { expanded = false }
                    ) {
                        uiState.courses.forEach {
                            DropdownMenuItem(
                                text = { Text(it) } ,
                                onClick = {
//                                            selected = "Option 1"
                                    viewModel.onCourseSelect(it)
                                    expanded = false
                                }
                            )
                        }
//                                    DropdownMenuItem(
//                                        text = { Text("Option 1") } ,
//                                        onClick = {
////                                            selected = "Option 1"
//                                            expanded = false
//                                        }
//                                    )
//                                    DropdownMenuItem(
//                                        text = { Text("Option 2") } ,
//                                        onClick = {
////                                            selected = "Option 2"
//                                            expanded = false
//                                        }
//                                    )
                    }
                }



                Spacer(modifier = Modifier.height(16.dp))

                NormalTextField(
                    value = uiState.password ,
                    onValueChange = { viewModel.onPwdChange(it) } ,
                    placeholder = "Enter your Password",
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Password,
                    modifier = Modifier.focusRequester(passwordFocus),
                    onImeAction = {
                        focusManager.clearFocus()
                        viewModel.createAccount()
                    }
                )

            }
            /*
            HorizontalPager(
                state = pagerState ,
                modifier = Modifier.fillMaxWidth() ,
                userScrollEnabled = false
            ) { page ->

                when (page) {

                    0 -> { // Student
                        Column {

                            NormalTextField(
                                value = uiState.rollNo ,
                                onValueChange = { viewModel.onRollNoChange(it) } ,
                                placeholder = "Enter your Roll no",
                                modifier = Modifier.focusRequester(rollFocus),
                                imeAction = ImeAction.Next,
                                onImeAction = {
                                    emailFocus.requestFocus()
                                }
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            NormalTextField(
                                value = uiState.email ,
                                onValueChange = { viewModel.onEmailChange(it) } ,
                                placeholder = "Enter your Email",
                                imeAction = ImeAction.Next,
                                modifier = Modifier.focusRequester(emailFocus),
                                onImeAction = {
                                    passwordFocus.requestFocus()
                                }
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            NormalTextField(
                                value = uiState.name ,
                                onValueChange = {
                                    viewModel.onNameChange(it)
                                } ,
                                placeholder = "Enter your name",
                                imeAction = ImeAction.Next,
                                modifier = Modifier.focusRequester(nameFocus),
                                onImeAction = {
                                    passwordFocus.requestFocus()
                                }
                            )

                            Spacer(modifier = Modifier.height(16.dp))
                            var expanded by remember { mutableStateOf(false) }
                            Box {
                                Button(
                                    onClick = {
                                        expanded = true
                                    },
                                    shape = MaterialTheme.shapes.small
                                ) {
                                    Text(
                                        "Course ${uiState.selectedCourse?:""}"
                                    )
                                }

                                DropdownMenu(
                                    expanded = expanded ,
                                    onDismissRequest = { expanded = false }
                                ) {
                                    uiState.courses.forEach {
                                        DropdownMenuItem(
                                            text = { Text(it) } ,
                                            onClick = {
//                                            selected = "Option 1"
                                                viewModel.onCourseSelect(it)
                                                expanded = false
                                            }
                                        )
                                    }
//                                    DropdownMenuItem(
//                                        text = { Text("Option 1") } ,
//                                        onClick = {
////                                            selected = "Option 1"
//                                            expanded = false
//                                        }
//                                    )
//                                    DropdownMenuItem(
//                                        text = { Text("Option 2") } ,
//                                        onClick = {
////                                            selected = "Option 2"
//                                            expanded = false
//                                        }
//                                    )
                                }
                            }



                            Spacer(modifier = Modifier.height(16.dp))

                            NormalTextField(
                                value = uiState.password ,
                                onValueChange = { viewModel.onPwdChange(it) } ,
                                placeholder = "Enter your Password",
                                imeAction = ImeAction.Done,
                                keyboardType = KeyboardType.Password,
                                modifier = Modifier.focusRequester(passwordFocus),
                                onImeAction = {
                                    focusManager.clearFocus()
                                    viewModel.createAccount()
                                }
                            )

                        }
                    }

                    1 -> { // Coordinator
                        Column(
                            modifier = Modifier.fillMaxWidth()
                        ) {

                            NormalTextField(
                                value = uiState.email ,
                                onValueChange = { viewModel.onEmailChange(it) } ,
                                placeholder = "Enter your Email"
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            NormalTextField(
                                value = uiState.password ,
                                onValueChange = { viewModel.onPwdChange(it) } ,
                                placeholder = "Enter your Password"
                            )
                        }
                    }
                }
            }*/

            Spacer(modifier = Modifier.height(24.dp))

            Row {
                Text(text = "Have an account? ")
                Text(
                    text = "Login" ,
                    color = MaterialTheme.colorScheme.error ,
                    fontWeight = FontWeight.Bold ,
                    modifier = Modifier.clickable { onLoginClick() }
                )
            }

            VerticalSpace()

            GradientButton(
                text = "Create account" ,
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
                    .background(Color.Black.copy(alpha = 0.3f)) ,
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}