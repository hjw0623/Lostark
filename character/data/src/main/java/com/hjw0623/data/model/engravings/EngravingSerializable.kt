package com.hjw0623.data.model.engravings

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EngravingSerializable(
    @SerialName("Slot") val slot: Int,
    @SerialName("Name") val name: String,
    @SerialName("Icon") val icon: String,
    @SerialName("Tooltip") val tooltip: String
)
