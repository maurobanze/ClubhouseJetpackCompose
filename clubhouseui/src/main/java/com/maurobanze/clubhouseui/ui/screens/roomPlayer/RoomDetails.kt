package com.maurobanze.clubhouseui.ui.screens.roomPlayer

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.MoreHoriz
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maurobanze.clubhouseui.models.*
import com.maurobanze.clubhouseui.repository.FakeDataRepository
import com.maurobanze.clubhouseui.ui.components.Avatar
import com.maurobanze.clubhouseui.ui.screens.roomPlayer.components.MicOffIndicator
import com.maurobanze.clubhouseui.ui.screens.roomPlayer.components.ModeratorIndicator
import com.maurobanze.clubhouseui.ui.screens.roomPlayer.components.NewUserIndicator
import com.maurobanze.clubhouseui.ui.screens.roomPlayer.components.SpeakingIndicator
import com.maurobanze.clubhouseui.ui.theme.ClubhouseTheme

@ExperimentalStdlibApi
@ExperimentalFoundationApi
@Composable
fun RoomDetails(
    room: Room,
    modifier: Modifier = Modifier
) {

    Box(modifier = modifier.padding(start = 24.dp, end = 24.dp, top = 24.dp)) {
        Icon(
            modifier = Modifier.align(Alignment.TopEnd),
            imageVector = Icons.Rounded.MoreHoriz,
            contentDescription = "More room options"
        )

        Column(modifier = Modifier.fillMaxWidth()) {
            room.clubName?.let {
                ClubText(club = room.clubName)
            }

            Text(
                modifier = Modifier.fillMaxWidth(0.9f),
                text = room.title,
                style = MaterialTheme.typography.h6
            )

            ParticipantGrid(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                speakers = room.speakers,
                followedBySpeakers = room.spectatorsFollowedBySpeakers,
                spectators = room.spectatorsNotFollowedBySpeakers
            )
        }
    }
}

@Composable
private fun ClubText(club: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = club.toUpperCase(Locale.current),
            modifier = Modifier.padding(end = 8.dp),
            style = MaterialTheme.typography.overline,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            maxLines = 1
        )

        CompositionLocalProvider(LocalContentColor provides MaterialTheme.colors.secondary) {
            Icon(
                imageVector = Icons.Rounded.Home,
                contentDescription = "club icon",
                modifier = Modifier.size(14.dp)
            )
        }
    }
}

@Composable
private fun ParticipantGrid(
    modifier: Modifier = Modifier,
    speakers: List<Participant>,
    followedBySpeakers: List<Participant>,
    spectators: List<Participant>
) {

    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(bottom = 256.dp)
    ) {
        val speakerRows = speakers.chunked(3)
        items(items = speakerRows) { speakerRow ->
            GridRow(
                items = speakerRow,
                columnCount = 3,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp)
            ) { speaker ->
                ParticipantGridItem(participant = speaker, avatarSize = 72.dp)
            }
        }

        item {
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.disabled) {
                Text(
                    text = "Followed by the speakers",
                    style = MaterialTheme.typography.body2,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(start = 16.dp, bottom = 16.dp)
                )
            }
        }

        val followedBySpeakerRows = followedBySpeakers.chunked(4)
        items(followedBySpeakerRows) { row ->

            GridRow(
                columnCount = 4,
                items = row,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) { participant ->
                ParticipantGridItem(participant = participant, avatarSize = 64.dp)
            }
        }

        item {
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.disabled) {
                Text(
                    text = "Others in the room",
                    style = MaterialTheme.typography.body2,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(start = 16.dp, top = 24.dp, bottom = 24.dp)
                )
            }
        }

        val spectatorRows = spectators.chunked(4)
        items(spectatorRows) { row ->
            GridRow(
                columnCount = 4,
                items = row,
                modifier = Modifier.fillMaxWidth()
            ) { spectator ->
                ParticipantGridItem(participant = spectator, avatarSize = 64.dp)
            }
        }
    }
}

@Composable
private fun GridRow(
    columnCount: Int,
    items: List<Participant>,
    modifier: Modifier = Modifier,
    itemContent: @Composable (participant: Participant) -> Unit
) {

    Row(modifier = modifier) {
        repeat(columnCount) { index ->
            Box(
                modifier = Modifier
                    .weight(1f, fill = true),
                propagateMinConstraints = true
            ) {

                if (index < items.size) {
                    itemContent(items[index])
                }
            }
        }
    }
}

@Composable
private fun ParticipantGridItem(
    participant: Participant,
    avatarSize: Dp,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Box {

            val speakingIndicatorAlpha =
                if (participant.canSpeak() && participant.isSpeaking())
                    1f else 0f

            SpeakingIndicator(
                modifier = Modifier
                    .size(avatarSize + 12.dp)
                    .alpha(speakingIndicatorAlpha),
                strokeWidth = 3.dp
            )

            Avatar(
                modifier = Modifier
                    .size(avatarSize)
                    .align(Alignment.Center),
                pictureUrl = participant.profilePictureUrl
            )

            if (participant.isNewUser) {
                NewUserIndicator(
                    modifier = Modifier
                        .size(32.dp)
                        .align(Alignment.BottomStart)
                )
            }
            if (participant.canSpeak() && participant.isMuted()) {
                MicOffIndicator(
                    modifier = Modifier
                        .size(32.dp)
                        .align(Alignment.BottomEnd)
                )
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            if (participant.isModerator()) {
                ModeratorIndicator(Modifier.size(20.dp))
            }

            Text(
                text = participant.name.substringBefore(" "),
                style = MaterialTheme.typography.body2,
                fontWeight = FontWeight.Medium,
                maxLines = 1
            )
        }
    }
}


@ExperimentalStdlibApi
@ExperimentalFoundationApi
@Composable
@Preview
fun RoomDetailsPreview() {

    val room = FakeDataRepository().fakeRoom()
    ClubhouseTheme {
        RoomDetails(room = room, Modifier.fillMaxSize())
    }
}

