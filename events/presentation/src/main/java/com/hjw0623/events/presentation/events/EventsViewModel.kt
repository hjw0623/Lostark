package com.hjw0623.events.presentation.events

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.hjw0623.events.domain.EventsRepository

class EventsViewModel(
    private val eventsRepository: EventsRepository
): ViewModel() {

    var state by mutableStateOf(EventsState())
        private set


    fun onAction(action: EventsAction) {

    }
}