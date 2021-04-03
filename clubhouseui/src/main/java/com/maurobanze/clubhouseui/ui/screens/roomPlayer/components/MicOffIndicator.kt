package com.maurobanze.clubhouseui.ui.screens.roomPlayer.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Mic
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maurobanze.clubhouseui.ui.theme.ClubhouseTheme

@Composable
fun MicOffIndicator(
    modifier: Modifier = Modifier,
    lineColor: Color = MaterialTheme.colors.error
) {
    Card(
        modifier = modifier,
        shape = CircleShape,
        elevation = 2.dp
    ) {
        Box(contentAlignment = Alignment.Center) {
            val surfaceColor = MaterialTheme.colors.surface

            Icon(imageVector = Icons.Rounded.Mic, contentDescription = "mic status")

            Canvas(modifier = Modifier.matchParentSize()) {
                rotate(0f) {
                    drawLine(
                        start = Offset.Zero,
                        end = Offset(x = size.width, y = size.height),
                        color = surfaceColor,
                        strokeWidth = 14f
                    )

                    inset(20f) {

                        drawLine(
                            start = Offset.Zero,
                            end = Offset(x = size.width, y = size.height),
                            color = lineColor,
                            strokeWidth = 6f,
                            cap = StrokeCap.Round
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun MicIndicatorPreview() {
    ClubhouseTheme {
        MicOffIndicator()
    }
}