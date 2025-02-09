package com.hjw0623.data.model.card

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CardEffectItemSerializable(
    @SerialName("Name") val name: String,
    @SerialName("Description") val description: String
)
