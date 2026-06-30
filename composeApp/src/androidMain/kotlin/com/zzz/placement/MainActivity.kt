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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zzz.core.ui.presentation.components.TestContainer
import com.zzz.core.ui.theme.ClaveTheme
//import com.zzz.feature.auth.AuthRoot
import com.zzz.placement.nav.Navigation

/**
 * Android entry point
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
//            App()
//            TestContainer {
//                Scaffold(
//                    Modifier.fillMaxSize()
//                ) {paddingValues ->
//                    Column(
//                        Modifier.padding(paddingValues)
//                    ) {
//                        AuthRoot(
//                            navToHome = {}
//                        )
//                    }
//                }
//            }

            var darkTheme by rememberSaveable {
                mutableStateOf(true)
            }

            ClaveTheme(
                darkTheme = darkTheme
            ) {
                Navigation(
                    darkTheme = darkTheme,
                    onThemeChanged = {
                        darkTheme = it
                    }
                )
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
                .padding(16.dp)
        ) {
//            UpdateProfileDetailsPage()
        }
    }

}