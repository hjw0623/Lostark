package com.hjw0623.data.model.gems

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GemsSerializable(
    @SerialName("Effects") val effects: EffectsSerializable,
    @SerialName("Gems") val gems: List<GemSerializable>
)