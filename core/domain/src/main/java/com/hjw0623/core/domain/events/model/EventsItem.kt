package com.hjw0623.core.domain.events.model

data class EventsItem(
    val categoryName: String,
    val contentsName: String,
    val contentsIcon: String,
    val minItemLevel: Int,
    val startTimes: List<String>,
    val location: String,
    val rewardItems: List<RewardItem>
)