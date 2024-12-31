package com.hjw0623.core.data.networking.islands

import com.hjw0623.core.data.networking.islands.model.IslandsSerializable
import com.hjw0623.core.data.networking.islands.model.ItemSerializable
import com.hjw0623.core.data.networking.islands.model.IslandRewardItemsSerializable
import com.hjw0623.core.domain.islands.model.Islands
import com.hjw0623.core.domain.islands.model.Item
import com.hjw0623.core.domain.islands.model.IslandRewardItems

fun IslandsSerializable.toDomain(): Islands {
    return Islands(
        categoryName = this.categoryName,
        contentsName = this.contentsName,
        contentsIcon = this.contentsIcon,
        minItemLevel = this.minItemLevel,
        startTimes = this.startTimes,
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

