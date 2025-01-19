package com.hjw0623.character.domain.model

data class CharacterProfile(
    val characterImage: String,
    val expeditionLevel: Int,
    val pvpGradeName: String,
    val townLevel: Int?,
    val townName: String?,
    val title: String?,
    val guildMemberGrade: String?,
    val guildName: String?,
    val usingSkillPoint: Int,
    val totalSkillPoint: Int,
    val stats: List<Stat>,
    val tendencies: List<Tendency>,
    val serverName: String,
    val characterName: String,
    val characterLevel: Int,
    val characterClassName: String,
    val itemAvgLevel: String,
    val itemMaxLevel: String
)
