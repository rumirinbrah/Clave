package com.zzz.feature.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zzz.core.ui.presentation.components.ClaveDefaults
import com.zzz.core.ui.presentation.components.VerticalSpace
import com.zzz.feature.auth.login.AuthTabItem
import com.zzz.feature.auth.login.LoginScreen
import com.zzz.feature.auth.login.RoleToggle
import com.zzz.feature.auth.login.authTabs
import com.zzz.feature.auth.signup.SignUpScreen
import kotlinx.coroutines.launch

@Composable
fun AuthRoot(
    modifier: Modifier = Modifier,
    navToHome : ()->Unit,
){
    AuthPage(
        modifier,
        navToHome = navToHome
    )
}
val authPagerTabs = listOf(
    AuthTabItem("LogIn",0),
    AuthTabItem("SignUp",1),
)
@Composable
private fun AuthPage(
    modifier: Modifier = Modifier,
    navToHome : ()->Unit,
){
    val pagerState = rememberPagerState { authTabs.size }
    val scope = rememberCoroutineScope()

    Column(
        modifier
            .fillMaxSize()
            .padding(ClaveDefaults.CONTAINER_PADDING),
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
            text = "Choose action",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground.copy(0.7f)
        )

        VerticalSpace(16.dp)

        RoleToggle(
            pagerState = pagerState,
            tabs = authPagerTabs
        )
        HorizontalPager(
            state = pagerState,
            userScrollEnabled = true,
            modifier = Modifier.fillMaxWidth()
        ) {page->
            when(page){
                0->{
                    LoginScreen(
                        onRegister = {
                            scope.launch {
                                pagerState.animateScrollToPage(1)
                            }
                        },
                        navToHome = navToHome
                    )
                }
                1->{
                    SignUpScreen(
                        onLoginClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(0)
                            }
                        }
                    )
                }
            }

        }
    }
}