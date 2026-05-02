package com.zzz.placement.nav

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.zzz.feature.auth.otp.VerifyOtpScreen
import com.zzz.feature.auth.login.LoginScreen
import com.zzz.feature.auth.signup.SignUpScreen
import com.zzz.feature.job.details.presentation.JobDescriptionRoot
import com.zzz.feature.job.home.presentation.JobHomePageRoot
import com.zzz.feature.job.user.UpdateProfileRoot
import com.zzz.feature.job.user.presentation.UserAccountPageRoot

fun NavGraphBuilder.authGraph(
    navController: NavController,
    navBarVisibilityChange : (visible : Boolean) ->Unit
){
    navigation<Screen.Auth>(
        startDestination = Screen.Auth.LogIn
    ){
        composable<Screen.Auth.LogIn> {backstack->
//            val parentEntry = remember(backstack) {
//                navController.getBackStackEntry(Screen.Auth)
//            }
            LaunchedEffect(Unit){
                navBarVisibilityChange(false)
            }
            LoginScreen(
                onRegister = {
                    navController.navigate(Screen.Auth.SignUp){
                        navController.popBackStack()
                    }
                },
                navToHome = {
                    navController.navigate(Screen.Home){
                        navController.popBackStack()
                    }
                },
                onNavigateToOtp = { userId->
                    navController.navigate(Screen.Auth.VerifyOtp(userId))
                }
            )

        }
        composable<Screen.Auth.SignUp> {
            LaunchedEffect(Unit){
                navBarVisibilityChange(false)
            }
            SignUpScreen(
                onLoginClick = {
                    navController.navigate(Screen.Auth.LogIn){
                        navController.popBackStack()
                    }
                },
                onNavigateToOtp = { email->
                    navController.navigate(Screen.Auth.VerifyOtp(email))
                }
            )
        }

        composable<Screen.Auth.VerifyOtp> { backStack ->

            LaunchedEffect(Unit) {
                navBarVisibilityChange(false)
            }

            val route = backStack.toRoute<Screen.Auth.VerifyOtp>()
            VerifyOtpScreen(
                email = route.email,
                onOtpVerified = {
                    navController.navigate(Screen.Home) {
                        popUpTo(Screen.Auth) { inclusive = true }
                    }
                }
            )
        }
    }
}
fun NavGraphBuilder.homeGraph(
    navController: NavController,
    navBarVisibilityChange : (visible : Boolean) ->Unit
){
    navigation<Screen.Home>(
        startDestination = Screen.Home.HomeFeed
    ){
        composable<Screen.Home.HomeFeed> {
            LaunchedEffect(Unit){
                navBarVisibilityChange(true)
            }
            JobHomePageRoot(
                onJobClick = {
                    navController.navigate(Screen.Home.JobDescription(it))
                }
            )
        }
        composable<Screen.Home.JobDescription> {
            LaunchedEffect(Unit){
                navBarVisibilityChange(false)
            }
            val route = it.toRoute<Screen.Home.JobDescription>()
            JobDescriptionRoot(
                onBack = {
                    navController.navigateUp()
                },
                jobId = route.jobId
            )
        }
    }
}

fun NavGraphBuilder.accountGraph(
    navController: NavController,
    navBarVisibilityChange : (visible : Boolean) ->Unit
) {
    navigation<Screen.Account>(
        startDestination = Screen.Account.AccountPage
    ) {
        //--------ACC--------
        composable<Screen.Account.AccountPage> {
            LaunchedEffect(Unit){
                navBarVisibilityChange(true)
            }

            UserAccountPageRoot(
                onLogOut = {
                    navController.navigate(Screen.Auth) {
                        popUpTo(Screen.Home) {
                            inclusive = true
                        }
                    }
                } ,
                editProfile =  {
                    navController.navigate(Screen.Account.Profile)
                },
                editSettings =  {
                    navController.navigate(Screen.Account.Settings)
                },
                editPrefs =  {
                    navController.navigate(Screen.Account.Preferences)
                },
                editResume =  {
                    navController.navigate(Screen.Account.Resume)
                },
            )
        }
        //--------SETT--------
        composable<Screen.Account.Settings> {
            LaunchedEffect(Unit){
                navBarVisibilityChange(false)
            }
            Box(
                Modifier.fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ){
                Text("Settings")
            }
        }
        //--------PREFS--------
        composable<Screen.Account.Preferences> {
            LaunchedEffect(Unit){
                navBarVisibilityChange(false)
            }
            Box(
                Modifier.fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ){
                Text("Preferences")
            }
        }
        //--------RESUME--------
        composable<Screen.Account.Resume> {
            LaunchedEffect(Unit){
                navBarVisibilityChange(false)
            }
            Box(
                Modifier.fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ){
                Text("Resume")
            }
        }
        //--------PROF--------
        composable<Screen.Account.Profile> {
            LaunchedEffect(Unit){
                navBarVisibilityChange(false)
            }
            UpdateProfileRoot(
                onBack = {
                     navController.navigateUp()
                }
            )
        }
    }
}

