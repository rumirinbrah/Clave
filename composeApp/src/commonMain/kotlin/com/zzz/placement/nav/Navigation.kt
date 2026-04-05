package com.zzz.placement.nav

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zzz.feature.job.home.presentation.JobHomePageRoot

/**
 * Entry point
 * @author zyzz
 */
@Composable
fun Navigation(

) {
    val navController = rememberNavController()

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
                authGraph(navController)

                composable<Screen.Home> {
                    JobHomePageRoot()
                }

            }
        }

        BottomNavBar(
            Modifier.align(Alignment.BottomCenter)
                .navigationBarsPadding(),
            navController = navController,
            currentRoute = Screen.Home,
            onRouteChange = {}
        )
    }
}