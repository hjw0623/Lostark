package com.hjw0623.events.presentation.events

import com.hjw0623.core.presentation.ui.UiText

sealed interface EventsEvent {
    data class Error(val error: UiText): EventsEvent
}