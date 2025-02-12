package com.hjw0623.data.model.arkpassive

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArkPassiveDto(
    @SerialName("IsArkPassive") val isArkPassive: Boolean,
    @SerialName("Points") val points: List<ArkPointDto>,
    @SerialName("Effects") val effects: List<ArkEffectDto>
)
