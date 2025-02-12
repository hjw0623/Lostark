package com.hjw0623.data.model.arkpassive

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArkEffectDto(
    @SerialName("Name") val name: String,
    @SerialName("Description") val description: String,
    @SerialName("Icon") val icon: String,
    @SerialName("ToolTip") val tooltip: String
)
