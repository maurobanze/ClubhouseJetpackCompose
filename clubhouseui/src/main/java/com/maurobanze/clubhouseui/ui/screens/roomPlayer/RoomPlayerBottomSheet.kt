package com.maurobanze.clubhouseui.ui.screens.roomPlayer


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maurobanze.clubhouseui.models.Room
import com.maurobanze.clubhouseui.repository.FakeDataRepository
import com.maurobanze.clubhouseui.ui.theme.ClubhouseTheme

@ExperimentalStdlibApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun RoomPlayerBottomSheet(
    expanded: Boolean,
    room: Room,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {

        AnimatedVisibility(
            visible = expanded,
            modifier = Modifier.align(Alignment.BottomCenter),
            enter = slideInVertically(
                initialOffsetY = { fullHeight -> fullHeight }
            ),
            exit = slideOutVertically(
                targetOffsetY = { fullHeight -> fullHeight }
            )
        ) {


            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.98f)
                    .clickable(
                        onClick = onClick,
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ),
                shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
            ) {
                RoomDetails(
                    room = room
                )
            }
        }

        RoomPlayerControlCard(
            expanded = expanded,
            modifier = Modifier
                .clickable(
                    onClick = onClick,
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                )
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            avatarUrls = room.participants.take(3).map {
                it.profilePictureUrl
            }
        )
    }
}

@ExperimentalStdlibApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
@Preview
fun RoomPlayerBottomSheetPreview() {
    ClubhouseTheme {
        val fakeData = FakeDataRepository()
        var expanded: Boolean by remember { mutableStateOf(false) }
        RoomPlayerBottomSheet(
            expanded = expanded,
            onClick = { expanded = !expanded },
            room = fakeData.fakeRoom()
        )
    }
}