package com.hjw0623.data.model.gems

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SkillSerializable(
    @SerialName("Description") val description: List<String>,
    @SerialName("GemSlot") val gemSlot: Int,
    @SerialName("Icon") val icon: String,
    @SerialName("Name") val name: String,
    @SerialName("Option") val option: String,
    @SerialName("Tooltip") val tooltip: String
)