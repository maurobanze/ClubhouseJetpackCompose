package com.maurobanze.clubhouseui.ui.screens.roomPlayer.components

import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Stars
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.maurobanze.clubhouseui.ui.theme.ClubhouseTheme

@Composable
fun ModeratorIndicator(
    modifier: Modifier = Modifier
) {
    Icon(
        modifier = modifier,
        imageVector = Icons.Rounded.Stars,
        contentDescription = "moderator icon",
        tint = MaterialTheme.colors.secondary
    )
}


@Composable
@Preview
private fun Preview() {
    ClubhouseTheme {
        ModeratorIndicator()
    }
}