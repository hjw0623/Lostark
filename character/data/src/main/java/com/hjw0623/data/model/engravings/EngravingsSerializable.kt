package com.hjw0623.data.model.engravings

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EngravingsSerializable(
    @SerialName("Engravings") val engravings: List<EngravingSerializable>?,
    @SerialName("Effects") val effects: List<EngravingEffectSerializable>?,
    @SerialName("ArkPassiveEffects") val arkPassiveEffects: List<ArkPassiveEffectSerializable>?
)
