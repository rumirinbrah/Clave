package com.zzz.placement

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.zzz.core.ui.network.NetworkObserverCommon
import com.zzz.core.ui.presentation.components.ImageComponent
import com.zzz.core.ui.presentation.components.TestContainer
import com.zzz.core.ui.theme.ClaveTheme
import com.zzz.feature.job.user.presentation.UserProfilePageRoot
import com.zzz.feature.job.user.presentation.components.ProfileActionCard
import com.zzz.placement.nav.BottomNavBar
import com.zzz.placement.nav.Screen
import kotlinx.coroutines.launch
import placementapp.composeapp.generated.resources.Res
import placementapp.composeapp.generated.resources.compose_multiplatform
import java.time.LocalDate

/**
 * Android entry point
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
//            App()
            TestContainer {
                Column {
                    val scope = rememberCoroutineScope()
                    val context = LocalContext.current
                    val connectivity = remember { NetworkObserverCommon() }
                    val isConnected = remember {
                        scope.launch {
                            connectivity.isConnected.collect {
                            }
                        }
                    }

                    Text("HI")
                    ImageComponent(
                        imageUrl = "https://es.web.img3.acsta.net/pictures/23/06/02/12/12/3866876.jpg" ,
                        size = 300.dp
                    )
//                    ProfileActionCard(
//                        icon = 2,
//                        actionText = "",
//                        onClick = {}
//                    )
                    BottomNavBar(
                        navController = rememberNavController() ,
                        currentRoute = Screen.Home ,
                        onRouteChange = {

                        }
                    )
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppAndroidPreview() {
    ClaveTheme {
        Box(
            Modifier.fillMaxSize()
        ) {
            BottomNavBar(
                navController = rememberNavController() ,
                currentRoute = Screen.Home ,
                onRouteChange = {

                }
            )
        }
    }

}