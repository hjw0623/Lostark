package com.hjw0623.events.presentation.events

sealed interface EventsAction {
    data object OnEventDetailClick: EventsAction
    data object OnNoticeClick: EventsAction
}