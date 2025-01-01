package com.hjw0623.core.data.networking.events

import com.hjw0623.core.data.networking.events.model.EventSerializable
import com.hjw0623.core.domain.events.model.Event

fun EventSerializable.toDomain(): Event {
    return Event(
        endDate = this.endDate,
        link = this.link,
        rewardDate = this.rewardDate,
        startDate = this.title,
        thumbnail = this.thumbnail,
        title = this.title
    )
}