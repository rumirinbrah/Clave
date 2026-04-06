package com.zzz.placement.nav

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.zzz.feature.job.home.presentation.JobHomePageRoot
import com.zzz.feature.job.user.presentation.UserProfilePageRoot

val bottomRoutes = listOf(
    Screen.Home,
    Screen.Jobs,
    Screen.Community,
    Screen.Account,
)

/**
 * Entry point
 * @author zyzz
 */
@Composable
fun Navigation(

) {
    val navController = rememberNavController()
//    var currentRoute by remember {
//        mutableStateOf<Screen>(Screen.Home)
//    }
    val viewModel = viewModel<NavViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    val navStack by navController.currentBackStackEntryAsState()

    LaunchedEffect(navStack){
        val route = navStack?.destination?.route
        if(route== Screen.Home.HomeFeed::class.qualifiedName){
            viewModel.changeRoute(Screen.Home)
        }
    }

    Box(
        Modifier.fillMaxSize()
    ) {
        Scaffold(
            Modifier.fillMaxSize()
        ) {paddingValues ->
            NavHost(
                navController = navController ,
                startDestination = Screen.Home,
                modifier = Modifier.padding(paddingValues)
                    .padding(16.dp),
            ) {
                authGraph(navController){
                    viewModel.navBarVisible(it)
                }

                homeGraph(navController){
                    viewModel.navBarVisible(it)
                }

                composable<Screen.Jobs> {
                    Box(
                        Modifier.fillMaxSize()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ){
                        Text("All jobs page")
                    }
                }
                composable<Screen.Community> {
                    Box(
                        Modifier.fillMaxSize()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ){
                        Text("Community page")
                    }
                }
                composable<Screen.Account> {
                    UserProfilePageRoot(
                        onLogOut = {
                            navController.navigate(Screen.Auth){
                                popUpTo(Screen.Home){
                                    inclusive = true
                                }
                            }
                        }
                    )
                }

            }
        }
        if(state.navBarVisible){
            BottomNavBar(
                Modifier.align(Alignment.BottomCenter)
                    .navigationBarsPadding(),
                navController = navController,
                currentRoute = state.currentRoute,
                onRouteChange = {
                    viewModel.changeRoute(it)
                    navController.navigate(it){
                        this.popUpTo(Screen.Home.HomeFeed){
                            this.inclusive = false
                        }
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}