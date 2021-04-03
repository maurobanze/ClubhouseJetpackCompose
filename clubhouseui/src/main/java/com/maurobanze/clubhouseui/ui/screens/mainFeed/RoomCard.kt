package com.maurobanze.clubhouseui.ui.screens.mainFeed

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.rounded.ChatBubble
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maurobanze.clubhouseui.models.Participant
import com.maurobanze.clubhouseui.models.Room
import com.maurobanze.clubhouseui.models.canSpeak
import com.maurobanze.clubhouseui.repository.FakeDataRepository
import com.maurobanze.clubhouseui.ui.components.Avatar
import com.maurobanze.clubhouseui.ui.theme.ClubhouseTheme

@Composable
fun RoomCard(
    room: Room,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Column(
            modifier = Modifier
                .clickable { onClick() }
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {

            Column(modifier = Modifier.padding(8.dp)) {
                room.clubName?.let {
                    ClubText(club = room.clubName)
                }

                Text(
                    modifier = Modifier
                        .padding(bottom = 16.dp),
                    text = room.title,
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Medium,
                    maxLines = 2
                )
            }

            Box {
                Box(
                    modifier = Modifier.width(96.dp),
                    contentAlignment = Alignment.TopEnd
                ) {
                    AvatarBundle(
                        avatar0Url = room.speakers[0].profilePictureUrl,
                        avatar1Url = room.speakers.getOrNull(1)?.profilePictureUrl,
                        modifier = Modifier.padding(end = 16.dp)
                    )
                }

                Column(modifier = Modifier.padding(start = 96.dp)) {
                    ImportantParticipantList(participants = room.importantParticipants)

                    CompositionLocalProvider(
                        LocalContentAlpha provides ContentAlpha.disabled
                    ) {
                        ParticipantCount(
                            participantCount = room.participants.size,
                            speakerCount = room.speakers.size
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ClubText(club: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = club.toUpperCase(Locale.current),
            modifier = Modifier.padding(end = 8.dp),
            style = MaterialTheme.typography.overline,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            maxLines = 1
        )
        CompositionLocalProvider(LocalContentColor provides MaterialTheme.colors.secondary) {
            Icon(
                imageVector = Icons.Rounded.Home,
                contentDescription = "club icon",
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Composable
private fun AvatarBundle(
    avatar0Url: String,
    avatar1Url: String?,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {

        val avatar0TopPadding = if (avatar1Url == null) 0.dp else 10.dp

        Avatar(
            pictureUrl = avatar0Url,
            modifier = Modifier
                .padding(start = 30.dp, top = avatar0TopPadding)
                .size(40.dp)
        )

        avatar1Url?.let {
            Avatar(
                pictureUrl = avatar1Url,
                modifier = Modifier.size(40.dp)
            )
        }
    }
}

/**
 * Note: Feels
 */
@Composable
private fun ImportantParticipantList(participants: List<Participant>) {
    Column {
        participants.forEach {
            ImportantParticipantText(participant = it)
        }
    }
}

@Composable
private fun ImportantParticipantText(participant: Participant) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = participant.name,
            modifier = Modifier.padding(end = 8.dp),
            fontSize = 18.sp
        )

        if (participant.canSpeak()) {
            Icon(
                imageVector = Icons.Outlined.ChatBubbleOutline,
                "speaker icon",
                modifier = Modifier.size(14.dp)
            )
        }
    }
}

@Composable
private fun ParticipantCount(participantCount: Int, speakerCount: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        ParticipantCountText(participantCount = participantCount)
        Text(
            text = "/",
            modifier = Modifier.padding(horizontal = 4.dp),
            style = MaterialTheme.typography.body2
        )
        SpeakerCountText(speakerCount = speakerCount)
    }
}

@Composable
private fun ParticipantCountText(participantCount: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "$participantCount",
            modifier = Modifier.padding(horizontal = 2.dp),
            style = MaterialTheme.typography.subtitle1
        )

        Icon(
            imageVector = Icons.Rounded.Person,
            "person icon",
            modifier = Modifier.size(14.dp)
        )
    }
}

@Composable
private fun SpeakerCountText(speakerCount: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "$speakerCount",
            modifier = Modifier.padding(horizontal = 2.dp),
            style = MaterialTheme.typography.subtitle1
        )

        Icon(
            imageVector = Icons.Rounded.ChatBubble,
            "speaker icon",
            modifier = Modifier.size(14.dp),
        )
    }
}

@Composable
@Preview
fun RoomCardPreview() {
    ClubhouseTheme {

        val fakeData = FakeDataRepository()
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {

            RoomCard(
                room = fakeData.fakeRoom(),
                onClick = {}
            )
        }
    }
}