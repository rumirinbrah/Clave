package com.zzz.placement

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
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
import com.zzz.feature.auth.SignUpScreen
import com.zzz.feature.auth.login.LoginScreen
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
                Scaffold(
                    Modifier.fillMaxSize()
                ) {paddingValues ->
                    Column(
                        Modifier.padding(paddingValues)
                    ) {
                        SignUpScreen {  }
                    }
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
            UserProfilePageRoot()
        }
    }

}