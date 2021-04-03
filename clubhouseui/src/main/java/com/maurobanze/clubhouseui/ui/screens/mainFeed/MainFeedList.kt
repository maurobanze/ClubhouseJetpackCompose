package com.maurobanze.clubhouseui.ui.screens.mainFeed

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maurobanze.clubhouseui.models.Room
import com.maurobanze.clubhouseui.models.Schedule
import com.maurobanze.clubhouseui.repository.FakeDataRepository
import com.maurobanze.clubhouseui.ui.theme.ClubhouseTheme


@Composable
fun MainFeedList(
    eventSchedule: Schedule,
    rooms: List<Room>,
    roomItemOnClick: (roomId: String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 128.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        item(key = "eventSchedule") {
            EventScheduleCard(
                schedule = eventSchedule,
                modifier = Modifier.fillMaxWidth()
            )
        }

        items(
            items = rooms,
            key = { room -> room.id }
        ) { room ->
            RoomCard(
                room = room,
                onClick = { roomItemOnClick(room.id) },
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
@Preview
fun RoomListPreview() {
    ClubhouseTheme {
        val fakeData = FakeDataRepository()

        MainFeedList(
            eventSchedule = fakeData.fakeSchedule(),
            rooms = fakeData.fakeRooms(),
            roomItemOnClick = {}
        )
    }
}