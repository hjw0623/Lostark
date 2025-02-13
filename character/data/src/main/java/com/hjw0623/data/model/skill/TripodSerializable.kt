package com.hjw0623.data.model.skill

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TripodSerializable(
    @SerialName("Tier") val tier: Int,
    @SerialName("Slot") val slot: Int,
    @SerialName("Name") val name: String,
    @SerialName("Icon") val icon: String,
    @SerialName("Level") val level: Int,
    @SerialName("IsSelected") val isSelected: Boolean,
    @SerialName("Tooltip") val tooltip: String
)
