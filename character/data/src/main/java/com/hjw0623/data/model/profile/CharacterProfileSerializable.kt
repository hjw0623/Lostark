package com.hjw0623.data.model.profile

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterProfileSerializable(
    @SerialName("CharacterImage") val characterImage: String,
    @SerialName("ExpeditionLevel") val expeditionLevel: Int,
    @SerialName("PvpGradeName") val pvpGradeName: String,
    @SerialName("TownLevel") val townLevel: Int?,
    @SerialName("TownName") val townName: String?,
    @SerialName("Title") val title: String?,
    @SerialName("GuildMemberGrade") val guildMemberGrade: String?,
    @SerialName("GuildName") val guildName: String?,
    @SerialName("UsingSkillPoint") val usingSkillPoint: Int,
    @SerialName("TotalSkillPoint") val totalSkillPoint: Int,
    @SerialName("Stats") val stats: List<StatSerializable>,
    @SerialName("Tendencies") val tendencies: List<TendencySerializable>,
    @SerialName("ServerName") val serverName: String,
    @SerialName("CharacterName") val characterName: String,
    @SerialName("CharacterLevel") val characterLevel: Int,
    @SerialName("CharacterClassName") val characterClassName: String,
    @SerialName("ItemAvgLevel") val itemAvgLevel: String,
    @SerialName("ItemMaxLevel") val itemMaxLevel: String
)