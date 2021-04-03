package com.maurobanze.clubhouseui.models

data class Room(
    val id: String,
    val title: String,
    val clubName: String?,
    val participants: List<Participant>
) {
    /**
     * The following members serve as different views of the participant list.
     * Since the participant list can get large, in a real-world application you'd have to think where you perform
     * these computations, and for how long you keep the results around. For this example, they are performed
     * at object creation.
     */


    val speakers: List<Participant> = participants
        .filter { it.canSpeak() }
        .sortByImportance()

    val spectatorsFollowedBySpeakers: List<Participant> = participants.filter {
        it.isSpectatorFollowedBySpeakers()
    }


    val spectatorsNotFollowedBySpeakers: List<Participant> = participants.filter {
        it.isSpectatorNotFollowedBySpeakers()
    }

    val importantParticipants: List<Participant> =
        participants
            .filter { it.isSpeakerOrImportantSpectator() }
            .sortByImportance()


    private fun List<Participant>.sortByImportance() = this.sortedWith(
        compareBy {
            when {
                it.isModerator() -> 0
                it.isNonModeratingSpeaker() -> 1
                it.isImportantSpectator() -> 2
                else -> 3
            }
        }
    )
}
