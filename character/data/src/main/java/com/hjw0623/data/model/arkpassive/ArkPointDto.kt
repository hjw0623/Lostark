package com.hjw0623.data.model.arkpassive

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArkPointDto(
    @SerialName("Name") val name: String,
    @SerialName("Value") val value: Int,
    @SerialName("Tooltip") val tooltip: String
)
