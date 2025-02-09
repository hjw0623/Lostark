package com.hjw0623.data.model.card

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CardEffectSerializable(
    @SerialName("Index") val index: Int,
    @SerialName("CardSlots") val cardSlots: List<Int>,
    @SerialName("Items") val items: List<CardEffectItemSerializable>
)
