package com.hjw0623.data.model.engravings

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EngravingEffectSerializable(
    @SerialName("Icon") val icon: String,
    @SerialName("Name") val name: String,
    @SerialName("Description") val description: String
)
