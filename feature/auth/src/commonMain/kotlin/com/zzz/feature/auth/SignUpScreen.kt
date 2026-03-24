package com.zzz.feature.auth

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
import com.zzz.core.ui.presentation.components.NormalTextField
import com.zzz.feature.auth.login.RoleToggle
import com.zzz.feature.auth.login.authTabs

@Composable
fun SignUpScreen(
    onLoginClick: () -> Unit
) {

    val pagerState = rememberPagerState { authTabs.size }

    var rollNo by remember { mutableStateOf("") }
    var mobileNo by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(40.dp))

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

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "WELCOME TO CLAVE",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Let's set you up!",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground.copy(0.7f)
        )

        Spacer(modifier = Modifier.height(16.dp))

        RoleToggle(
            pagerState = pagerState,
            tabs = authTabs
        )

        Spacer(modifier = Modifier.height(24.dp))

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth(),
            userScrollEnabled = false
        ) { page ->

            when (page) {

                0 -> { // Student
                    Column {

                        NormalTextField(
                            value = name,
                            onValueChange = { name = it },
                            placeholder = "Enter your Name"
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        NormalTextField(
                            value = rollNo,
                            onValueChange = { rollNo = it },
                            placeholder = "Enter your RollNo"
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        NormalTextField(
                            value = mobileNo,
                            onValueChange = { mobileNo = it },
                            placeholder = "Enter your MobileNo"
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        NormalTextField(
                            value = password,
                            onValueChange = { password = it },
                            placeholder = "Enter your Password"
                        )
                    }
                }

                1 -> { // Coordinator
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        NormalTextField(
                            value = name,
                            onValueChange = { name = it },
                            placeholder = "Enter your Name"
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        NormalTextField(
                            value = mobileNo,
                            onValueChange = { mobileNo = it },
                            placeholder = "Enter your MobileNo"
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        NormalTextField(
                            value = password,
                            onValueChange = { password = it },
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
            onClick = { }
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}