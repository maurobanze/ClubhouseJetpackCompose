package com.maurobanze.clubhouseui.models

import com.maurobanze.clubhouseui.models.Participant.SpeakingStatus
import com.maurobanze.clubhouseui.models.Participant.SpeakingStatus.Muted
import com.maurobanze.clubhouseui.models.Participant.SpeakingStatus.Unmuted
import com.maurobanze.clubhouseui.models.Participant.Type.*

data class Participant(

    val id: String,
    val name: String,
    val profilePictureUrl: String,
    val isNewUser: Boolean,
    val type: Type
) {
    sealed class Type {
        /**
         * @param important: A spectator can be important if:
         * - current user follows them
         * - high-ranked influencer
         * - It is the current user
         *
         */
        data class Spectator(val important: Boolean, val followedBySpeakers: Boolean) : Type()
        data class Speaker(val speakingStatus: SpeakingStatus) : Type()
        data class Moderator(val speakingStatus: SpeakingStatus) : Type()
    }

    sealed class SpeakingStatus {
        object Muted : SpeakingStatus()
        data class Unmuted(val speaking: Boolean) : SpeakingStatus()
    }
}

fun Participant.canSpeak(): Boolean = type !is Spectator

fun Participant.isSpectatorFollowedBySpeakers(): Boolean =
    type is Spectator && type.followedBySpeakers

fun Participant.isSpectatorNotFollowedBySpeakers(): Boolean =
    type is Spectator && !type.followedBySpeakers

fun Participant.isImportantSpectator(): Boolean =
    type is Spectator && type.important

fun Participant.isSpeakerOrImportantSpectator(): Boolean = canSpeak() || isImportantSpectator()

fun Participant.isModerator(): Boolean = type is Moderator

fun Participant.isNonModeratingSpeaker(): Boolean = type is Speaker

fun Participant.isMuted(): Boolean {

    val speakingStatus: SpeakingStatus = when (type) {
        is Speaker -> type.speakingStatus
        is Moderator -> type.speakingStatus
        is Spectator -> throw IllegalArgumentException(
            "Checking if a Spectator is Muted is considered a logic error."
        )
    }

    return speakingStatus is Muted
}

fun Participant.isSpeaking(): Boolean {

    val speakingStatus: SpeakingStatus = when (type) {
        is Speaker -> type.speakingStatus
        is Moderator -> type.speakingStatus
        is Spectator -> throw IllegalArgumentException(
            "Checking if a Spectator is Speaking is considered a logic error."
        )
    }

    return speakingStatus is Unmuted && speakingStatus.speaking
}


