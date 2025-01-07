package com.hjw0623.events.presentation.events.model

import com.hjw0623.events.domain.Islands
import com.hjw0623.events.domain.Item

data class IslandUi(
    val name: String,
    val imgUrl: String,
    val mainRewardItem: String,
    val additionalItem: List<Item>
)

fun Islands.toIslandUi(): IslandUi {
    val name = this.contentsName
    val imgUrl = this.contentsIcon
    val mainRewardItem = this.rewardItems.first().items.last().name
    val additionalItem = this.rewardItems.first().items

    return IslandUi(
        name = name,
        imgUrl = imgUrl,
        mainRewardItem = mainRewardItem,
        additionalItem = additionalItem
    )
}
