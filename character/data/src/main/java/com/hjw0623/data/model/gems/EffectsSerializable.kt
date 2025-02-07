package com.hjw0623.data.model.gems

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EffectsSerializable(
    @SerialName("Description") val description: String,
    @SerialName("Skills") val skills: List<SkillSerializable>
)