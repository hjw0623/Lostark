package com.hjw0623.data.model.engravings

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArkPassiveEffectSerializable(
    @SerialName("AbilityStoneLevel") val abilityStoneLevel: Int?,
    @SerialName("Grade") val grade: String,
    @SerialName("Level") val level: Int,
    @SerialName("Name") val name: String,
    @SerialName("Description") val description: String
)
