package com.hjw0623.events.presentation.events.model

import com.hjw0623.events.domain.model.Event

data class EventUi(
    val link: String,
    val thumbnail: String
)

fun Event.toEventUi(): EventUi {
    return EventUi(
        link = link,
        thumbnail = thumbnail,
    )
}


