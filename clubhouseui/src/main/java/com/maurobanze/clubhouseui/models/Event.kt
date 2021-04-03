package com.maurobanze.clubhouseui.models

import java.time.LocalDateTime
import java.util.*

data class Event(
    val id: String,
    val name: String,
    val time: Date,
    val clubName: String?,
    val description: String
)
