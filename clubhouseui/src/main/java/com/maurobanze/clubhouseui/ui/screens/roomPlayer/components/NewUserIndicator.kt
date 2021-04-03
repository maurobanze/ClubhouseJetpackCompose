package com.maurobanze.clubhouseui.ui.screens.roomPlayer.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maurobanze.clubhouseui.ui.theme.ClubhouseTheme

@Composable
fun NewUserIndicator(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        elevation = 2.dp,
        shape = CircleShape
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(text = "🎉", maxLines = 1)
        }
    }
}

@Composable
@Preview
private fun Preview() {

    ClubhouseTheme {
        NewUserIndicator()
    }
}