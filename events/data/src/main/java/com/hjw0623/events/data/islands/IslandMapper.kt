package com.hjw0623.events.data.islands

import com.hjw0623.events.data.islands.model.IslandsSerializable
import com.hjw0623.events.data.islands.model.ItemSerializable
import com.hjw0623.events.data.islands.model.IslandRewardItemsSerializable
import com.hjw0623.events.domain.model.IslandRewardItems
import com.hjw0623.events.domain.model.Islands
import com.hjw0623.events.domain.model.Item

fun IslandsSerializable.toDomain(): Islands {
    return Islands(
        categoryName = this.categoryName,
        contentsName = this.contentsName,
        contentsIcon = this.contentsIcon,
        minItemLevel = this.minItemLevel,
        startTimes = this.startTimes?: emptyList(),
        location = this.location,
        rewardItems = this.rewardItems.map { it.toDomain() }
    )
}

fun IslandRewardItemsSerializable.toDomain(): IslandRewardItems {
    return IslandRewardItems(
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

