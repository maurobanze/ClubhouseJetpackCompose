package com.maurobanze.clubhouseui.repository

import com.maurobanze.clubhouseui.models.*
import com.maurobanze.clubhouseui.utils.times
import java.util.*

class FakeDataRepository {

    fun fakeActiveUser() = User(
        "aidfjkmosf03",
        "Mauro Banze",
        "https://unsplash.com/photos/NohB3FJSY90/download?w=300"
    )

    fun unreadNotificationCount() = 6

    fun fakeSchedule() = Schedule(
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

    fun fakeRoom() = Room(
        id = "ld89jklfdmklvihy",
        title = "NFT: the future of art?",
        clubName = "Bitcoin corner",
        participants = 4 * listOf(
            Participant(
                id = "01i3920r8uj",
                name = "Mauro Banze",
                profilePictureUrl = "https://unsplash.com/photos/NohB3FJSY90/download?w=300",
                isNewUser = true,
                type = Participant.Type.Spectator(important = false, followedBySpeakers = true)
            ),
            Participant(
                id = "landofk4",
                name = "Mauro Banze",
                profilePictureUrl = "https://unsplash.com/photos/NohB3FJSY90/download?w=300",
                isNewUser = false,
                type = Participant.Type.Spectator(important = false, followedBySpeakers = true)
            ),
            Participant(
                id = "inkdcmn339u02",
                name = "Amanda Alexandra",
                profilePictureUrl = "https://unsplash.com/photos/ZHvM3XIOHoE/download?w=300",
                isNewUser = true,
                type = Participant.Type.Moderator(
                    Participant.SpeakingStatus.Unmuted(
                        speaking = true
                    )
                )
            ),
            Participant(
                id = "0pmdnjk9089mksfGM",
                name = "Helen Kesete",
                profilePictureUrl = "https://unsplash.com/photos/mEZ3PoFGs_k/download?w=300",
                isNewUser = true,
                type = Participant.Type.Speaker(Participant.SpeakingStatus.Muted)
            )
        )
    )

    fun fakeRooms(): List<Room> = listOf(
        Room(
            id = "ld89jklfdmklvihy",
            title = "NFT: the future of art?",
            clubName = "Bitcoin corner",
            participants = listOf(
                Participant(
                    id = "inkdcmn339u02",
                    name = "Amanda Alexandra",
                    profilePictureUrl = "https://unsplash.com/photos/Cc5mWXngpmw/download?w=300",
                    isNewUser = false,
                    type = Participant.Type.Moderator(
                        Participant.SpeakingStatus.Unmuted(
                            true
                        )
                    )
                ),
                Participant(
                    id = "0pmdnjk9089mksfGM",
                    name = "Helen Kesete",
                    profilePictureUrl = "https://unsplash.com/photos/mEZ3PoFGs_k/download?w=300",
                    isNewUser = false,
                    type = Participant.Type.Speaker(Participant.SpeakingStatus.Muted)
                )
            ) + (
                    10 * listOf(
                        Participant(
                            id = "01i3920r8uj",
                            name = "Linda Mao",
                            profilePictureUrl = "https://unsplash.com/photos/AHBvAIVqk64/download?w=300",
                            isNewUser = false,
                            type = Participant.Type.Spectator(
                                important = false,
                                followedBySpeakers = false
                            )
                        ),
                        Participant(
                            id = "landofk4",
                            name = "Marcelo Dauane",
                            profilePictureUrl = "https://unsplash.com/photos/NohB3FJSY90/download?w=300",
                            isNewUser = false,
                            type = Participant.Type.Spectator(false, followedBySpeakers = true)
                        ),
                        Participant(
                            id = "sfdjnfdsjkdfza",
                            name = "Jane Doe",
                            profilePictureUrl = "https://unsplash.com/photos/AR7aumwKr2s/download?w=300",
                            isNewUser = false,
                            type = Participant.Type.Spectator(false, followedBySpeakers = true)
                        )
                    )
                    )
        ),
        Room(
            id = "02oTYWskmlfmlsf",
            title = "Should early-stage founders take a salary?",
            clubName = null,
            participants = listOf(
                Participant(
                    id = "01i3920r8uj",
                    name = "Chet Atkins",
                    profilePictureUrl = "https://unsplash.com/photos/NohB3FJSY90/download?w=300",
                    isNewUser = false,
                    type = Participant.Type.Speaker(
                        Participant.SpeakingStatus.Unmuted(speaking = false)
                    )
                ),
                Participant(
                    id = "inkdcmn339u02",
                    name = "Tommy Emmanuel",
                    profilePictureUrl = "https://unsplash.com/photos/ZHvM3XIOHoE/download?w=300",
                    isNewUser = false,
                    type = Participant.Type.Moderator(
                        Participant.SpeakingStatus.Unmuted(
                            true
                        )
                    )
                ),
                Participant(
                    id = "0pmdnjk9089mksfGM",
                    name = "Helen Kesete",
                    profilePictureUrl = "https://unsplash.com/photos/mEZ3PoFGs_k/download?w=300",
                    isNewUser = true,
                    type = Participant.Type.Speaker(Participant.SpeakingStatus.Muted)
                )
            ) + (20 * listOf(
                Participant(
                    id = "sdijo2r8u0ojfsdlmn",
                    name = "Cecil Marvin",
                    profilePictureUrl = "https://unsplash.com/photos/763-mBawsfg/download?w=300",
                    isNewUser = false,
                    type = Participant.Type.Spectator(false, followedBySpeakers = true)
                ),

                Participant(
                    id = "sfdjnfdsjkdfza",
                    name = "Guidione Machava",
                    profilePictureUrl = "https://unsplash.com/photos/DNikBY1J--g/download?w=300",
                    isNewUser = false,
                    type = Participant.Type.Spectator(false, followedBySpeakers = true)
                ),
                Participant(
                    id = "r2309ifnklfds",
                    name = "Martin",
                    profilePictureUrl = "https://unsplash.com/photos/tidSLv-UaNs/download?w=300",
                    isNewUser = false,
                    type = Participant.Type.Spectator(false, followedBySpeakers = true)
                ),
                Participant(
                    id = "38djskfji409",
                    name = "Stélio do Rosário",
                    profilePictureUrl = "https://unsplash.com/photos/6UWqw25wfLI/download?w=300",
                    isNewUser = false,
                    type = Participant.Type.Spectator(false, followedBySpeakers = false)
                ),
                Participant(
                    id = "2r8u90jofsdG",
                    name = "Kim",
                    profilePictureUrl = "https://unsplash.com/photos/IHIgnhLvz5Q/download?w=300",
                    isNewUser = false,
                    type = Participant.Type.Spectator(false, followedBySpeakers = false)
                ),
                Participant(
                    id = "29i0djklfdlkn",
                    name = "Tobi",
                    profilePictureUrl = "https://unsplash.com/photos/Ap2wEySalkk/download?w=300",
                    isNewUser = false,
                    type = Participant.Type.Spectator(false, followedBySpeakers = false)
                )
            ))
        ),
        Room(
            id = "lmdsvij49009efmlk",
            title = "Just pure vibes",
            clubName = "Chill House",
            participants = listOf(
                Participant(
                    id = "inkdcmn339u02",
                    name = "Gabor Hastings",
                    profilePictureUrl = "https://unsplash.com/photos/ZHvM3XIOHoE/download?w=300",
                    isNewUser = true,
                    type = Participant.Type.Moderator(
                        Participant.SpeakingStatus.Unmuted(
                            speaking = true
                        )
                    )
                ),
                Participant(
                    id = "0pmdnjk9089mksfGM",
                    name = "Alina Roberts",
                    profilePictureUrl = "https://unsplash.com/photos/mEZ3PoFGs_k/download?w=300",
                    isNewUser = false,
                    type = Participant.Type.Speaker(Participant.SpeakingStatus.Muted)
                )
            ) + (15 * listOf(
                Participant(
                    id = "fdsf4t",
                    name = "Benedita",
                    profilePictureUrl = "https://unsplash.com/photos/yRpe13BHdKw/download?w=300",
                    isNewUser = false,
                    type = Participant.Type.Spectator(
                        important = false,
                        followedBySpeakers = true
                    )
                ),
                Participant(
                    id = "fdsf4t",
                    name = "Carla",
                    profilePictureUrl = "https://unsplash.com/photos/a0bhuv8Lvg0/download?w=300",
                    isNewUser = false,
                    type = Participant.Type.Spectator(
                        important = false,
                        followedBySpeakers = true
                    )
                ),
                Participant(
                    id = "fdsf4t",
                    name = "Vikky",
                    profilePictureUrl = "https://unsplash.com/photos/yViyrYmnYvE/download?w=300",
                    isNewUser = false,
                    type = Participant.Type.Spectator(
                        important = false,
                        followedBySpeakers = true
                    )
                ),
                Participant(
                    id = "48udfnkfdkUT",
                    name = "Alison Tshala",
                    profilePictureUrl = "https://unsplash.com/photos/4yTHVRarfGY/download?w=300",
                    isNewUser = false,
                    type = Participant.Type.Spectator(
                        important = false,
                        followedBySpeakers = false
                    )
                )
            ))
        ),
        Room(
            id = "buqytecd0249u87",
            title = "The future of Hip-Hop",
            clubName = null,
            participants = listOf(
                Participant(
                    id = "01i3920r8uj",
                    name = "Kendrick Lamar",
                    profilePictureUrl = "https://unsplash.com/photos/bJ2Dm9ZyeIY/download?w=300",
                    isNewUser = false,
                    type = Participant.Type.Moderator(
                        Participant.SpeakingStatus.Unmuted(
                            true
                        )
                    )
                ),
                Participant(
                    id = "inkdcmn339u02",
                    name = "Jermain Cole",
                    profilePictureUrl = "https://unsplash.com/photos/X-LDSuL0dEM/download?w=300",
                    isNewUser = false,
                    type = Participant.Type.Moderator(
                        Participant.SpeakingStatus.Unmuted(
                            true
                        )
                    )
                ),
                Participant(
                    id = "0pmdnjk9089mksfGM",
                    name = "Mauro Banze",
                    profilePictureUrl = "https://unsplash.com/photos/mEZ3PoFGs_k/download?w=300",
                    isNewUser = false,
                    type = Participant.Type.Speaker(Participant.SpeakingStatus.Muted)
                )
            ) + (3 * listOf(
                Participant(
                    id = "894894i2nf",
                    name = "Mary",
                    profilePictureUrl = "https://unsplash.com/photos/sLGYaQ_stMM/download?w=300",
                    isNewUser = false,
                    type = Participant.Type.Spectator(
                        important = false, followedBySpeakers = true
                    )
                ),
                Participant(
                    id = "gt34tv09jndfd",
                    name = "Verna",
                    profilePictureUrl = "https://unsplash.com/photos/dnL6ZIpht2s/download?w=300",
                    isNewUser = false,
                    type = Participant.Type.Spectator(
                        important = false, followedBySpeakers = true
                    )
                ),
                Participant(
                    id = "sdjnkfjiore",
                    name = "Lucia Williams",
                    profilePictureUrl = "https://unsplash.com/photos/J1jYLLlRpA4/download?w=300",
                    isNewUser = false,
                    type = Participant.Type.Spectator(
                        important = false, followedBySpeakers = false
                    )
                ), Participant(
                    id = "230i9dfmk,",
                    name = "Gloria Adams",
                    profilePictureUrl = "https://unsplash.com/photos/ouDjrbw-fs0/download?w=300",
                    isNewUser = false,
                    type = Participant.Type.Spectator(
                        important = false, followedBySpeakers = false
                    )
                )
            ))
        ),
        Room(
            id = "kjlsjndfb3",
            title = "To the Mooooon (on the way to Mars)🚀",
            clubName = null,
            participants = listOf(
                Participant(
                    id = "01i3920r8uj",
                    name = "Jeff Bezos",
                    profilePictureUrl = "https://unsplash.com/photos/rymh7EZPqRs/download?w=300",
                    isNewUser = false,
                    type = Participant.Type.Moderator(
                        Participant.SpeakingStatus.Unmuted(
                            true
                        )
                    )
                ),
                Participant(
                    id = "inkdcmn339u02",
                    name = "Elon Musk",
                    profilePictureUrl = "https://unsplash.com/photos/Gv6b0bPhAg0/download?w=300",
                    isNewUser = false,
                    type = Participant.Type.Moderator(
                        Participant.SpeakingStatus.Unmuted(
                            true
                        )
                    )
                ),
                Participant(
                    id = "0pmdnjk9089mksfGM",
                    name = "MKBHD",
                    profilePictureUrl = "https://unsplash.com/photos/2EGNqazbAMk/download?w=300",
                    isNewUser = false,
                    type = Participant.Type.Speaker(Participant.SpeakingStatus.Muted)
                )
            ) + (2 * listOf(
                Participant(
                    id = "39fdmnfFFfio3",
                    name = "Clovis",
                    profilePictureUrl = "https://unsplash.com/photos/bEZQWH49daU/download?w=300",
                    isNewUser = false,
                    type = Participant.Type.Spectator(
                        important = false, followedBySpeakers = true
                    )
                ),
                Participant(
                    id = "39fdmnfFFfio3",
                    name = "Clovis",
                    profilePictureUrl = "https://unsplash.com/photos/Izg_qfON0ao/download?w=300",
                    isNewUser = false,
                    type = Participant.Type.Spectator(
                        important = false, followedBySpeakers = true
                    )
                ),
                Participant(
                    id = "39fdmnfFFfio3",
                    name = "Courtland Allen",
                    profilePictureUrl = "https://unsplash.com/photos/tkXJoA_sn78/download?w=300",
                    isNewUser = false,
                    type = Participant.Type.Spectator(
                        important = false, followedBySpeakers = true
                    )
                )

            )) + (100 * listOf(

                Participant(
                    id = "gt34tv09jndfd",
                    name = "Verna",
                    profilePictureUrl = "https://unsplash.com/photos/dnL6ZIpht2s/download?w=300",
                    isNewUser = false,
                    type = Participant.Type.Spectator(
                        important = false, followedBySpeakers = false
                    )
                ),
                Participant(
                    id = "sdjnkfjiore",
                    name = "Lucia Williams",
                    profilePictureUrl = "https://unsplash.com/photos/J1jYLLlRpA4/download?w=300",
                    isNewUser = false,
                    type = Participant.Type.Spectator(
                        important = false, followedBySpeakers = false
                    )
                ), Participant(
                    id = "230i9dfmk,",
                    name = "Lucia Williams",
                    profilePictureUrl = "https://unsplash.com/photos/ouDjrbw-fs0/download?w=300",
                    isNewUser = false,
                    type = Participant.Type.Spectator(
                        important = false, followedBySpeakers = false
                    )
                )
            ))
        )
    )
}