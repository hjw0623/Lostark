package com.hjw0623.events.data.events

import com.hjw0623.events.data.events.model.EventSerializable
import com.hjw0623.events.domain.model.Event

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