package com.zzz.core.ui.presentation.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Spacer util
 * @author zyzz
 */
@Composable
fun HorizontalSpace(
    width : Dp = 20.dp
) {
    Spacer(Modifier.height(width))
}