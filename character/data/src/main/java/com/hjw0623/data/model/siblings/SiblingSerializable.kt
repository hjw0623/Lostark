package com.hjw0623.data.model.siblings

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SiblingSerializable(
    @SerialName ("ServerName") val serverName: String,
    @SerialName ("CharacterName") val characterName: String,
    @SerialName ("CharacterLevel") val characterLevel: Int,
    @SerialName ("CharacterClassName") val characterClassName: String,
    @SerialName ("ItemAvgLevel") val itemAvgLevel: String,
    @SerialName ("ItemMaxLevel") val itemMaxLevel: String
)