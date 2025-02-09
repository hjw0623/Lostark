package com.hjw0623.data.model.card

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CardSerializable(
    @SerialName("Slot") val slot: Int,
    @SerialName("Name") val name: String,
    @SerialName("Icon") val icon: String,
    @SerialName("AwakeCount") val awakeCount: Int,
    @SerialName("AwakeTotal") val awakeTotal: Int,
    @SerialName("Grade") val grade: String,
    @SerialName("Tooltip") val tooltip: String
)
