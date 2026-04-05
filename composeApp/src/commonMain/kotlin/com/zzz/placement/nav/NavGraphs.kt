package com.zzz.placement.nav

import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.zzz.feature.auth.SignUpScreen
import com.zzz.feature.auth.login.LoginScreen

fun NavGraphBuilder.authGraph(
    navController: NavController
){
    navigation<Screen.Auth>(
        startDestination = Screen.Auth.LogIn
    ){
        composable<Screen.Auth.LogIn> {backstack->
//            val parentEntry = remember(backstack) {
//                navController.getBackStackEntry(Screen.Auth)
//            }
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