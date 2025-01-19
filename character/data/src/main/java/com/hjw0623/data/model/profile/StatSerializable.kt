package com.hjw0623.data.model.profile

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StatSerializable(
    @SerialName("Type") val type: String,
    @SerialName("Value") val value: String,
    @SerialName("Tooltip") val tooltip: List<String>
)