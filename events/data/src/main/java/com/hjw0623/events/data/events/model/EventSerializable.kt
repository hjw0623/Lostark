package com.hjw0623.events.data.events.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EventSerializable(
    @SerialName("EndDate") val endDate: String,
    @SerialName("Link") val link: String,
    @SerialName("RewardDate") val rewardDate: String? = "",
    @SerialName("StartDate") val startDate: String,
    @SerialName("Thumbnail") val thumbnail: String,
    @SerialName("Title") val title: String
)
