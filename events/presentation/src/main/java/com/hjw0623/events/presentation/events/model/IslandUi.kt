package com.hjw0623.events.presentation.events.model

import com.hjw0623.events.domain.model.Islands
import com.hjw0623.events.domain.model.Item
import java.time.LocalDate
import java.time.LocalDateTime

data class IslandUi(
    val name: String,
    val imgUrl: String,
    val mainRewardItem: String,
    val additionalItem: List<Item>,
    val startTimes: List<LocalDateTime>
)

fun Islands.toIslandUi(): IslandUi {
    val now = LocalDateTime.now()

    val allStartTimes = this.startTimes.map { LocalDateTime.parse(it) }

    val mainRewardItem = this.rewardItems.first().items.find { item ->
        item.startTimes?.any { startTime ->
            val date = LocalDate.parse(startTime.substring(0, 10))
            date == now.toLocalDate()
        } == true
    }?.name ?: "없음"

    return IslandUi(
        name = this.contentsName,
        imgUrl = this.contentsIcon,
        mainRewardItem = mainRewardItem,
        additionalItem = this.rewardItems.firstOrNull()?.items ?: emptyList(),
        startTimes = allStartTimes
    )
}

