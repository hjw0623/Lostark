package com.hjw0623.events.data.islands.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IslandsSerializable(
    @SerialName("CategoryName") val categoryName: String,
    @SerialName("ContentsName") val contentsName: String,
    @SerialName("ContentsIcon") val contentsIcon: String,
    @SerialName("MinItemLevel") val minItemLevel: Int,
    @SerialName("StartTimes") val startTimes: List<String>? = emptyList(),
    @SerialName("Location") val location: String,
    @SerialName("RewardItems") val rewardItems: List<IslandRewardItemsSerializable> = emptyList()
)