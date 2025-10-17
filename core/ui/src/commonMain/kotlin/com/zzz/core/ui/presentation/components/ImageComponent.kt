package com.zzz.core.ui.presentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import coil3.request.crossfade

/**
 * For rendering resource image files
 *
 * Pass the image size explicitly via parameter.
 *
 * @author zyzz
*/
@Composable
fun ImageComponent(
    modifier: Modifier = Modifier ,
    imageRes : Int ,
    contentDescription : String? = null ,
    contentScale : ContentScale = ContentScale.Fit ,
    size : Dp = 100.dp
) {

    val context = LocalPlatformContext.current

    AsyncImage(
        model = ImageRequest.Builder(context)
            .data(imageRes)
            .crossfade(true)
            .build(),
        contentDescription = contentDescription,
        contentScale = contentScale,
        modifier = modifier.size(size)
    )

}

/**
 * For rendering remote images.
 *
 * Pass the image size explicitly via parameter.
 *
 * @author zyzz
 */
@Composable
fun ImageComponent(
    modifier: Modifier = Modifier ,
    imageUrl : String,
    contentDescription : String? = null ,
    contentScale : ContentScale = ContentScale.Fit ,
    size : Dp = 100.dp
) {

    val context = LocalPlatformContext.current

    AsyncImage(
        model = imageUrl,
        contentDescription = contentDescription,
        contentScale = contentScale,
        modifier = modifier.size(size)
    )


}

