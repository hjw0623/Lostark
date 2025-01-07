package com.hjw0623.events.presentation.events.model

import com.hjw0623.events.domain.Islands
import com.hjw0623.events.domain.Item
import java.time.LocalDate

data class IslandUi(
    val name: String,
    val imgUrl: String,
    val mainRewardItem: String,
    val additionalItem: List<Item>
)

fun Islands.toIslandUi(): IslandUi {
    val currentDate = LocalDate.now()

    val name = this.contentsName
    val imgUrl = this.contentsIcon

    // 현재 날짜와 일치하는 startTimes를 가진 첫 번째 Item의 이름 찾기
    val mainRewardItem = this.rewardItems.first().items.find { item ->
        item.startTimes?.any { startTime ->
            val date = LocalDate.parse(startTime.substring(0, 10)) // Extract and parse date
            date == currentDate // Check if the date matches currentDate
        } == true
    }?.name ?: "No Reward Today" // 없을 경우 기본값 설정

    val additionalItem = this.rewardItems.first().items

    return IslandUi(
        name = name,
        imgUrl = imgUrl,
        mainRewardItem = mainRewardItem,
        additionalItem = additionalItem
    )
}

