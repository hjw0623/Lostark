package com.hjw0623.events.presentation.events

import androidx.compose.runtime.traceEventEnd
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hjw0623.core.domain.util.onError
import com.hjw0623.core.domain.util.onSuccess
import com.hjw0623.events.domain.EventsRepository
import com.hjw0623.events.domain.Islands
import com.hjw0623.events.presentation.events.model.toEventUi
import com.hjw0623.events.presentation.events.model.toIslandUi
import com.hjw0623.events.presentation.events.model.toNoticeUi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class EventsViewModel(
    private val eventsRepository: EventsRepository
) : ViewModel() {

    private val _state = MutableStateFlow(EventState())
    val state = _state
        .onStart { loadAllData() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            EventState()
        )

    private val _events = Channel<EventsEvent>()
    val events = _events.receiveAsFlow()

    fun onAction(action: EventsAction) {
        when (action) {
            is EventsAction.OnEventDetailClick -> TODO()
            is EventsAction.OnIslandDetailClick -> TODO()
            is EventsAction.OnNoticeDetailClick -> TODO()
        }
    }

    private fun loadAllData() {
        loadIslands()
        loadEvents()
        loadNotices()
    }

    private fun loadIslands() {
        viewModelScope.launch {
            _state.update { it.copy(
                isLoading = true
            ) }

            eventsRepository
                .getIslands()
                .onSuccess { islands ->
                    val filteredIslands = filterEventsByCurrentDate(islands)
                    _state.update { it.copy(
                        isLoading = false,
                        currentIslands = filteredIslands.map { it.toIslandUi() }
                    ) }
                }
                .onError { error ->
                    _state.update { it.copy(isLoading = false) }
                    _events.send(EventsEvent.Error(error))
                }
        }
    }

    private fun loadEvents() {
        viewModelScope.launch {
            _state.update { it.copy(
                isLoading = true
            ) }

            eventsRepository
                .getEvents()
                .onSuccess { events ->
                    _state.update { it.copy(
                        isLoading = false,
                        events = events.map { it.toEventUi() }
                    )}
                }
                .onError { error ->
                    _state.update { it.copy(isLoading = false) }
                    _events.send(EventsEvent.Error(error))
                }
        }
    }
    private fun loadNotices() {
        viewModelScope.launch {
            _state.update { it.copy(
                isLoading = true
            ) }

            eventsRepository
                .getNotices()
                .onSuccess { notices ->
                    _state.update { it.copy(
                        isLoading = false,
                        notices = notices.map { it.toNoticeUi() }
                    ) }
                }
                .onError { error ->
                    _state.update { it.copy(isLoading = false) }
                    _events.send(EventsEvent.Error(error))
                }
        }
    }

    fun filterEventsByCurrentDate(islands: List<Islands>): List<Islands> {
        val currentDate = LocalDate.now()
        return islands.mapNotNull { island ->
            val filteredStartTimes = island.startTimes.filter { startTime ->
                val eventDate = LocalDate.parse(startTime.substring(0, 10), DateTimeFormatter.ISO_LOCAL_DATE)
                eventDate == currentDate
            }
            if (filteredStartTimes.isNotEmpty()) {
                island.copy(startTimes = filteredStartTimes)
            } else {
                null
            }
        }
    }

}