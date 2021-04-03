package com.maurobanze.clubhouseui.features

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maurobanze.clubhouseui.models.Room
import com.maurobanze.clubhouseui.models.Schedule
import com.maurobanze.clubhouseui.models.User
import com.maurobanze.clubhouseui.repository.FakeDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

/**
 * Simple ViewModel providing fake data for demonstration purposes only.
 * I use MVI, but you could use your favourite architecture, as long as you are able to expose
 * state for the Ui to render.
 *
 * To learn more about MVI and Unidirectional Data Flow, check out:
 * - [Jake Wharthon's talk](https://www.youtube.com/watch?v=0IKHxjkgop4)
 * - [Tutorial](https://www.raywenderlich.com/817602-mvi-architecture-for-android-tutorial-getting-started)
 */
class MainScreenViewModel(
    initialState: MainScreenUiState = MainScreenUiState(),
    private val repository: FakeDataRepository = FakeDataRepository(),
) : ViewModel() {

    var currentUiState: MainScreenUiState = initialState
        private set

    private val mutex = Mutex()

    private val uiStateFlow = MutableSharedFlow<MainScreenUiState>(replay = 1)

    fun uiStateAsFlow(): SharedFlow<MainScreenUiState> = uiStateFlow

    fun dispatchAction(uiAction: MainScreenUiAction) {
        onActionReceived(uiAction)
    }

    init {
        dispatchAction(MainScreenUiAction.LoadAllData)
    }

    private fun onActionReceived(uiAction: MainScreenUiAction) {
        when (uiAction) {
            is MainScreenUiAction.LoadAllData -> executeLoadAllData()

            is MainScreenUiAction.SelectRoom -> dispatchChange(
                MainScreenChange.UserSelectedRoom(uiAction.roomId)
            )

            is MainScreenUiAction.SwitchSelectedRoomExpandState -> dispatchChange(
                MainScreenChange.UserSwitchedSelectedRoomExpandState
            )
        }
    }

    private fun onReduce(
        currentState: MainScreenUiState,
        change: MainScreenChange
    ): MainScreenUiState {

        return when (change) {

            is MainScreenChange.StartedLoadingData -> currentState.copy(
                state = MainScreenUiStates.LoadingData
            )

            is MainScreenChange.LoadedAllData -> currentState.copy(
                state = MainScreenUiStates.DisplayingData,
                activeUser = change.activeUser,
                unreadNotificationCount = change.unreadNotificationCount,
                eventSchedule = change.eventSchedule,
                rooms = change.rooms
            )

            is MainScreenChange.UserSelectedRoom -> {
                val selectedRoom = currentState.rooms.find { it.id == change.roomId }!!
                currentState.copy(
                    roomSelection = RoomSelection.Selected(selectedRoom, expanded = true)
                )
            }

            is MainScreenChange.UserSwitchedSelectedRoomExpandState -> {

                when (currentState.roomSelection) {
                    is RoomSelection.NoSelection -> currentState
                    is RoomSelection.Selected -> currentState.copy(
                        roomSelection = currentState.roomSelection.copy(
                            expanded = !currentState.roomSelection.expanded
                        )
                    )
                }
            }
        }
    }

    private fun executeLoadAllData() {
        dispatchChange(MainScreenChange.StartedLoadingData)

        viewModelScope.launch(Dispatchers.IO) {
            delay(1000)//artificial delay to simulate loading data

            val activeUser = repository.fakeActiveUser()
            val unreadNotificationCount = repository.unreadNotificationCount()
            val roomSchedule = repository.fakeSchedule()
            val rooms = repository.fakeRooms()

            dispatchChange(
                MainScreenChange.LoadedAllData(
                    activeUser, unreadNotificationCount, roomSchedule, rooms
                )
            )
        }
    }

    /**
     * Sends a [MainScreenChange] to [onReduce] for a new state to be
     * generated. It uses a Mutex to ensure the correct, latest ui state is used.
     */
    private fun dispatchChange(change: MainScreenChange) {
        viewModelScope.launch(Dispatchers.Default) {
            mutex.withLock {//syncronized block
                val newUiState = onReduce(currentUiState, change)
                if (newUiState !== currentUiState) {//referential equality check
                    currentUiState = newUiState
                    uiStateFlow.emit(newUiState)
                }
            }
        }
    }
}

data class MainScreenUiState(
    val state: MainScreenUiStates? = null,
    val activeUser: User? = null,
    val unreadNotificationCount: Int? = null,
    val eventSchedule: Schedule? = null,
    val rooms: List<Room> = listOf(),
    val roomSelection: RoomSelection = RoomSelection.NoSelection
)

sealed class MainScreenUiStates {
    object LoadingData : MainScreenUiStates()
    object DisplayingData : MainScreenUiStates()
}

sealed class RoomSelection {
    object NoSelection : RoomSelection()
    data class Selected(
        val room: Room,
        val expanded: Boolean
    ) : RoomSelection()
}

sealed class MainScreenUiAction {
    object LoadAllData : MainScreenUiAction()
    object SwitchSelectedRoomExpandState : MainScreenUiAction()
    data class SelectRoom(val roomId: String) : MainScreenUiAction()
}


sealed class MainScreenChange {

    object StartedLoadingData : MainScreenChange()

    data class LoadedAllData(
        val activeUser: User,
        val unreadNotificationCount: Int,
        val eventSchedule: Schedule,
        val rooms: List<Room>
    ) : MainScreenChange()

    object UserSwitchedSelectedRoomExpandState : MainScreenChange()
    data class UserSelectedRoom(val roomId: String) : MainScreenChange()
}