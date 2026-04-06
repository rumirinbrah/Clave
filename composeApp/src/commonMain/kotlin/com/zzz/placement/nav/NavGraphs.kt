package com.zzz.placement.nav

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.zzz.feature.auth.login.LoginScreen
import com.zzz.feature.auth.signup.SignUpScreen
import com.zzz.feature.job.details.presentation.JobDescriptionPage
import com.zzz.feature.job.details.presentation.JobDescriptionRoot
import com.zzz.feature.job.details.presentation.JobDescriptionViewModel
import com.zzz.feature.job.home.presentation.JobHomePageRoot

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