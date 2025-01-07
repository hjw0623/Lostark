package com.hjw0623.events.presentation.events

import com.hjw0623.events.presentation.events.model.EventUi
import com.hjw0623.events.presentation.events.model.IslandUi

sealed interface EventsAction {
    data class OnIslandDetailClick(val islandUi: IslandUi): EventsAction
    data class OnEventDetailClick(val eventUi: EventUi): EventsAction
    data class OnNoticeDetailClick(val eventUi: EventUi): EventsAction
}