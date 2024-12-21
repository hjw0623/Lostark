package com.hjw0623.core.data.networking.events

import com.hjw0623.core.data.networking.events.model.EventsItemSerializable
import com.hjw0623.core.data.networking.events.model.ItemSerializable
import com.hjw0623.core.data.networking.events.model.RewardItemSerializable
import com.hjw0623.core.domain.events.model.EventsItem
import com.hjw0623.core.domain.events.model.Item
import com.hjw0623.core.domain.events.model.RewardItem

fun EventsItemSerializable.toDomain(): EventsItem {
    return EventsItem(
        categoryName = this.categoryName,
        contentsName = this.contentsName,
        contentsIcon = this.contentsIcon,
        minItemLevel = this.minItemLevel,
        startTimes = this.startTimes,
        location = this.location,
        rewardItems = this.rewardItems.map { it.toDomain() }
    )
}

fun RewardItemSerializable.toDomain(): RewardItem {
    return RewardItem(
        itemLevel = this.itemLevel,
        items = this.items.map { it.toDomain() }
    )
}

fun ItemSerializable.toDomain(): Item {
    return Item(
        name = this.name,
        icon = this.icon,
        grade = this.grade,
        startTimes = this.startTimes
    )
}

