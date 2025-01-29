package com.hjw0623.character.presentation.model.profile

import com.hjw0623.character.domain.model.profile.CharacterProfile

fun CharacterProfile.toCharacterProfileUi(): CharacterProfileUi {
    return CharacterProfileUi(
        characterImage = this.characterImage,
        characterClassName = this.characterClassName,
        serverName = this.serverName,
        title = this.title ?: "",
        characterName = this.characterName,
        itemAvgLevel = this.itemAvgLevel,
        characterLevel = this.characterLevel,
        guildName = this.guildName ?: "",
        expeditionLevel = this.expeditionLevel,
        pvpGradeName = this.pvpGradeName,
        townLevel = this.townLevel ?: 0,
        townName = this.townName ?: ""
    )
}