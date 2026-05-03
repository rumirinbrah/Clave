package com.zzz.feature.auth.login

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zzz.core.ui.domain.network.UIEvent
import com.zzz.core.ui.presentation.components.ClaveDefaults
import com.zzz.core.ui.presentation.components.GradientButton
import com.zzz.core.ui.presentation.components.NormalTextField
import com.zzz.core.ui.presentation.components.VerticalSpace
import com.zzz.core.ui.util.ClaveLogger.logD
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun LoginScreen(
    onRegister: () -> Unit,
    navToHome : ()->Unit,
    viewModel: LoginViewModel = koinViewModel()
) {

    val pagerState = rememberPagerState { authTabs.size }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val events = viewModel.events

    val (rollFocus, emailFocus, passwordFocus) = FocusRequester.createRefs()

    val focusManager = LocalFocusManager.current

    LaunchedEffect(events){
        events.collect{event->
                    println(event)
            when(event){
                is LoginEvents.OtpVerification -> {
                    logD {
                        "OTP verification req"
                    }
                }
                LoginEvents.AlreadyLoggedIn->{
                    println("ALREADY WALA EVENT")
                   navToHome()
                }
                is UIEvent.Error ->{
                    logD {
                        event.errorMsg
                    }
                }
                is UIEvent.Success ->{
                    navToHome()
                    logD {
                        "Succees"
                    }
                }
            }
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = ClaveDefaults.CONTAINER_PADDING),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        VerticalSpace(24.dp)

        AnimatedVisibility(uiState.errorMsg!=null){
            Text(
                uiState.errorMsg ?: "",
                color = MaterialTheme.colorScheme.error
            )
        }

        VerticalSpace(24.dp)

        Column (
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){

            NormalTextField(
                value = uiState.rollNo,
                onValueChange = { viewModel.onRollNoChange(it) },
                placeholder = "Enter your Roll no",
                modifier = Modifier.focusRequester(rollFocus),
                imeAction = ImeAction.Next,
                onImeAction = {
                    emailFocus.requestFocus()
                }
            )


            NormalTextField(
                value = uiState.email,
                onValueChange = { viewModel.onEmailChange(it) },
                placeholder = "Enter your Email",
                imeAction = ImeAction.Next,
                modifier = Modifier.focusRequester(emailFocus),
                onImeAction = {
                    passwordFocus.requestFocus()
                }
            )

            NormalTextField(
                value = uiState.password,
                onValueChange = { viewModel.onPwdChange(it) },
                placeholder = "Enter password",
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password,
                modifier = Modifier.focusRequester(passwordFocus),
                onImeAction = {
                    focusManager.clearFocus()
                    viewModel.login()
                }
            )
        }

        /*
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
                            placeholder = "Enter your Roll no",
                            modifier = Modifier.focusRequester(rollFocus),
                            imeAction = ImeAction.Next,
                            onImeAction = {
                                emailFocus.requestFocus()
                            }
                        )


                        NormalTextField(
                            value = uiState.email,
                            onValueChange = { viewModel.onEmailChange(it) },
                            placeholder = "Enter your Email",
                            imeAction = ImeAction.Next,
                            modifier = Modifier.focusRequester(emailFocus),
                            onImeAction = {
                                passwordFocus.requestFocus()
                            }
                        )

                        NormalTextField(
                            value = uiState.password,
                            onValueChange = { viewModel.onPwdChange(it) },
                            placeholder = "Enter password",
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Password,
                            modifier = Modifier.focusRequester(passwordFocus),
                            onImeAction = {
                                focusManager.clearFocus()
                                viewModel.login()
                            }
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
                            value = uiState.email,
                            onValueChange = { viewModel.onEmailChange(it) },
                            placeholder = "Enter your Email"
                        )
                    }
                }
            }
        }*/

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
            onClick = {
                viewModel.login()
                //TEST
//                navToHome()
            }
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

