package com.hjw0623.events.presentation.events

import com.hjw0623.core.domain.util.DataError

sealed interface EventsEvent {
    data class Error(val error: DataError.Network): EventsEvent
}