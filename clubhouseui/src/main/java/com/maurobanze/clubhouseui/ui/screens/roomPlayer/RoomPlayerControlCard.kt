package com.maurobanze.clubhouseui.ui.screens.roomPlayer

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PanTool
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.maurobanze.clubhouseui.ui.components.Avatar
import com.maurobanze.clubhouseui.ui.theme.ClubhouseTheme
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets
import dev.chrisbanes.accompanist.insets.navigationBarsPadding

@ExperimentalAnimationApi
@Composable
fun RoomPlayerControlCard(
    expanded: Boolean,
    avatarUrls: List<String>,
    modifier: Modifier = Modifier
) {
    val transition = updateTransition(targetState = expanded, label = "expand card")

    val paddingBottom by transition.animateDp(label = "content Padding") { isExpanded ->
        if (isExpanded) 48.dp else 16.dp
    }

    val leaveButtonHorizontalBias by transition.animateFloat(label = "button horizontal bias") { isExpanded ->
        if (isExpanded) -1f else 1f
    }
    val leaveButtonAlignment = BiasAlignment(leaveButtonHorizontalBias, verticalBias = 0f)

    val leaveButtonWidth by transition.animateDp(label = "button width") {
        if (expanded) 160.dp else 40.dp
    }

    val leaveButtonText =
        if (expanded) "✌\uD83C\uDFFD Leave quietly" else "✌\uD83C\uDFFD"

    val avatarListAlpha by transition.animateFloat(label = "avatar list alpha") { isExpanded ->
        if (isExpanded) 0f else 1f
    }

    val cardElevation = if (expanded) 0.dp else 64.dp

    RoomPlayerControlCard(
        modifier = modifier,
        avatarUrls = avatarUrls,
        elevation = cardElevation,
        contentBottomPadding = paddingBottom,
        avatarListAlpha = avatarListAlpha,
        leaveButtonWidth = leaveButtonWidth,
        leaveButtonAlignment = leaveButtonAlignment,
        leaveButtonText = leaveButtonText
    )
}

@Composable
private fun RoomPlayerControlCard(
    avatarUrls: List<String>,
    elevation: Dp,
    contentBottomPadding: Dp,
    avatarListAlpha: Float,
    leaveButtonWidth: Dp,
    leaveButtonAlignment: Alignment,
    leaveButtonText: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = elevation,
        shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
    ) {
        Row(
            modifier = Modifier
                .navigationBarsPadding()
                .fillMaxWidth()
                .padding(bottom = contentBottomPadding, top = 16.dp, start = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier.weight(1f, fill = true)
            ) {

                AvatarTrio(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .alpha(avatarListAlpha),
                    avatarUrls = avatarUrls
                )

                CircularButton(
                    modifier = Modifier
                        .width(leaveButtonWidth)
                        .align(leaveButtonAlignment),
                    onClick = {},
                ) {
                    Text(
                        text = leaveButtonText,
                        maxLines = 1,
                        color = MaterialTheme.colors.error
                    )
                }
            }

            CircularButton(
                onClick = {},
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "Ping people to room"
                )
            }

            CircularButton(
                onClick = {},
            ) {
                Icon(
                    imageVector = Icons.Outlined.PanTool,
                    contentDescription = "Raise hand"
                )
            }
        }
    }
}

@Composable
private fun AvatarTrio(
    avatarUrls: List<String>,
    modifier: Modifier = Modifier
) {
    require(avatarUrls.size <= 3) { "avatarUrls' size must be at most 3" }

    Row(modifier = modifier) {
        avatarUrls.forEachIndexed { index, url ->
            Avatar(
                pictureUrl = url,
                modifier = Modifier
                    .size(40.dp)
                    .offset((-10 * index).dp)
            )
        }
    }
}

@Composable
private fun CircularButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    FloatingActionButton(
        modifier = modifier.defaultMinSize(40.dp, 40.dp),
        onClick = onClick,
        elevation = FloatingActionButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp
        ),
        backgroundColor = LocalContentColor.current.copy(alpha = 0.05f)
    ) {
        content()
    }
}

@ExperimentalAnimationApi
@Preview
@Composable
fun Preview() {
    ClubhouseTheme {
        ProvideWindowInsets {
            Box(modifier = Modifier.fillMaxSize()) {
                var expanded by remember { mutableStateOf(false) }
                RoomPlayerControlCard(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .clickable { expanded = !expanded },
                    expanded = expanded,
                    avatarUrls = listOf(
                        "https://unsplash.com/photos/mEZ3PoFGs_k/download?w=300",
                        "https://unsplash.com/photos/ZHvM3XIOHoE/download?w=300",
                        "https://unsplash.com/photos/NohB3FJSY90/download?w=300"
                    )
                )
            }
        }
    }
}
