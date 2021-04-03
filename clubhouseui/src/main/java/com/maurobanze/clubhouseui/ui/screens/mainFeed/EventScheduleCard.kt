package com.maurobanze.clubhouseui.ui.screens.mainFeed

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maurobanze.clubhouseui.models.Event
import com.maurobanze.clubhouseui.models.Schedule
import com.maurobanze.clubhouseui.ui.theme.ClubhouseTheme
import java.util.*

@Composable
fun EventScheduleCard(schedule: Schedule, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        elevation = 0.dp,
        backgroundColor = LocalContentColor.current.copy(alpha = 0.05f)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            schedule.events.forEach {
                EventScheduleListItem(event = it)
            }
        }
    }
}

@Composable
private fun EventScheduleListItem(event: Event) {
    Box {
        Box(
            modifier = Modifier.width(96.dp),
            contentAlignment = Alignment.TopEnd
        ) {
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    modifier = Modifier.padding(end = 16.dp),
                    style = MaterialTheme.typography.body2,
                    text = "12:34 PM"
                )
            }
        }

        Column(modifier = Modifier.padding(start = 96.dp)) {
            event.clubName?.let {
                ClubText(
                    club = it,
                    modifier = Modifier.padding(
                        bottom = 2.dp,
                        top = 2.dp
                    )
                )
            }

            Text(
                text = event.name,
                modifier = Modifier.fillMaxWidth(),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
private fun ClubText(club: String, modifier: Modifier) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {
        Text(
            text = club.toUpperCase(Locale.current),
            modifier = Modifier.padding(end = 8.dp),
            style = MaterialTheme.typography.overline,
            fontSize = 10.sp,
            fontWeight = FontWeight.Medium,
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
@Preview
private fun Preview() {
    ClubhouseTheme {
        Surface(color = MaterialTheme.colors.background) {
            EventScheduleCard(
                schedule = Schedule(
                    events = listOf(
                        Event(
                            "jodfj9344f",
                            "NFTs FTW (For the win)",
                            Date(),
                            "Bitcoin Corner",
                            "A very nice bitcoin NFT event happening soon"
                        ),
                        Event(
                            "rlpkoivosnj003",
                            "How to Get Away with Murder: let's talk",
                            Date(),
                            "Tv Shows we love",
                            "Bla bla bla bla"
                        ),
                        Event(
                            "k3o1uifkm",
                            "Guitar masterclass with Tommy Emmanuel",
                            Date(),
                            clubName = null,
                            "Bla bla bla bla"
                        )
                    )
                )
            )
        }
    }
}

