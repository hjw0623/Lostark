package com.hjw0623.data.model.skill

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SkillSerializable(
    @SerialName("Name") val name: String,
    @SerialName("Icon") val icon: String,
    @SerialName("Level") val level: Int,
    @SerialName("Type") val type: String,
    @SerialName("SkillType") val skillType: Int,
    @SerialName("Tripods") val tripods: List<TripodSerializable>,
    @SerialName("Rune") val rune: RuneSerializable?,
    @SerialName("Tooltip") val tooltip: String
)
