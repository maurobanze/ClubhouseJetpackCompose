package com.maurobanze.clubhouseui.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Apps
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.maurobanze.clubhouseui.features.*
import com.maurobanze.clubhouseui.repository.FakeDataRepository
import com.maurobanze.clubhouseui.ui.screens.mainFeed.MainFeedList
import com.maurobanze.clubhouseui.ui.screens.roomPlayer.RoomPlayerBottomSheet
import com.maurobanze.clubhouseui.ui.theme.ClubhouseTheme
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets


@ExperimentalStdlibApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainScreenViewModel = viewModel()
) {
    //TODO: wrap into a LaunchedEffect so it executes only once
    //viewModel.dispatchAction(MainScreenUiAction.LoadAllData)//TODO confirm it's right place to call

    val uiState by viewModel.uiStateAsFlow().collectAsState(initial = viewModel.currentUiState)

    MainScreen(
        modifier = modifier,
        uiState = uiState,
        roomPlayerBottomSheetOnClick = {
            viewModel.dispatchAction(
                MainScreenUiAction.SwitchSelectedRoomExpandState
            )
        },
        roomListItemOnClick = { roomId ->
            viewModel.dispatchAction(
                MainScreenUiAction.SelectRoom(roomId)
            )
        }
    )
}

@ExperimentalStdlibApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
private fun MainScreen(
    uiState: MainScreenUiState,
    roomPlayerBottomSheetOnClick: () -> Unit,
    roomListItemOnClick: (roomId: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val roomSelection = uiState.roomSelection

    Column(modifier = modifier) {

        MainScreenTopBar(
            roomPlayerExpanded = roomSelection is RoomSelection.Selected && roomSelection.expanded,
            activeUserProfilePictureUrl = uiState.activeUser?.profilePictureUrl,
            unreadNotificationCount = uiState.unreadNotificationCount ?: 0
        )

        Box(modifier = Modifier.fillMaxSize()) {

            when (uiState.state) {

                is MainScreenUiStates.LoadingData -> {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }

                MainScreenUiStates.DisplayingData -> {

                    androidx.compose.animation.AnimatedVisibility(
                        visible = !(roomSelection is RoomSelection.Selected
                                && roomSelection.expanded),
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        MainFeedList(
                            modifier = Modifier
                                .fillMaxSize()
                                .align(Alignment.TopCenter),
                            eventSchedule = uiState.eventSchedule!!,
                            rooms = uiState.rooms,
                            roomItemOnClick = roomListItemOnClick
                        )
                    }
                }

                null -> {
                }
            }

            val startRoomLayoutBottomPadding =
                if (uiState.roomSelection is RoomSelection.Selected)
                    116.dp else 48.dp

            BottomActions(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
                startRoomLayoutBottomPadding
            )

            androidx.compose.animation.AnimatedVisibility(
                visible = roomSelection is RoomSelection.Selected,
                enter = slideInVertically(
                    initialOffsetY = { fullHeight -> fullHeight }
                ),
                exit = slideOutVertically(
                    targetOffsetY = { fullHeight -> fullHeight }
                )
            ) {
                check(roomSelection is RoomSelection.Selected)

                RoomPlayerBottomSheet(
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.TopCenter),
                    expanded = roomSelection.expanded,
                    onClick = roomPlayerBottomSheetOnClick,
                    room = roomSelection.room
                )

            }
        }
    }

}

/**
 * Start a room, and view online contacts view
 */
@Composable
private fun BottomActions(modifier: Modifier = Modifier, bottomPadding: Dp) {
    Column(
        modifier = modifier
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            MaterialTheme.colors.background
                        )
                    )
                )
        ) {

            StartRoomButton(
                modifier = Modifier.align(Alignment.Center),
                onClick = {}
            )

            ViewContactsButton(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 36.dp)
                    .size(36.dp)
            )
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(bottomPadding)
                .background(MaterialTheme.colors.background)
        )
    }
}

@Composable
private fun StartRoomButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    FloatingActionButton(
        modifier = modifier,
        onClick = onClick,
        elevation = FloatingActionButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp
        ),

        ) {
        Row(
            Modifier.padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.padding(end = 8.dp),
                imageVector = Icons.Rounded.Add,
                contentDescription = "start a room"
            )
            Text(
                text = "Start a room",
                style = MaterialTheme.typography.button,
                fontSize = 18.sp
            )
        }
    }
}

@Composable
private fun ViewContactsButton(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier,
        imageVector = Icons.Rounded.Apps,
        contentDescription = "view online contacts"
    )
}

@ExperimentalStdlibApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
@Preview
fun MainFeedScreenPreview() {
    ClubhouseTheme {
        ProvideWindowInsets {
            val fakeData = FakeDataRepository()

            val uiState = MainScreenUiState(
                state = MainScreenUiStates.DisplayingData,
                activeUser = fakeData.fakeActiveUser(),
                unreadNotificationCount = 3,
                eventSchedule = fakeData.fakeSchedule(),
                rooms = fakeData.fakeRooms(),
                roomSelection = RoomSelection.Selected(
                    room = fakeData.fakeRoom(),
                    expanded = false
                )
            )

            MainScreen(
                modifier = Modifier.fillMaxSize(),
                uiState = uiState,
                roomPlayerBottomSheetOnClick = {

                },
                roomListItemOnClick = {

                }
            )
        }
    }
}

