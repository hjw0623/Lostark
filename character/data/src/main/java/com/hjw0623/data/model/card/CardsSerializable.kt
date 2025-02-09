package com.hjw0623.data.model.card

import com.hjw0623.data.model.engravings.EngravingEffectSerializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CardsSerializable(
    @SerialName("Cards") val cards: List<CardSerializable>,
    @SerialName("Effects") val effects: List<CardEffectSerializable>
)
