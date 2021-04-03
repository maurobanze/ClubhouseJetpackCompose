package com.maurobanze.clubhouseui.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.chrisbanes.accompanist.coil.CoilImage

/**
 * Draws an avatar, loading the image from the network.
 * The shape doesn't exactly match Clubhouse's, but it's good enough for now
 */
@Composable
fun Avatar(
    pictureUrl: String,
    modifier: Modifier = Modifier,
    contentDescription: String = "avatar",
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
            shape = RoundedCornerShape(percent = 45)
        ) {
            CoilImage(
                modifier = Modifier
                    .fillMaxSize(),
                data = pictureUrl,
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop,
                fadeIn = true
            )
        }
    }
}


@Composable
@Preview
fun AvatarPreview() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Avatar(
            "https://unsplash.com/photos/NohB3FJSY90/download?w=300",
            modifier = Modifier.size(300.dp)
        )
    }
}