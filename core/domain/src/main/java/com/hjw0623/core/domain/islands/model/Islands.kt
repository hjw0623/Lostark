package com.hjw0623.core.domain.islands.model

data class Islands(
    val categoryName: String,
    val contentsName: String,
    val contentsIcon: String,
    val minItemLevel: Int,
    val startTimes: List<String>,
    val location: String,
    val rewardItems: List<IslandRewardItems>
)