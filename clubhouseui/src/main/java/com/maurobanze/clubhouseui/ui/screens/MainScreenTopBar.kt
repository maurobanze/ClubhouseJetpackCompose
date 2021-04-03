package com.maurobanze.clubhouseui.ui.screens

import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.icons.rounded.ExpandMore
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maurobanze.clubhouseui.ui.components.Avatar
import com.maurobanze.clubhouseui.ui.theme.ClubhouseTheme

@ExperimentalAnimationApi
@Composable
fun MainScreenTopBar(
    modifier: Modifier = Modifier,
    roomPlayerExpanded: Boolean,
    unreadNotificationCount: Int = 0,
    activeUserProfilePictureUrl: String? = null
) {
    TopAppBar(
        modifier = modifier,
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 0.dp)
    ) {

        val iconSize = 28.dp
        Box(modifier = Modifier.fillMaxSize()) {

            Crossfade(
                targetState = roomPlayerExpanded,
                modifier = Modifier.align(Alignment.CenterStart)
            ) { expanded ->
                if (expanded) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            modifier = Modifier
                                .size(iconSize)
                                .padding(end = 4.dp),
                            imageVector = Icons.Rounded.ExpandMore,
                            contentDescription = "back to room list"
                        )

                        Text(
                            text = "All rooms",
                            fontWeight = FontWeight.Medium
                        )
                    }
                } else {
                    Icon(
                        modifier = Modifier
                            .size(iconSize)
                            .align(Alignment.CenterStart),
                        imageVector = Icons.Outlined.Search,
                        contentDescription = "search"
                    )
                }
            }

            Row(
                modifier = Modifier.align(Alignment.CenterEnd),
                horizontalArrangement = Arrangement.spacedBy(32.dp, Alignment.End)
            ) {

                Box(contentAlignment = Alignment.CenterEnd) {

                    val transition = updateTransition(targetState = roomPlayerExpanded)

                    val alpha by transition.animateFloat { expanded ->
                        if (expanded) 1f else 0f
                    }

                    val counterAlpha = 1f - alpha

                    Box(Modifier.alpha(alpha)) {
                        Icon(
                            modifier = Modifier.size(iconSize),
                            imageVector = Icons.Outlined.Description,
                            contentDescription = "community guidelines"
                        )
                    }

                    Box(Modifier.alpha(counterAlpha)) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(
                                32.dp,
                                Alignment.End
                            )
                        ) {
                            Icon(
                                modifier = Modifier.size(iconSize),
                                imageVector = Icons.Outlined.Inbox,
                                contentDescription = "inbox"
                            )
                            Icon(
                                modifier = Modifier.size(iconSize),
                                imageVector = Icons.Outlined.Today,
                                contentDescription = "upcoming events"
                            )
                            NotificationIcon(
                                modifier = Modifier.size(iconSize),
                                unreadCount = unreadNotificationCount
                            )
                        }
                    }

                }

                Crossfade(
                    targetState = activeUserProfilePictureUrl != null,
                    modifier = Modifier.size(iconSize)
                ) { activeUserExists ->

                    if (activeUserExists) {
                        check(activeUserProfilePictureUrl != null)

                        Avatar(
                            pictureUrl = activeUserProfilePictureUrl,
                            contentDescription = "profile",
                            modifier = Modifier.size(iconSize)
                        )
                    } else {
                        Icon(
                            modifier = Modifier.size(iconSize),
                            imageVector = Icons.Outlined.Person,
                            contentDescription = "profile"
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun NotificationIcon(unreadCount: Int, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Icon(
            modifier = Modifier.fillMaxSize(),
            imageVector = Icons.Outlined.Notifications,
            contentDescription = "notifications"
        )

        if (unreadCount > 0) {
            Surface(
                modifier = Modifier.align(Alignment.TopEnd),
                shape = CircleShape,
                color = Color.Red,
                border = BorderStroke(2.dp, Color.White)
            ) {
                Text(
                    text = "$unreadCount",
                    style = MaterialTheme.typography.caption,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(horizontal = 4.dp),
                    color = Color.White
                )
            }
        }
    }
}

@ExperimentalAnimationApi
@Composable
@Preview
fun MainScreenTopBarPreview() {
    ClubhouseTheme {
        Surface(color = MaterialTheme.colors.background) {
            MainScreenTopBar(
                activeUserProfilePictureUrl = "https://unsplash.com/photos/NohB3FJSY90/download?w=300",
                roomPlayerExpanded = false
            )
        }
    }
}