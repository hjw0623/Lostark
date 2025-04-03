package com.hjw0623.events.presentation.events

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hjw0623.core.domain.util.onError
import com.hjw0623.core.domain.util.onSuccess
import com.hjw0623.core.presentation.ui.UiText
import com.hjw0623.core.presentation.ui.asUiText
import com.hjw0623.events.domain.EventsRepository
import com.hjw0623.events.presentation.events.model.IslandUi
import com.hjw0623.events.presentation.events.model.toEventUi
import com.hjw0623.events.presentation.events.model.toIslandUi
import com.hjw0623.events.presentation.events.model.toNoticeUi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import java.time.LocalDateTime

class EventsViewModel(
    private val eventsRepository: EventsRepository
) : ViewModel() {

    private var _state = MutableStateFlow(EventState())
    val state = _state
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            EventState()
        )

    private val _events = Channel<EventsEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    private var hasErrorOccurred = false

    init {
        viewModelScope.launch {
            while (true) {
                _state.update { it.copy(now = LocalDateTime.now()) }
                delay(1000)
            }
        }

        loadAllData()
    }

    private fun loadAllData() {
        loadIslands()
        loadEvents()
        loadNotices()
    }

    fun onIslandSelected(island: IslandUi?) {
        _state.update { it.copy(selectedIsland = island) }
    }

    private fun loadIslands() {
        viewModelScope.launch {
            Timber.d("Loading islands...")
            _state.update { it.copy(isIslandsLoading = true) }
            eventsRepository.getIslands()
                .onSuccess { islands ->
                    _state.update {
                        it.copy(
                            isIslandsLoading = false,
                            currentIslands = islands.map { it.toIslandUi() }
                        )
                    }
                    Timber.d("Successfully loaded islands: $islands")
                }
                .onError { error ->
                    Timber.e("$error", "Failed to load Events")
                    sendError(error.asUiText())
                }
        }
    }

    private fun loadEvents() {
        viewModelScope.launch {
            Timber.d("Loading events...")
            _state.update { it.copy(isEventsLoading = true) }

            eventsRepository
                .getEvents()
                .onSuccess { events ->
                    _state.update {
                        it.copy(
                            isEventsLoading = false,
                            events = events.map { it.toEventUi() }
                        )
                    }
                    Timber.d("Successfully loaded events: $events")
                }
                .onError { error ->
                    Timber.e("$error", "Failed to load Events")
                    sendError(error.asUiText())
                }
        }
    }

    private fun loadNotices() {
        viewModelScope.launch {
            _state.update { it.copy(isNoticesLoading = true) }

            eventsRepository
                .getNotices()
                .onSuccess { notices ->
                    _state.update {
                        it.copy(
                            isNoticesLoading = false,
                            notices = notices.map { it.toNoticeUi() }
                        )
                    }
                    Timber.d("Successfully loaded Notices: $notices")
                }
                .onError { error ->
                    Timber.e("$error", "Failed to load Events")
                    sendError(error.asUiText())
                }
        }
    }

    private fun sendError(message: UiText) {
        viewModelScope.launch {
            if (!hasErrorOccurred) {
                hasErrorOccurred = true
                _events.send(EventsEvent.Error(message))
            }
        }
    }
}
