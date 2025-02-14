package com.hjw0623.data.model.avatar

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AvatarSerializable(
    @SerialName("Type") val type: String,
    @SerialName("Name") val name: String,
    @SerialName("Icon") val icon: String,
    @SerialName("Grade") val grade: String,
    @SerialName("IsSet") val isSet: Boolean,
    @SerialName("IsInner") val isInner: Boolean,
    @SerialName("Tooltip") val tooltip: String,
)
