package com.hjw0623.data.model.skill

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RuneSerializable(
    @SerialName("Name") val name: String,
    @SerialName("Icon") val icon: String,
    @SerialName("Grade") val grade: String,
    @SerialName("Tooltip") val tooltip: String
)
