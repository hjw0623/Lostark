package com.hjw0623.events.presentation.events

import androidx.compose.runtime.Immutable
import com.hjw0623.events.presentation.events.model.EventUi
import com.hjw0623.events.presentation.events.model.IslandUi
import com.hjw0623.events.presentation.events.model.NoticeUi

@Immutable
data class EventState(
    val isLoading: Boolean = false,
    val currentIslands: List<IslandUi> = emptyList(),
    val selectedIsland: IslandUi? = null,
    val events: List<EventUi> = emptyList(),
    val notices: List<NoticeUi> = emptyList()
)
