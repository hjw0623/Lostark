package com.hjw0623.core.data.networking.events.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ItemSerializable(
    @SerialName("Name") val name: String,
    @SerialName("Icon") val icon: String,
    @SerialName("Grade") val grade: String,
    @SerialName("StartTimes") val startTimes: List<String>? = null
)