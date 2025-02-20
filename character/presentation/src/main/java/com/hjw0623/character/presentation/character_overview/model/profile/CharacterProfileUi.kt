package com.hjw0623.character.presentation.character_overview.model.profile

data class CharacterProfileUi(
    val characterImage: String,
    val characterClassName: String,
    val serverName: String,
    val title: String,
    val characterName: String,
    val itemAvgLevel: String,
    val characterLevel: Int,
    val guildName: String,
    val expeditionLevel: Int,
    val pvpGradeName: String,
    val townLevel: Int,
    val townName: String,
)
