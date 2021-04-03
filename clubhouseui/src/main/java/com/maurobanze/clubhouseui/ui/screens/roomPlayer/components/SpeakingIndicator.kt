package com.maurobanze.clubhouseui.ui.screens.roomPlayer.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun SpeakingIndicator(
    modifier: Modifier = Modifier,
    strokeColor: Color = MaterialTheme.colors.primary,
    strokeWidth: Dp = 4.dp
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(percent = 45),
        border = BorderStroke(strokeWidth, strokeColor),
        color = Color.Transparent
    ) {}
}