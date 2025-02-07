package com.hjw0623.data.model.gems

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GemSerializable(
    @SerialName("Grade") val grade: String,
    @SerialName("Icon") val icon: String,
    @SerialName("Level") val level: Int,
    @SerialName("Name") val name: String,
    @SerialName("Slot") val slot: Int,
    @SerialName("Tooltip") val tooltip: String?
)