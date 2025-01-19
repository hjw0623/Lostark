package com.hjw0623.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TendencySerializable(
    @SerialName("Type") val type: String,
    @SerialName("Point") val point: Int,
    @SerialName("MaxPoint") val maxPoint: Int
)