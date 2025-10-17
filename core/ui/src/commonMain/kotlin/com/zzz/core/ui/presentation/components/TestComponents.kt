package com.zzz.core.ui.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Test scaffold wrapper
 * @author zyzz
*/
@Composable
fun TestContainer(
    content : @Composable ()->Unit
) {
    Scaffold() {
        Box(
            Modifier.fillMaxSize()
                .padding(it)
        ){
            content()
        }
    }
}