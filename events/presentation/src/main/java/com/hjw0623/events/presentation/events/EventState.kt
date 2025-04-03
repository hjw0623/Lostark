package com.hjw0623.events.presentation.events

import com.hjw0623.events.presentation.events.model.EventUi
import com.hjw0623.events.presentation.events.model.IslandUi
import com.hjw0623.events.presentation.events.model.NoticeUi
import java.time.LocalDateTime

data class EventState(
    val isIslandsLoading: Boolean = false,
    val isEventsLoading: Boolean = false,
    val isNoticesLoading: Boolean = false,
    val currentIslands: List<IslandUi> = emptyList(),
    var selectedIsland: IslandUi? = null,
    val events: List<EventUi> = emptyList(),
    val notices: List<NoticeUi> = emptyList(),
    val now: LocalDateTime = LocalDateTime.now()
) {
    val isLoading: Boolean
        get() = isIslandsLoading || isEventsLoading || isNoticesLoading
}
